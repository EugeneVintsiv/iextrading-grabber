package io.github.eugenevintsiv.iextradinggrabber.web.rest.validator.constraint;

import io.github.eugenevintsiv.iextradinggrabber.web.rest.validator.annotation.EnumValidator;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, String> {

    private List<String> valueList = new ArrayList<>();
    private String messageTpl;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) return false;
        if (!valueList.contains(value.toUpperCase())) {
            context.disableDefaultConstraintViolation();
            final String messageTpl = this.messageTpl + ". Available values: " + valueList;
            context.buildConstraintViolationWithTemplate(messageTpl).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();
        messageTpl = constraintAnnotation.message();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes")
                Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }
    }
}