在本地配置DNS 解析

C:\Windows\System32\drivers\etc


`docker run -d --name mynginx -p 9999:80 nginx`


`docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mynginx`

###window

`route print`

`route -p add 172.17.0.0/16 172.27.16.1`

###ubuntu

`ip route show`

`sudo ip route add 172.17.0.0/16 via $(cat /etc/resolv.conf | grep nameserver | awk '{print $2}' | head -n1)`