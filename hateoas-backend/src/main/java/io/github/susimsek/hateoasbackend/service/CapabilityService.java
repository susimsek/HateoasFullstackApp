package io.github.susimsek.hateoasbackend.service;

import io.github.susimsek.hateoasbackend.domain.Capability;
import io.github.susimsek.hateoasbackend.exception.CapabilityNotFoundException;
import io.github.susimsek.hateoasbackend.repository.CapabilityRepository;
import io.github.susimsek.hateoasbackend.service.mapper.CapabilityMapper;
import io.github.susimsek.hateoasbackend.service.request.CapabilityCreateRequest;
import io.github.susimsek.hateoasbackend.service.request.CapabilityUpdateRequest;
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

    public List<Capability> getAllCapabilities() {
        return capabilityRepository.findAll();
    }

    public Page<Capability> getAllCapabilitiesWithPagination(Pageable pageable) {
        return  capabilityRepository.findAll(pageable);
    }

    public Capability getCapability(Long id) {
        return findById(id);
    }

    public Capability saveCapability(CapabilityCreateRequest capabilityCreateRequest) {
        var capability = capabilityMapper.toCapability(capabilityCreateRequest);
        return capabilityRepository.save(capability);
    }

    public Capability updateCapability(Long id, CapabilityUpdateRequest capabilityUpdateRequest) {
        var capability = findById(id);
        capabilityMapper.partialUpdate(capability, capabilityUpdateRequest);
        return capabilityRepository.save(capability);
    }

    public void deleteCapability(Long id) {
        var capability = findById(id);
        capabilityRepository.delete(capability);
    }

    Capability findById(Long id) {
        return capabilityRepository.findById(id)
                .orElseThrow(() -> new CapabilityNotFoundException(String.format("Capability with ID: %d Not found", id)));
    }
}