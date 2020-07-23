# Poc for testing SSM and Secret Manager

How to:
1. Create the parameter store and secret manager on aws (test and password_test resp.) ;
1. Upload this jar on aws ecs or ec2 with git clone;
1. Build the project:
    ```sh
    mvn clean package
    ```
1. Run the jar - obs: this application starts on 5656:
    ```sh
    java -jar testing-aws-parameter-store-0.0.1-SNAPSHOT.jar
    ```
1. Make a curl in routes **/parameters/{parameter_name}** or **/secrets/secret_name**
```sh
curl --location --request GET 'localhost:5656/parameters/teste' --header 'Content-Type: application/json'
curl --location --request GET 'localhost:5656/secrets/password_teste' --header 'Content-Type: application/json'