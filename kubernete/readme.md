通过获取集群状态的方法，检查是否已恰当地配置了 kubectl：

```
kubectl cluster-info
```

you can now use it to access your shiny new cluster
```
kubectl get po -A
```
Create a sample deployment and expose it on port 8080
```
kubectl create deployment hello-minikube --image=kicbase/echo-server:1.0
```
```
kubectl expose deployment hello-minikube --type=NodePort --port=8080
```
deployment will soon show up when you run
```
kubectl get services hello-minikube
```
Alternatively, use kubectl to forward the port:
```
kubectl port-forward service/hello-minikube 7080:8080
```

To access a LoadBalancer deployment, use the “minikube tunnel” command. Here is an example deployment:

`kubectl create deployment balanced --image=kicbase/echo-server:1.0`

`kubectl expose deployment balanced --type=LoadBalancer --port=8080`

To find the routable IP, run this command and examine the EXTERNAL-IP column:

`kubectl get services balanced`

delete services and ingress

`kubectl delete service balanced hello-minikube`

`kubectl delete services,ingress --all`

安装ingress控制器

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml`

`kubectl apply -f ingress-example.yaml`

`kubectl get ingressClass`

