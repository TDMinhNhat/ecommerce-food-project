package io.github.tdminhnhat.core.enums;

public enum TypeResultValidation {

    // All the validations for something has to meet all requirements
    ALL_VALIDATED,

    // Minimum one validation is valid
    MINIMUM_ONE_VALIDATED,

    // Minimum the number of validation is valid for the request defined
    MINIMUM_FOR_REQUEST
}
