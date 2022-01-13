#!/bin/bash
set -x
# Create parameter
awslocal secretsmanager create-secret --name /applications/test-secret \
   --description "My test secret for uses into spring boot" \
   --secret-string "{\"secret_param1\":\"Hello\",\"secret_param2\":\"World\"}"
set +x