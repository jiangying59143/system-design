###docker 命令

根据 **Dockerfile** build 出一个一个定制化的 image

`docker build -t mysql-5.7 .`

列出所有images

`docker images`

移除image

`docker image rm xxx`

###docker-compose命令
重新启动已经定义在docker-compose.yml文件中的所有服务，并在后台运行它们

`docker-compose up -d`

如果你只想重新启动某个特定的服务，而不是所有服务，可以使用以下命令：

_"xxx uses an image, skipping" 这个信息通常出现在Docker Compose的输出中，表示某个容器在启动时已经存在并且已经使用了指定的镜像，所以Docker Compose 跳过了拉取新镜像的步骤，而直接使用已有的镜像来启动容器_

`docker-compose up -d <service_name>`

如果你需要重新构建容器，以便更新镜像或配置，可以使用docker-compose build命令

`docker-compose build`

如果你需要停止和删除Docker Compose中的容器

_**如果想在docker-compose.xml修改的密码生效 需要删除挂在文件**_

`docker-compose down`

### network

`docker network ls`

网络模式	简介

Host	容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口。

Bridge	此模式会为每一个容器分配、设置IP等，并将容器连接到一个docker0虚拟网桥，通过docker0网桥以及Iptables nat表配置与宿主机通信。

None	该模式关闭了容器的网络功能。

Container	创建的容器不会创建自己的网卡，配置自己的IP，而是和一个指定的容器共享IP、端口范围。

自定义网络	略
