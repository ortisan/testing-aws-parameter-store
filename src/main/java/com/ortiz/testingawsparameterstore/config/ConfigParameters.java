package com.ortiz.testingawsparameterstore.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import org.springframework.stereotype.Component;

@Component
public class ConfigParameters {
    public String getParameter(String parameter) {
        AWSCredentialsProvider credentials = InstanceProfileCredentialsProvider.getInstance();
        AWSSimpleSystemsManagement simpleSystemsManagementClient = (AWSSimpleSystemsManagement)((AWSSimpleSystemsManagementClientBuilder)((AWSSimpleSystemsManagementClientBuilder)AWSSimpleSystemsManagementClientBuilder.standard().withCredentials(credentials)).withRegion("us-east-1")).build();
        GetParameterRequest parameterRequest = new GetParameterRequest();
        parameterRequest.withName(parameter).setWithDecryption(Boolean.valueOf(true));
        GetParameterResult parameterResult = simpleSystemsManagementClient.getParameter(parameterRequest);
        return parameterResult.getParameter().getValue();
    }
    public String getSecret(String secretName) {
        AWSCredentialsProvider credentials = InstanceProfileCredentialsProvider.getInstance();
        AWSSimpleSystemsManagement simpleSystemsManagementClient = (AWSSimpleSystemsManagement)((AWSSimpleSystemsManagementClientBuilder)((AWSSimpleSystemsManagementClientBuilder)AWSSimpleSystemsManagementClientBuilder.standard().withCredentials(credentials)).withRegion("us-east-1")).build();
        GetParameterRequest parameterRequest = new GetParameterRequest();
        parameterRequest.withName(secretName).setWithDecryption(Boolean.valueOf(true));
        GetParameterResult parameterResult = simpleSystemsManagementClient.getParameter(parameterRequest);
        return parameterResult.getParameter().getValue();
    }
}
