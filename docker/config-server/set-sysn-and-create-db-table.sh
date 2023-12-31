#!/bin/bash
echo -e "\e[41m\e[44m ---------------- set-sysn-and-create-db-table.sh start ----------- \e[0m"

while true; do
  if mysqladmin ping -h master1 -u root -proot &> /dev/null; then
    echo "MySQL is running"
    break  # 如果MySQL已启动，退出循环
  else
    echo "MySQL is not running. Waiting for 5 seconds..."
    sleep 5  # 如果MySQL未启动，等待5秒钟
  fi
done

echo "# 设置用户 slave1"
mysql -h master1 -uroot -proot -e "CREATE USER 'master1-slave1'@'%' IDENTIFIED BY 'master1-slave1'";
mysql -h master1 -uroot -proot -e "grant replication slave, replication client on *.* to 'master1-slave1'@'%';";
echo "# 设置用户 slave2"
mysql -h master1 -uroot -proot -e "CREATE USER 'master1-slave2'@'%' IDENTIFIED BY 'master1-slave2';"
mysql -h master1 -uroot -proot -e "grant replication slave, replication client on *.* to 'master1-slave2'@'%';"
echo "# 设置用户 canal"
mysql -h master1 -uroot -proot -e "CREATE USER 'canal'@'%' IDENTIFIED BY 'canal'";
mysql -h master1 -uroot -proot -e "grant select, replication slave, replication client on *.* to 'canal'@'%';";
mysql -h master1 -uroot -proot -e "flush privileges;"
mysql -h master1 -uroot -proot -e "SHOW GRANTS FOR 'master1-slave1'@'%';";
mysql -h master1 -uroot -proot -e "SHOW GRANTS FOR 'master1-slave2'@'%';";
mysql -h master1 -uroot -proot -e "SHOW GRANTS FOR 'canal'@'%';";

echo "# 定义master_status.txt路径"
master_status_path='/root/master_status.txt'
echo "$master_status_path"
mysql -h master1 -uroot -proot -e "SHOW MASTER STATUS">"$master_status_path"
chmod 700 "$master_status_path"
echo "# 读取 master_status.txt 文件并将其内容保存到变量中"
file_contents=$(cat $master_status_path)
echo "# 删除文件"
rm "$master_status_path"
echo "# 使用 awk 分割文件内容并获取每列的值 从第二行开始"
binlog_file=$(echo "$file_contents" | awk 'NR > 1 {print $1}')
binlog_position=$(echo "$file_contents" | awk 'NR > 1 {print $2}')
binlog_type=$(echo "$file_contents" | awk 'NR > 1 {print $3}')
echo "# 打印每列的值"
echo "Binlog File: $binlog_file"
echo "Binlog Position: $binlog_position"
echo "Binlog Type: $binlog_type"
echo "# 停止从服务器的复制进程"
mysql -h master1-slave1 -uroot -proot -e "stop slave;"
echo "# 更改主服务器配置，使用脚本中获取的值"
mysql -h master1-slave1 -uroot -proot <<EOF
change master to
master_host='master1',
master_user='master1-slave1',
master_password='master1-slave1',
MASTER_LOG_FILE='$binlog_file',
MASTER_LOG_POS=$binlog_position;
EOF
echo "# 重新启动从服务器的复制进程"
mysql -h master1-slave1 -uroot -proot -e "start slave;"
echo "# 停止从服务器的复制进程"
mysql -h master1-slave2 -uroot -proot -e "stop slave;"
echo "# 更改主服务器配置，使用脚本中获取的值"
mysql -h master1-slave2 -uroot -proot <<EOF
change master to
master_host='master1',
master_user='master1-slave2',
master_password='master1-slave2',
MASTER_LOG_FILE='$binlog_file',
MASTER_LOG_POS=$binlog_position;
EOF
echo "# 重新启动从服务器的复制进程"
mysql -h master1-slave2 -uroot -proot -e "start slave;"
mysql -h master1-slave1 -uroot -proot -e "show slave status;"
mysql -h master1-slave2 -uroot -proot -e "show slave status;"
echo "# 创建数据库和表"
mysql -h master1 -uroot -proot -e "drop database if exists db0;";
mysql -h master1 -uroot -proot -e "drop database if exists db1;";
mysql -h master1 -uroot -proot -e "CREATE DATABASE db0";
mysql -h master1 -uroot -proot <<EOF
create table db0.t_user
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    age  int default 0 not null,
    constraint table_name_name_uindex
        unique (name)
);
EOF
mysql -h master1 -uroot -proot -e "CREATE DATABASE db1";
mysql -h master1 -uroot -proot <<EOF
create table db1.t_user
(
    id   bigint        not null
        primary key,
    name varchar(50)   null,
    age  int default 0 not null,
    constraint table_name_name_uindex
        unique (name)
);
EOF
echo "# 创建数据库和表 完成 O(∩_∩)O"

echo -e "\e[41m\e[44m ---------------- set-sysn-and-create-db-table.sh end ----------- \e[0m"
