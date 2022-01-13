# Poc for testing SSM and Secret Manager

## How to:
1. Create secret:
   ```sh
   aws secretsmanager create-secret --name /applications/test-secret \
   --description "My test secret for uses into spring boot" \
   --secret-string file://example.json
   ```
1. Upload image to ECR
   ```sh
   aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 779882487479.dkr.ecr.us-east-1.amazonaws.com
   aws ecr create-repository --repository-name test-param-secret || true
   docker build -t 779882487479.dkr.ecr.us-east-1.amazonaws.com/test-param-secret:latest .
   docker push 779882487479.dkr.ecr.us-east-1.amazonaws.com/test-param-secret:latest
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