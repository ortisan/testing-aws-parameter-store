package com.ortiz.testingawsparameterstore.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Value("${secret_param1}")
    String param1;
    @Value("${secret_param2}")
    String param2;

    @GetMapping("/test")
    public String getParameter() {
        return String.format("%s%s", param1, param2);
    }
}
