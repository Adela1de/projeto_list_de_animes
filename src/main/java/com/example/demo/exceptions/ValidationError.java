package com.example.demo.exceptions;

import com.example.demo.controllers.exceptions.StandardError;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ValidationError extends StandardError {

    private Set<FieldMessage> errors = new HashSet<>();

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public void addErrors(String fieldName, String message)
    {
        errors.add(new FieldMessage(fieldName, message));
    }
}
