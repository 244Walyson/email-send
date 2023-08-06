package com.waly.emailsend.dto;

import java.util.ArrayList;
import java.util.List;

public class CustonErrorValidation extends CustonError{

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
