### 参考网站

https://github.com/kubesphere/kubesphere/blob/master/README_zh.md

### 获取所有release

https://github.com/kubesphere/ks-installer/releases

`kubectl apply -f metrics-server.yaml`

`kubectl apply -f kubesphere-installer-3.4.0.yaml`

`kubectl apply -f cluster-configuration-3.4.0.yaml`

`kubectl logs -n kubesphere-system $(kubectl get pod -n kubesphere-system -l 'app in (ks-install, ks-installer)' -o jsonpath='{.items[0].metadata.name}') -f`

`kubectl -n kubesphere-monitoring-system create secret generic kube-etcd-client-certs  --from-file=etcd-client-ca.crt=/etc/kubernetes/pki/etcd/ca.crt  --from-file=etcd-client.crt=/etc/kubernetes/pki/apiserver-etcd-client.crt  --from-file=etcd-client.key=/etc/kubernetes/pki/apiserver-etcd-client.key`

````
Console: http://192.168.154.100:30880
Account: admin
Password: P@88w0rd

NOTES：
1. After you log into the console, please check the
   monitoring status of service components in
   "Cluster Management". If any service is not
   ready, please wait patiently until all components
   are up and running.
2. Please change the default password after login.

#####################################################
https://kubesphere.io             2024-01-27 17:15:09
#####################################################
````