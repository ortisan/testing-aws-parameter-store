# Poc for testing SSM and Secret Manager

## How to:
1. Create secret:
   ```sh
   aws secretsmanager create-secret --name /applications/test-secret \
   --description "My test secret for uses into spring boot" \
   --secret-string file://example.json
   ```
1. Make a curl in route **/test**
```sh
curl --location --request GET 'localhost:8080/test' --header 'Content-Type: application/json'
```

### Useful Info:

#### Class that decides where pick auth data
[io.awspring.cloud.autoconfigure.context.ContextCredentialsAutoConfiguration](https://docs.awspring.io/spring-cloud-aws/docs/2.3.1-SNAPSHOT/apidocs/index.html?io/awspring/cloud/autoconfigure/context/ContextCredentialsAutoConfiguration.html)

#### Chain of AWS auth providers
[com.amazonaws.auth.DefaultAWSCredentialsProviderChain.DefaultAWSCredentialsProviderChain](https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/DefaultAWSCredentialsProviderChain.html)