### config map

创建configmap

`kubectl create -f configmap.yaml`

查看configmap详情

`kubectl describe cm configmap -n dev`

查看pod

`kubectl get pod pod-configmap -n dev`

`kubectl exec -it pod-configmap -n dev /bin/sh`
````
# cd /configmap/config/
# ls
info
# more info
username:admin
password:123456
````

### Secret

````
echo -n 'admin' | base64 #准备username
echo -n '123456' | base64 #准备password
````

创建secret

`kubectl create -f secret.yaml`

查看secret详情

`kubectl describe secret secret -n dev`

查看pod

`kubectl get pod pod-secret -n dev`

进入容器，查看secret信息，发现已经自动解码了

`kubectl exec -it pod-secret /bin/sh -n dev`
````
# ls /secret/config/
password  username
# more /secret/config/username
admin
# more /secret/config/password
123456
````