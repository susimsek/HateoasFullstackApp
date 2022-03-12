package io.github.susimsek.hateoasbackend.request;

import io.github.susimsek.hateoasbackend.validator.annotation.FieldsGreatness;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@FieldsGreatness(firstFieldName = "numOfDevelopers",
        secondFieldName = "numOfAvailableDevelopers",
        message = "{error.constraints.FieldsGreatness.message}")
public record CapabilityUpdateRequest(
        @Schema(description = "Name of the Technology Stack.", example = "JavaEE", required = true)
        @NotBlank(message = "{error.constraints.techStack.NotBlank.message}")
        @Size(min = 1, max = 255)
        String techStack,

        @Schema(description = "Num of the Developers.", example = "20", required = false)
        @PositiveOrZero
        Integer numOfDevelopers,

        @Schema(description = "Num of the Available Developers.", example = "10", required = false)
        @PositiveOrZero
        Integer numOfAvailableDevelopers){}
