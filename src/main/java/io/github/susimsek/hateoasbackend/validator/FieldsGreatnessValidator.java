package io.github.susimsek.hateoasbackend.validator;

import io.github.susimsek.hateoasbackend.validator.annotation.FieldsGreatness;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsGreatnessValidator implements ConstraintValidator<FieldsGreatness, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldsGreatness constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstFieldName();
        secondFieldName = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Object firstField = new BeanWrapperImpl(value)
                .getPropertyValue(firstFieldName);
        Object secondField = new BeanWrapperImpl(value)
                .getPropertyValue(secondFieldName);

        if (firstField instanceof Integer first && secondField instanceof Integer second
                && first < second ) {
            addPropertyNode(context, firstFieldName);
            return false;
        }
        if (firstField instanceof Long first && secondField instanceof Long second
                && first < second ) {
            addPropertyNode(context, firstFieldName);
            return false;
        }

        return true;
    }

    private void addPropertyNode(ConstraintValidatorContext context, String fieldName) {
        context.buildConstraintViolationWithTemplate(
                context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(fieldName).addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
