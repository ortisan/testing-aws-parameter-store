#!/bin/bash
set -x
# Create parameter
awslocal ssm put-parameter --name "my-string" --type String --value "Hello World Read Parameter" --overwrite
set +x