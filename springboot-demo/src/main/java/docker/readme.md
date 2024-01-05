1. 可以执行 `docker build -t spring-boot-demo:v1 .`生成镜像

2. 也可以通过下面的配置 然后执行 `mvn clean package`
```
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>build-image-no-fork</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

3. 也可以通过下面的配置 然后执行 `mvn compile jib:build`
```
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>3.1.4</version>
    <configuration>
        <to>
            <image>localhost/registry/springboot:v1</image>
        </to>
    </configuration>
</plugin>
```
