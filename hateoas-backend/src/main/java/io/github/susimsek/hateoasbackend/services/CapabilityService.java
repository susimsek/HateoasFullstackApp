package io.github.susimsek.hateoasbackend.services;

import io.github.susimsek.hateoasbackend.domain.Capability;
import io.github.susimsek.hateoasbackend.dto.CapabilityDto;
import io.github.susimsek.hateoasbackend.exceptions.CapabilityNotFoundException;
import io.github.susimsek.hateoasbackend.mapper.CapabilityMapper;
import io.github.susimsek.hateoasbackend.repositories.CapabilityRepository;
import io.github.susimsek.hateoasbackend.request.CapabilityCreateRequest;
import io.github.susimsek.hateoasbackend.request.CapabilityUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CapabilityService {

    final CapabilityRepository capabilityRepository;
    final CapabilityMapper capabilityMapper;

    public List<CapabilityDto> getAllCapabilities() {
        return capabilityRepository.findAll().stream()
                .map(capabilityMapper::toDto)
                .toList();
    }

    public Page<CapabilityDto> getAllCapabilitiesWithPagination(Pageable pageable) {
        return capabilityRepository.findAll(pageable)
                .map(capabilityMapper::toDto);
    }

    public CapabilityDto getCapability(Long id) {
        var capability = findById(id);
        return capabilityMapper.toDto(capability);
    }

    public CapabilityDto saveCapability(CapabilityCreateRequest capabilityCreateRequest) {
        var capability = capabilityMapper.toCapability(capabilityCreateRequest);
        capability = capabilityRepository.save(capability);
        return save(capability);
    }

    public CapabilityDto updateCapability(Long id, CapabilityUpdateRequest capabilityUpdateRequest) {
        var capability = findById(id);
        capabilityMapper.partialUpdate(capability, capabilityUpdateRequest);
        return save(capability);
    }

    public void deleteCapability(Long id) {
        var capability = findById(id);
        capabilityRepository.delete(capability);
    }

    Capability findById(Long id) {
        return capabilityRepository.findById(id)
                .orElseThrow(() -> new CapabilityNotFoundException(String.format("Capability with ID: %d Not found", id)));
    }

    CapabilityDto save(Capability capability) {
       capability = capabilityRepository.save(capability);
       return capabilityMapper.toDto(capability);
    }
}