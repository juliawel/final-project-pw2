package com.ecommerce.demo.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidantionError extends CustomError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidantionError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String fieldMessage) {
        errors.removeIf(x -> x.getFieldName().equals(fieldMessage));
        errors.add(new FieldMessage(fieldName, fieldMessage));
    }
}
