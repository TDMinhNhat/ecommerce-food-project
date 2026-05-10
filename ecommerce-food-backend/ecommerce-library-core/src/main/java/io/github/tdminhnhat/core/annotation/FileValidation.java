package io.github.tdminhnhat.core.annotation;

import io.github.tdminhnhat.core.enums.FileTypeValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FileValidation {

    FileTypeValidation type() default FileTypeValidation.GENERAL;

    boolean nullable() default true;

    String message() default "File is not valid";
}
