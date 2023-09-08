### master连接slave
创建复制用户：在主服务器上创建一个用于复制的用户，并授予相应的权限

`CREATE USER 'replication_user'@'slave_ip' IDENTIFIED BY 'password';`

`GRANT REPLICATION SLAVE ON *.* TO 'replication_user'@'slave_ip';`

`FLUSH PRIVILEGES;`

`SHOW master status;`

### slave 执行命令

在从服务器上执行以下命令，将主服务器的数据复制到从服务器上

`STOP SLAVE;`

`CHANGE MASTER TO MASTER_HOST='master_ip', MASTER_USER='replication_user', MASTER_PASSWORD='password', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=12345;`

`START SLAVE;`

`SHOW slave status;`

### 连接其他服务器命令

`mysql -h master1 -uroot -proot`

`mysql -h master1 -uroot -proot -e "SHOW MASTER STATUS" > /var/lib/mysql/master_status.txt`

`./etc/mysql/conf.d/source/set-sysn-and-create-db-table.sh`
