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

import java.util.Optional;

import static io.github.susimsek.hateoasbackend.constant.CapabilityLinkConstants.CAPABILITIES;
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
        resources.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel(CAPABILITIES));
    }

    @Override
    public void addLinks(EntityModel<CapabilityDto> resource) {
        var contentOptional = Optional.ofNullable(resource.getContent());
        contentOptional.ifPresent(content -> resource.add(linkTo(methodOn(CapabilityController.class).getCapability(resource.getContent().getId())).withSelfRel()));
        resource.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel(CAPABILITIES));
    }

    public PagedModel<EntityModel<CapabilityDto>> toPagedModel(Page<CapabilityDto> page) {
        PagedModel<EntityModel<CapabilityDto>> resources = pagedResourcesAssembler.toModel(page, this);
        resources.add(linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel(CAPABILITIES));
        return resources;
    }
}