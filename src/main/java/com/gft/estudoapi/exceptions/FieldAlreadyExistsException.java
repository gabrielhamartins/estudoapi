package com.gft.estudoapi.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
public class FieldAlreadyExistsException extends RuntimeException{

    private String field;

    public FieldAlreadyExistsException(String field, String message){

        super(message);
        this.field=field;

    }

}
