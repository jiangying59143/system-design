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
