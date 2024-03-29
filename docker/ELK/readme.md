# docker ElasticSearch

## 创建网络
````
docker network create elastic
````

## 设置java 虚拟机内存大小
````
sudo sysctl -w vm.max_map_count=262144
````

````
docker run -itd --name es01 \
--net elastic -p 9200:9200 -it -m 1GB \
docker.elastic.co/elasticsearch/elasticsearch:8.12.2
````


ℹ️  Password for the elastic user (reset with `bin/elasticsearch-reset-password -u elastic`):

Z6Fc=+t9iMSH15CptkhS

ℹ️  HTTP CA certificate SHA-256 fingerprint:

7844149da1612b205888964fab84dbd0e6206ccb1910c82ac91253976bf89391

ℹ️  Configure Kibana to use this cluster:

• Run Kibana and click the configuration link in the terminal when Kibana starts.

• Copy the following enrollment token and paste it into Kibana in your browser (valid for the next 30 minutes):

eyJ2ZXIiOiI4LjEyLjIiLCJhZHIiOlsiMTcyLjE4LjAuMjo5MjAwIl0sImZnciI6Ijc4NDQxNDlkYTE2MTJiMjA1ODg4OTY0ZmFiODRkYmQwZTYyMDZjY2IxOTEwYzgyYWM5MTI1Mzk3NmJmODkzOTEiLCJrZXkiOiJVb05xZEk0QkpZVVN4N2NIa3E1NTotVVJhcUJRNFJTR1VZS0xiYXJPZXF3In0=

ℹ️ Configure other nodes to join this cluster:

• Copy the following enrollment token and start new Elasticsearch nodes with `bin/elasticsearch --enrollment-token <token>` (valid for the next 30 minutes):

eyJ2ZXIiOiI4LjEyLjIiLCJhZHIiOlsiMTcyLjE4LjAuMjo5MjAwIl0sImZnciI6Ijc4NDQxNDlkYTE2MTJiMjA1ODg4OTY0ZmFiODRkYmQwZTYyMDZjY2IxOTEwYzgyYWM5MTI1Mzk3NmJmODkzOTEiLCJrZXkiOiJWSU5xZEk0QkpZVVN4N2NIa3E1XzpvbmQxOHhPMVI3VzBCYlNoWVZmc1l3In0=

If you're running in Docker, copy the enrollment token and run:

`docker run -e "ENROLLMENT_TOKEN=<token>" docker.elastic.co/elasticsearch/elasticsearch:8.12.2`


## 重置密码
````
docker exec -it es01 \
/usr/share/elasticsearch/bin/elasticsearch-reset-password \
-u elastic
````
zSKk6ZnGxClCfbMOEfa7

## 设置上面的密码到下面 placeholder
````
export ELASTIC_PASSWORD="your_password"
````

## copy ca 证书到本地
````
docker cp es01:/usr/share/elasticsearch/config/certs/http_ca.crt .
````
本地双击安装，然后访问下面的链接

https://localhost:9200/

## 使用证书和密码登录
````
curl --cacert http_ca.crt -u elastic:$ELASTIC_PASSWORD https://localhost:9200
````

## 给其他节点生成enrollment-token， 只有30分钟有效期
````
docker exec -it es01 /usr/share/elasticsearch/bin/elasticsearch-create-enrollment-token -s node
````

## 用上面的token 创建节点并且加入集群 替换placeholder
````
docker run -e ENROLLMENT_TOKEN="<token>" --name es02 --net elastic -it -m 1GB docker.elastic.co/elasticsearch/elasticsearch:8.12.2
````


## 使用下面命令查看节点
````
curl --cacert http_ca.crt -u elastic:$ELASTIC_PASSWORD https://localhost:9200/_cat/nodes
````
或者再浏览器地址栏中输入 https://localhost:9200/_cat/nodes


# docker kibana

## 运行容器
````
docker run -itd --name kib01 --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.12.2
````

浏览器输入 http://localhost:5601

输入原来token

输入验证码

用下面的命令运行 kibana 再次填入

## 创建token
````
docker exec -it es01 /usr/share/elasticsearch/bin/elasticsearch-create-enrollment-token -s kibana
````

用户名 elastic
密码 zSKk6ZnGxClCfbMOEfa7