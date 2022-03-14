package io.github.susimsek.hateoasbackend.service.mapper;

import io.github.susimsek.hateoasbackend.domain.Capability;
import io.github.susimsek.hateoasbackend.service.dto.CapabilityDto;
import io.github.susimsek.hateoasbackend.service.request.CapabilityCreateRequest;
import io.github.susimsek.hateoasbackend.service.request.CapabilityUpdateRequest;
import org.mapstruct.*;

@Mapper
public interface CapabilityMapper extends EntityMapper<CapabilityDto, Capability> {

    Capability toCapability(CapabilityCreateRequest capabilityCreateRequest);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Capability entity, CapabilityUpdateRequest dto);
}
