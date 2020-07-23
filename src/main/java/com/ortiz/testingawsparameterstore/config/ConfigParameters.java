package com.ortiz.testingawsparameterstore.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ConfigParameters {
    private static final String REGION_DEFAULT = "us-east-1";

    public String getParameter(String parameter) {
        AWSCredentialsProvider credentials = InstanceProfileCredentialsProvider.getInstance();
        AWSSimpleSystemsManagement simpleSystemsManagementClient = (AWSSimpleSystemsManagement) ((AWSSimpleSystemsManagementClientBuilder) ((AWSSimpleSystemsManagementClientBuilder) AWSSimpleSystemsManagementClientBuilder.standard().withCredentials(credentials)).withRegion(REGION_DEFAULT)).build();
        GetParameterRequest parameterRequest = new GetParameterRequest();
        parameterRequest.withName(parameter).setWithDecryption(Boolean.valueOf(true));
        GetParameterResult parameterResult = simpleSystemsManagementClient.getParameter(parameterRequest);
        return parameterResult.getParameter().getValue();
    }

    public String getSecret(String secretName) {
        AWSCredentialsProvider credentials = InstanceProfileCredentialsProvider.getInstance();
        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withCredentials(credentials).withRegion(REGION_DEFAULT)
                .build();

        // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
        // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
        // We rethrow the exception by default.
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        // Decrypts secret using the associated KMS CMK.
        // Depending on whether the secret is a string or binary, one of these fields will be populated.
        if (getSecretValueResult.getSecretString() != null) {
            return getSecretValueResult.getSecretString();
        } else {
            return new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
        }
    }
}
