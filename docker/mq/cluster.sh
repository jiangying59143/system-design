rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl join_cluster rabbit1@rabbit1
rabbitmqctl start_app
echo "setup cluster end"