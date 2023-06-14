package br.com.molens.odontoDelta.utils.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern(regexp="^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "{br.com.molens.odontoDelta.utils.validate.DateFormat.message}")
public @interface DateFormat {

    String message() default "{br.com.molens.odontoDelta.utils.validate.DateFormat.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
