### Windows使用Docker出现exit 139错误

建议直接升级内核或者 采用 如下配置

创建C:\Users\(用户名)\.wslconfig，里面写入

`[wsl2]`

`kernelCommandLine = vsyscall=emulate`

**最后一步，重启电脑。** 

_来源 https://blog.csdn.net/i2blue/article/details/119035406_