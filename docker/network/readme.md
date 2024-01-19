windows 安装Ubuntu;

`wsl --install Ubuntu`

查看所有子系统状态和version

`wsl -l -v`

设置默认为Ubuntu

`wsl -s Ubuntu`

进入特定子系统

`wsl -s Ubuntu`

docker 创建子网

`docker network create --subnet=172.20.0.0/24 mhy-net`

`docker run -d --net mhy-net --ip 172.20.0.10 -p 8888:80 --name nginx001 nginx:latest`

docker setting: Resources -> Network

_IP地址段 "192.168.65.0/24" 是一个CIDR（Classless Inter-Domain Routing）表示法，用于表示一个IP地址范围。在这个表示法中，"192.168.65.0" 是网络的起始IP地址，"/24" 表示网络中有24位用于标识主机的部分。这也意味着网络中有2^(32-24) = 256个IP地址。_

windows 添加路由表

路由表打印

`route print`

添加路由 _**没起作用 有可能是wsl 的问题**_

`route -p add 172.20.0.0 mask 255.255.255.0 192.168.65.2`

`route -p delete 172.20.0.0 mask 255.255.255.0 192.168.65.2`

`route -p add 172.20.0.0 mask 255.255.255.0 172.27.16.2`

`route -p delete 172.20.0.0 mask 255.255.255.0 172.27.16.2`

