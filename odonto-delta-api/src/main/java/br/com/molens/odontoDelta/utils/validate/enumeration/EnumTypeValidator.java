package br.com.molens.odontoDelta.utils.validate.enumeration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumTypeValidator implements ConstraintValidator<EnumType, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumType annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        acceptedValues
                .stream()
                .filter(enumValue -> enumValue.equals(valor.toUpperCase())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(constraintValidatorContext.getDefaultConstraintMessageTemplate() + " Os valores aceitos são: " + acceptedValues));
        return true;
    }

}
