package io.github.susimsek.hateoasbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Schema(name="Capability", description = "Capability")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "capabilities")
@Builder
public class CapabilityDto extends RepresentationModel<CapabilityDto> {

    @Schema(description = "Unique identifier of the Capability.", example = "1", required = true)
    Long id;

    @Schema(description = "Name of the Technology Stack.", example = "JavaEE", required = true)
    String techStack;

    @Schema(description = "Num of the Developers.", example = "20", required = true)
    Integer numOfDevelopers;

    @Schema(description = "Num of the Available Developers.", example = "10", required = true)
    Integer numOfAvailableDevelopers;
}
