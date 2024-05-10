
refer to https://blog.csdn.net/randavy/article/details/133815155

````
System.setProperty("java.home", ".");
````

````
java -cp target/maven-native-image-1.0-SNAPSHOT.jar -agentlib:native-image-agent=config-output-dir=./tempDir com.my.App
````

````
native-image -jar target/maven-native-image-1.0-SNAPSHOT.jar -o myNative
````

