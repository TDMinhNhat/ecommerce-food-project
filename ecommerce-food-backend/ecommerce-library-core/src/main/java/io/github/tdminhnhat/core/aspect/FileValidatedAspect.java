package io.github.tdminhnhat.core.aspect;

import io.github.tdminhnhat.core.annotation.FileValidation;
import io.github.tdminhnhat.core.enums.FileTypeValidation;
import io.github.tdminhnhat.core.exception.FileValidationException;
import io.github.tdminhnhat.core.util.FileValidationUtil;
import org.apache.coyote.BadRequestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.FieldSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Aspect
@Component
public class FileValidatedAspect {

    @Pointcut("@annotation(io.github.tdminhnhat.core.annotation.FileValidations)")
    private void fileValidationsPointcut() {}

    @Pointcut("@annotation(io.github.tdminhnhat.core.annotation.FileValidation)")
    private void fileValidationPointcut() {}

    @Before("fileValidationsPointcut()")
    public void beforeFileValidations(JoinPoint joinPoint) {
        FieldSignature fieldSignature = (FieldSignature) joinPoint.getSignature();
        FileValidation fileValidation = fieldSignature.getField().getAnnotation(FileValidation.class);

        List<MultipartFile> files = (List<MultipartFile>) fieldSignature.getFieldType().arrayType().cast(List.class);
        files.forEach(file -> this.handleValidation(file, fileValidation));
    }

    @Before("fileValidationPointcut()")
    public void beforeFileValidation(JoinPoint joinPoint) throws IllegalAccessException {
        FieldSignature fieldSignature = (FieldSignature) joinPoint.getSignature();
        FileValidation fileValidation = fieldSignature.getField().getAnnotation(FileValidation.class);

        MultipartFile file = (MultipartFile) fieldSignature.getField().get(MultipartFile.class);
        handleValidation(file, fileValidation);
    }

    private void handleValidation(MultipartFile file, FileValidation fileValidation) {
        if(!FileValidationUtil.validate(file, fileValidation.type())) {
            throw new FileValidationException(fileValidation.message());
        }
    }
}
