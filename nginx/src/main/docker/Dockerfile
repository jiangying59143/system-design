# 使用Nginx的官方镜像
FROM nginx:latest

RUN rm /usr/share/nginx/html/index.html
# 将本地的index.html文件复制到Nginx的默认静态文件目录中
COPY index.html /usr/share/nginx/html/
