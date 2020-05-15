package com.trelloiii.sweater.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class UtilsController {
    static void getErrors(BindingResult bindingResult, Model model) {
        Map<String,String> errors=bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                err->err.getField()+"Error",
                FieldError::getDefaultMessage
        ));
        System.out.println(errors);
        model.mergeAttributes(errors);
    }
}
