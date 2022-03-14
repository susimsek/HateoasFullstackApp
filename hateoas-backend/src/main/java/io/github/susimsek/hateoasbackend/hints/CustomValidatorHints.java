package io.github.susimsek.hateoasbackend.hints;

import io.github.susimsek.hateoasbackend.validator.FieldsGreatnessValidator;
import io.github.susimsek.hateoasbackend.validator.annotation.FieldsGreatness;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.TypeHint;


@TypeHint(types = {
        FieldsGreatness.class,
        FieldsGreatnessValidator.class
})
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(name = "org.springframework.nativex.NativeListener")
public class CustomValidatorHints {
}


