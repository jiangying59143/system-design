--master 设置salve

--为从库服务器 设置用户名和密码（表明从服务器的ip可以使%,账号为slave 密码slave-pass
CREATE USER 'slave1'@'172.1.0.3' IDENTIFIED BY 'slave1-pass';
grant replication slave, replication client on *.* to 'slave1'@'172.1.0.3';--设置权限
flush privileges;--权限生效

show grants for 'slave1'@'172.1.0.3';
show master status;


--slave 设置 master

--MASTER_LOG_FILE get value from "show master status"
--MASTER_LOG_POS get value from "show master status"
change master to
master_host='172.1.0.2',
master_user='slave1',
master_password='slave1-pass',
MASTER_LOG_FILE='mysql-bin.000001',
MASTER_LOG_POS=787;

start slave;
show slave status;
