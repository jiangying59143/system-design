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
   src/main/docker/jib/entrypoint.sh 切记要换成 Linux 分隔符
   还要替换里面的主类名
```
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>${jib-maven-plugin.version}</version>
    <configuration>
        <allowInsecureRegistries>true</allowInsecureRegistries>
        <from>
            <image>eclipse-temurin:21-jre-jammy</image>
            <platforms>
                <platform>
                    <architecture>amd64</architecture>
                    <os>linux</os>
                </platform>
            </platforms>
        </from>
        <to>
            <image>localhost:5000/springboot-demo:latest</image>
        </to>
        <container>
            <entrypoint>
                <shell>bash</shell>
                <option>-c</option>
                <arg>/entrypoint.sh</arg>
            </entrypoint>
            <ports>
                <port>8080</port>
            </ports>
            <environment>
                <SPRING_OUTPUT_ANSI_ENABLED>ALWAYS</SPRING_OUTPUT_ANSI_ENABLED>
                <JHIPSTER_SLEEP>0</JHIPSTER_SLEEP>
            </environment>
            <creationTime>USE_CURRENT_TIMESTAMP</creationTime>
            <user>1000</user>
        </container>
        <extraDirectories>
            <paths>src/main/docker/jib</paths>
            <permissions>
                <permission>
                    <file>/entrypoint.sh</file>
                    <mode>755</mode>
                </permission>
            </permissions>
        </extraDirectories>
    </configuration>
</plugin>
```
