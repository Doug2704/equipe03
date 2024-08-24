package com.codigocerto.backend.exceptions;

import lombok.Data;

@Data
public class FieldMessage {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}