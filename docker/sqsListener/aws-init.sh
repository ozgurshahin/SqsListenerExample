#!/bin/bash
set -x
awslocal sqs create-queue --queue-name SqsListener.fifo --attributes '{"FifoQueue": "true"}'
set +x
