`docker run -d -p 5000:5000 --name registry-container registry`

然后在docker 配置里面添加下面的配置
`"insecure-registries": [
"localhost:5000"

],`