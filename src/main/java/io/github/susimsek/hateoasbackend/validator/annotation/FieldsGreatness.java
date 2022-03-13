package io.github.susimsek.hateoasbackend.validator.annotation;

import io.github.susimsek.hateoasbackend.validator.FieldsGreatnessValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FieldsGreatnessValidator.class })
public @interface FieldsGreatness {

    String message() default "{firstFieldName} must be greater than {secondFieldName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String firstFieldName();
    String secondFieldName();

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    public @interface List {
        FieldsGreatness[] value();
    }
}