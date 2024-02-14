关键代码

line 55 [localCDNconfigMap.yaml](localCDNconfigMap.yaml)

````
forward . 10.233.0.3 {
    force_tcp
}
````

line 19 [configMap.yaml](configMap.yaml)
````
hosts {
    10.233.49.60 core.harbor.domain
    fallthrough
}
````

````
kubectl delete cm nodelocaldns coredns -n kube-system

kubectl apply -f configMap.yaml,localCDNconfigMap.yaml

kubectl rollout restart deployment coredns  -n kube-system

````