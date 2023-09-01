--master

--为从库服务器 设置用户名和密码（表明从服务器的ip可以使%,账号为slave 密码slave-pass
CREATE USER 'slave'@'%' IDENTIFIED BY 'slave-pass';
grant replication slave, replication client on *.* to 'slave'@'%';--设置权限
flush privileges;--权限生效

show grants for 'slave'@'%';
show master status;


--slave
change master to
master_host='172.1.0.2',
master_user='slave',
master_password='slave-pass',
MASTER_LOG_FILE='mysql-bin.000003', --get result from "show master status"
MASTER_LOG_POS=885; --get result from "show master status"

start slave;
show slave status;
