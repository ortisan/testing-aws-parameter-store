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

#### Class that decids where pick auth data
io.awspring.cloud.autoconfigure.context.ContextCredentialsAutoConfiguration

#### Chain of AWS auth providers
com.amazonaws.auth.DefaultAWSCredentialsProviderChain.DefaultAWSCredentialsProviderChain