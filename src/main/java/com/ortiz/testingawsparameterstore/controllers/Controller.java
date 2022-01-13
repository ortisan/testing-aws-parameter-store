package com.ortiz.testingawsparameterstore.controllers;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Optional;

import static com.ortiz.testingawsparameterstore.Constants.PARAMETER_STORE_NAME;

@RestController
public class Controller {
    @Value("${secret_param1}")
    String param1;
    @Value("${secret_param2}")
    String param2;

    @Autowired
    AWSSimpleSystemsManagement ssmClient;

    @GetMapping("/test")
    public String getParameter() {
        return String.format("%s%s", param1, param2);
    }

    @GetMapping("/parameter-ad-hoc")
    public String getParameterAdHoc() {
        String parameterName = System.getenv(PARAMETER_STORE_NAME);
        return getParameters(parameterName);
    }

    private String getParameters(String parameterName) {
        GetParametersRequest request = new GetParametersRequest();
        request.withNames(parameterName).setWithDecryption(true);

        GetParametersResult parameters = this.ssmClient.getParameters(request);
        Optional<Parameter> parameter = parameters.getParameters().stream().findFirst();
        return parameter.get().getValue();
    }
}
