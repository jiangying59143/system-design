FROM openjdk:21-slim

# 设置工作目录
WORKDIR /app

# 复制可运行的jar文件到镜像中
COPY ../../../../target/springboot-demo-0.0.1-SNAPSHOT.jar /app/springboot-demo-0.0.1-SNAPSHOT.jar

# 暴露应用程序使用的端口（如果需要）
EXPOSE 8080

# 定义启动命令
CMD ["java", "-jar", "/app/springboot-demo-0.0.1-SNAPSHOT.jar"]
