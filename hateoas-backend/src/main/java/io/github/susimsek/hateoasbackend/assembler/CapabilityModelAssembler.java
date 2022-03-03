package io.github.susimsek.hateoasbackend.assembler;

import io.github.susimsek.hateoasbackend.controller.CapabilityController;
import io.github.susimsek.hateoasbackend.dto.CapabilityDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CapabilityModelAssembler implements SimpleRepresentationModelAssembler<CapabilityDto> {

    final PagedResourcesAssembler<CapabilityDto> pagedResourcesAssembler;

    @Override
    public void addLinks(CollectionModel<EntityModel<CapabilityDto>> resources) {
        resources.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withSelfRel());
        resources.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("capabilities"));
    }

    @Override
    public void addLinks(EntityModel<CapabilityDto> resource) {
        resource.add(linkTo(methodOn(CapabilityController.class).getCapability(resource.getContent().getId())).withSelfRel());
        resource.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("capabilities"));
    }

    public PagedModel<EntityModel<CapabilityDto>> toPagedModel(Page<CapabilityDto> page) {
        PagedModel<EntityModel<CapabilityDto>> resources = pagedResourcesAssembler.toModel(page, this);
        resources.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("capabilities"));
        return resources;
    }
}