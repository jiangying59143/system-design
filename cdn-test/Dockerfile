FROM openjdk:21-slim

# 设置工作目录
WORKDIR /app

# 复制编译好的jar文件到工作目录
COPY target/cdn-test-1.0-SNAPSHOT.jar /app

EXPOSE 1053

CMD ["java", "-jar", "cdn-test-1.0-SNAPSHOT.jar"]
