#!/bin/bash

#set -x

while ! mysqladmin --defaults-extra-file=/root/master.cnf ping -h master --silent; do
echo "master 未就绪，等待中..."
    sleep 1
done

while ! mysqladmin --defaults-extra-file=/root/slave0.cnf ping -h slave0 --silent; do
    echo "slave0 未就绪，等待中..."
    sleep 2
done

while ! mysqladmin --defaults-extra-file=/root/slave1.cnf ping -h slave1 --silent; do
    echo "slave1 未就绪，等待中..."
    sleep 2
done

echo -e "---------------- set-sysn-and-create-db-table.sh start ----------- "
echo "# 配置文件"
ls /root
#chmod 0444 /root/master.cnf /root/slave0.cnf /root/slave1.cnf
echo -e "\n# 设置用户"
mysql --defaults-extra-file=/root/master.cnf <<EOF
DROP USER IF EXISTS 'slave'@'%';
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
grant replication slave, replication client on *.* to 'slave'@'%';
#DROP USER IF EXISTS 'canal'@'%';
#CREATE USER 'canal'@'%' IDENTIFIED BY '123456';
#grant select, replication slave, replication client on *.* to 'canal'@'%';
flush privileges;
SHOW GRANTS FOR 'slave'@'%';
#SHOW GRANTS FOR 'canal'@'%';
EOF

master_status=$(mysql --defaults-extra-file=/root/master.cnf -e "SHOW MASTER STATUS;")
echo "# 使用 awk 分割文件内容并获取每列的值 从第二行开始"
binlog_file=$(echo "$master_status" | awk 'NR > 1 {print $1}')
binlog_position=$(echo "$master_status" | awk 'NR > 1 {print $2}')
binlog_type=$(echo "$master_status" | awk 'NR > 1 {print $3}')
echo "# 打印每列的值
Binlog File: $binlog_file
Binlog Position: $binlog_position
Binlog Type: $binlog_type"


echo -e "\n# slave0 配置"
mysql --defaults-extra-file=/root/slave0.cnf <<EOF
stop slave;
change master to
master_host='master',
master_user='slave',
master_password='123456',
MASTER_LOG_FILE='$binlog_file',
MASTER_LOG_POS=$binlog_position,
MASTER_PORT=3306;
start slave;
show slave status;
EOF
echo -e "\n# slave1 配置"
mysql --defaults-extra-file=/root/slave1.cnf <<EOF
stop slave;
change master to
master_host='master',
master_user='slave',
master_password='123456',
MASTER_LOG_FILE='$binlog_file',
MASTER_LOG_POS=$binlog_position,
MASTER_PORT=3306;
start slave;
show slave status;
EOF
echo -e "\n# 创建数据库和表测试"
mysql --defaults-extra-file=/root/master.cnf <<EOF
drop database if exists db0;
drop database if exists db1;
CREATE DATABASE db0;

create table db0.t_user
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    age  int default 0 not null
);

create table db0.t_order0
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    user_id bigint
);
create table db0.t_order1
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    user_id bigint
);

create table db0.t_order_item0
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    order_id bigint,
    user_id bigint
);
create table db0.t_order_item1
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    order_id bigint,
    user_id bigint
);

CREATE DATABASE db1;
create table db1.t_user
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    age  int default 0 not null
);

create table db1.t_order0
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    user_id bigint
);
create table db1.t_order1
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    user_id bigint
);

create table db1.t_order_item0
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    order_id bigint,
    user_id bigint
);
create table db1.t_order_item1
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    order_id bigint,
    user_id bigint
);
EOF

echo -e "\n# 查看slave0同步情况"
echo $(mysql --defaults-extra-file=/root/slave0.cnf -e "SELECT schema_name
                                                        FROM information_schema.schemata
                                                        WHERE schema_name LIKE 'db%';")

echo -e "\n# 查看slave1同步情况"
echo $(mysql --defaults-extra-file=/root/slave1.cnf -e "SELECT schema_name
                                                        FROM information_schema.schemata
                                                        WHERE schema_name LIKE 'db%';")

echo -e "---------------- set-sysn-and-create-db-table.sh end ----------- "
