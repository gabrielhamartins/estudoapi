package com.gft.estudoapi.exceptions.details;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FieldAlreadyExistsExceptionDetails extends ExceptionDetails{

    private String field;

}
