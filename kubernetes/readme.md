```
kubectl apply -f docker-registry.yaml
```

```
kubectl delete service registry-service
```

```
kubectl apply -f myapp-deployment.yaml
```

```
kubectl apply -f myapp-service.yaml
```

```
kubectl create -f myapp-pod.yaml
```

1. 查看所有 Deployments, pods, services：

```
kubectl get deployments
```

```
kubectl get pods
```

```
kubectl get services
```

2. 查看特定 Deployment 的详细信息：

```
kubectl describe deployment springboot-demo-deployment
```

3. 查看 Deployment 的 Pod 状态：

```
kubectl get pods -l app=springboot-demo
```

4. 查看特定 Deployment 的日志

```
kubectl logs deployment/springboot-demo-deployment
```

5. 删除特定的 Deployment

```
kubectl delete deployment springboot-demo-deployment
```