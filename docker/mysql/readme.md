### idea wsl docker mysql 错误
1. [ERROR] --initialize specified but the data directory has files in it. Aborting.

   删除临时文件
2. [ERROR] Could not set file permission for ca-key.pem

   镜像改为 mysql:5.7.16
3. Warning: World-writable config file is ignored

   添加如下代码
   ````
   entrypoint: >
      /bin/sh -c "chmod 0444 /etc/mysql/conf.d/my.cnf
      && docker-entrypoint.sh mysqld"
   ````
   
### idea wsl docker rabbitmq

1. file.sh not find

需要设置文件格式为 LF