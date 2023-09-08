#!/bin/bash
# 设置用户 slave1
mysql -h master1 -uroot -proot -e "CREATE USER 'master1-slave1'@'172.18.0.3' IDENTIFIED BY 'master1-slave1'";
mysql -h master1 -uroot -proot -e "grant replication slave, replication client on *.* to 'master1-slave1'@'172.18.0.3';";
# 设置用户 slave2
mysql -h master1 -uroot -proot -e "CREATE USER 'master1-slave2'@'172.18.0.4' IDENTIFIED BY 'master1-slave2';"
mysql -h master1 -uroot -proot -e "grant replication slave, replication client on *.* to 'master1-slave2'@'172.18.0.4';"
mysql -h master1 -uroot -proot -e "flush privileges;"
# 定义master_status.txt路径
master_status_path='/etc/mysql/conf.d/source/master_status.txt'
echo "$master_status_path"
mysql -h master1 -uroot -proot -e "SHOW MASTER STATUS">"$master_status_path"
chmod 700 "$master_status_path"
# 读取 master_status.txt 文件并将其内容保存到变量中
file_contents=$(cat $master_status_path)
# 删除文件
rm "$master_status_path"
# 使用 awk 分割文件内容并获取每列的值 从第二行开始
binlog_file=$(echo "$file_contents" | awk 'NR > 1 {print $1}')
binlog_position=$(echo "$file_contents" | awk 'NR > 1 {print $2}')
binlog_type=$(echo "$file_contents" | awk 'NR > 1 {print $3}')
# 打印每列的值
echo "Binlog File: $binlog_file"
echo "Binlog Position: $binlog_position"
echo "Binlog Type: $binlog_type"
# 停止从服务器的复制进程
mysql -h master1-slave1 -uroot -proot -e "stop slave;"
# 更改主服务器配置，使用脚本中获取的值
mysql -h master1-slave1 -uroot -proot <<EOF
change master to
master_host='172.18.0.2',
master_user='master1-slave1',
master_password='master1-slave1',
MASTER_LOG_FILE='$binlog_file',
MASTER_LOG_POS=$binlog_position;
EOF
# 重新启动从服务器的复制进程
mysql -h master1-slave1 -uroot -proot -e "start slave;"
# 停止从服务器的复制进程
mysql -h master1-slave2 -uroot -proot -e "stop slave;"
# 更改主服务器配置，使用脚本中获取的值
mysql -h master1-slave2 -uroot -proot <<EOF
change master to
master_host='172.18.0.2',
master_user='master1-slave2',
master_password='master1-slave2',
MASTER_LOG_FILE='$binlog_file',
MASTER_LOG_POS=$binlog_position;
EOF
# 重新启动从服务器的复制进程
mysql -h master1-slave2 -uroot -proot -e "start slave;"