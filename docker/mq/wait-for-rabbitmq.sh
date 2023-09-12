#!/bin/bash
max_retries=20
retry_interval=5
retries=0

while [ $retries -lt $max_retries ]; do
    nc -zv localhost 5672
    result=$?

    if [ $result -eq 0 ]; then
        echo "RabbitMQ is running!"
        # 在这里执行你想要的操作
        break
    else
        echo "RabbitMQ is not running. Retry in $retry_interval seconds..."
        sleep $retry_interval
        retries=$((retries + 1))
    fi
done