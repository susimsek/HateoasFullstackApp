package io.github.susimsek.hateoasbackend.controller.assembler;

import io.github.susimsek.hateoasbackend.controller.CapabilityController;
import io.github.susimsek.hateoasbackend.domain.Capability;
import io.github.susimsek.hateoasbackend.service.dto.CapabilityDto;
import io.github.susimsek.hateoasbackend.service.mapper.CapabilityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static io.github.susimsek.hateoasbackend.constant.CapabilityLinkConstants.CAPABILITIES;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CapabilityModelAssembler implements RepresentationModelAssembler<Capability, CapabilityDto> {

    final PagedResourcesAssembler<Capability> pagedResourcesAssembler;
    final CapabilityMapper capabilityMapper;

    @Override
    public CapabilityDto toModel(Capability entity) {
        CapabilityDto resource = capabilityMapper.toDto(entity);
        addLinks(resource);
        return resource;
    }

    @Override
    public CollectionModel<CapabilityDto> toCollectionModel(Iterable<? extends Capability> entities) {
        CollectionModel<CapabilityDto> resources = RepresentationModelAssembler.super.toCollectionModel(entities);
        addLinks(resources);
        return resources;
    }

    public PagedModel<CapabilityDto> toPagedModel(Page<Capability> page) {
        PagedModel<CapabilityDto> resources = pagedResourcesAssembler.toModel(page, this);
        addLinks(resources);
        return resources;
    }

    public void addLinks(CapabilityDto resource) {
        resource.add(linkTo(methodOn(CapabilityController.class).getCapability(resource.getId())).withSelfRel());
        resource.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel(CAPABILITIES));
    }

    public void addLinks(CollectionModel<CapabilityDto> resources) {
        resources.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel(CAPABILITIES));
    }
}