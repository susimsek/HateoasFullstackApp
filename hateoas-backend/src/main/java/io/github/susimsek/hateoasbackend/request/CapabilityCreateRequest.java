package io.github.susimsek.hateoasbackend.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;

public record CapabilityCreateRequest(
        @Schema(description = "Name of the Technology Stack.", example = "JavaEE", required = true)
        @NotBlank(message = "{error.constraints.techStack.NotBlank.message}")
        String techStack,

        @Schema(description = "Num of the Developers.", example = "20", required = false)
        Integer numOfDevelopers,

        @Schema(description = "Num of the Available Developers.", example = "10", required = false)
        Integer numOfAvailableDevelopers){}
