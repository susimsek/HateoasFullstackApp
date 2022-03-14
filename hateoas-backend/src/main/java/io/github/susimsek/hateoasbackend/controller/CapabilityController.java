package io.github.susimsek.hateoasbackend.controller;

import io.github.susimsek.hateoasbackend.domain.Capability;
import io.github.susimsek.hateoasbackend.controller.assembler.CapabilityModelAssembler;
import io.github.susimsek.hateoasbackend.service.dto.CapabilityDto;
import io.github.susimsek.hateoasbackend.service.request.CapabilityCreateRequest;
import io.github.susimsek.hateoasbackend.service.request.CapabilityUpdateRequest;
import io.github.susimsek.hateoasbackend.service.CapabilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "capability", description = "the Capability API")
public class CapabilityController {

    final CapabilityService capabilityService;
    final CapabilityModelAssembler capabilityModelAssembler;

    @Operation(summary = "Add a new capability", description = "Add a new capability", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping(path = "/capabilities",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CapabilityDto> newCapability(@Valid @RequestBody CapabilityCreateRequest capability) throws URISyntaxException {
        Capability entity = capabilityService.saveCapability(capability);
        CapabilityDto resource = capabilityModelAssembler.toModel(entity);
        return ResponseEntity
                .created(new URI(resource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                .body(resource);
    }

    @Operation(summary = "Find capability by ID", description = "Returns a single capability", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Capability not found", content = @Content) })
    @GetMapping(path = "/capabilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CapabilityDto> getCapability(@PathVariable Long id) {
        Capability entity = capabilityService.getCapability(id);
        return ResponseEntity.ok(capabilityModelAssembler.toModel(entity));
    }

    @Operation(summary = "Get all Capabilities with Pagination", description = "Get all Capabilities with Pagination", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(path = "/paged-capabilities",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<CapabilityDto>> getAllCapabilitiesWithPagination(@ParameterObject Pageable pageable) {
        Page<Capability> page = capabilityService.getAllCapabilitiesWithPagination(pageable);
        return ResponseEntity.ok(capabilityModelAssembler.toPagedModel(page));
    }

    @Operation(summary = "Get all Capabilities", description = "Get all Capabilities", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(path = "/capabilities",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CapabilityDto>> getAllCapabilities() {
        List<Capability> entities = capabilityService.getAllCapabilities();
        return ResponseEntity.ok(capabilityModelAssembler.toCollectionModel(entities));
    }

    @Operation(summary = "Update an existing capability", description = "Update an existing capability by Id", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Capability not found", content = @Content)})
    @PutMapping(path = "/capabilities/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CapabilityDto> updateCapability(@Valid @RequestBody CapabilityUpdateRequest capability,
                                                                      @PathVariable long id) {
        Capability entity = capabilityService.updateCapability(id, capability);
        return ResponseEntity.ok(capabilityModelAssembler.toModel(entity));
    }

    @Operation(summary = "Delete a capability", description = "", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Capability not found", content = @Content)})
    @DeleteMapping(path = "/capabilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCapability(@PathVariable Long id) {
        capabilityService.deleteCapability(id);
        return ResponseEntity.noContent().build();
    }
}
