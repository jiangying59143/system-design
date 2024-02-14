#### storageClass

`mkdir /root/data/nfs/storageClass -pv`

`vim /etc/exports`
````
/root/data/nfs/storageClass     192.168.154.0/24(rw,no_root_squash)
````

`systemctl restart nfs`

前置配置

`kubectl create -f namespace.yaml,class.yaml,rbac.yaml,provisioner.yaml`

`kubectl create -f pvc.yaml,claim-pod.yaml`

`kubectl get sc,pvc,pods -n dev -o wide`

`cd /root/data/nfs/storageClass/`

当删除claim时候 archived- 会加在目录上

archived-dev-test-claim-pvc-bc36b908-b270-43e1-8f67-19cf6af8b044

删除所有archived开头的文件夹

`find . -type d -name "archived*"  -exec rm -r {} +`

`tail -f out.txt`


参考 https://www.cnblogs.com/bixiaoyu/p/16444458.html