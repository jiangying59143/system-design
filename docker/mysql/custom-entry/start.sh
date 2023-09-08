#!/bin/sh

cp /etc/mysql/conf.d/source/* /etc/mysql/conf.d/
# no use
#cp /etc/mysql/conf.d/source/set-sysn-and-create-db-table.sh /docker-entrypoint-initdb.d/set-sysn-and-create-db-table.sh

echo -e "\e[41m\e[44m ---------------- copy finished----------- \e[0m"
/entrypoint.sh mysqld
