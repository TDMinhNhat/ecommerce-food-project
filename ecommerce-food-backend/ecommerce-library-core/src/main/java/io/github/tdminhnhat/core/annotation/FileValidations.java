package io.github.tdminhnhat.core.annotation;

import io.github.tdminhnhat.core.enums.TypeResultValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FileValidations {

    /**
     * Define list of conditions for validating file input
     */
    FileValidation[] fileValidation() default {};

    /**
     * How the way that the field is validated and acceptable
     */
    TypeResultValidation typeResultValidation() default TypeResultValidation.ALL_VALIDATED;
}
