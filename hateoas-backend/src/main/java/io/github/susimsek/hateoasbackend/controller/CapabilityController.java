package io.github.susimsek.hateoasbackend.controller;

import io.github.susimsek.hateoasbackend.assembler.CapabilityModelAssembler;
import io.github.susimsek.hateoasbackend.dto.CapabilityDto;
import io.github.susimsek.hateoasbackend.request.CapabilityCreateRequest;
import io.github.susimsek.hateoasbackend.request.CapabilityUpdateRequest;
import io.github.susimsek.hateoasbackend.services.CapabilityService;
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
import org.springframework.hateoas.EntityModel;
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
    final CapabilityModelAssembler assembler;

    @Operation(summary = "Add a new capability", description = "Add a new capability", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping(path = "/capabilities",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<CapabilityDto>> newCapability(@Valid @RequestBody CapabilityCreateRequest capability) throws URISyntaxException {
        CapabilityDto savedCapability = capabilityService.saveCapability(capability);
        EntityModel<CapabilityDto> capabilityResource =  assembler.toModel(savedCapability);
        return ResponseEntity
                .created(new URI(capabilityResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                .body(capabilityResource);
    }

    @Operation(summary = "Find capability by ID", description = "Returns a single capability", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Capability not found", content = @Content) })
    @GetMapping(path = "/capabilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CapabilityDto>> getCapability(@PathVariable Long id) {
        CapabilityDto capability = capabilityService.getCapability(id);
        return ResponseEntity.ok(assembler.toModel(capability));
    }

    @Operation(summary = "Get all Capabilities with Pagination", description = "Get all Capabilities with Pagination", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(path = "/paged-capabilities",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<CapabilityDto>>> getAllCapabilitiesWithPagination(@ParameterObject Pageable pageable) {

        Page<CapabilityDto> capabilities = capabilityService.getAllCapabilitiesWithPagination(pageable);
        return ResponseEntity.ok(assembler.toPagedModel(capabilities));
    }

    @Operation(summary = "Get all Capabilities", description = "Get all Capabilities", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(path = "/capabilities",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<CapabilityDto>>> getAllCapabilities() {
        List<CapabilityDto> capabilities = capabilityService.getAllCapabilities();
        return ResponseEntity.ok(assembler.toCollectionModel(capabilities));
    }

    @Operation(summary = "Update an existing capability", description = "Update an existing capability by Id", tags = { "capability" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Capability not found", content = @Content)})
    @PutMapping(path = "/capabilities/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CapabilityDto>> updateCapability(@Valid @RequestBody CapabilityUpdateRequest capability,
                                                                      @PathVariable long id) {
        CapabilityDto updatedCapability = capabilityService.updateCapability(id, capability);
        return ResponseEntity.ok(assembler.toModel(updatedCapability));
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
