package com.ortiz.testingawsparameterstore.controllers;

import com.ortiz.testingawsparameterstore.config.ConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingController {

    @Autowired
    private ConfigParameters configParameters;

    @GetMapping("/parameters/{parameter_name}")
    public String getParameter(@PathVariable("parameter_name")  String parameter) {
        return configParameters.getParameter(parameter);
    }

    @GetMapping("/secrets/{secret_name}")
    public String getSecret(@PathVariable("secret_name")  String secretName) {
        return configParameters.getSecret(secretName);
    }
}
