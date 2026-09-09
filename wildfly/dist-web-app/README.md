# dist-web-app

## Build

```bash
./mvnw clean package
```

## Run

```bash
java -jar target/dist-web-app-1.0.0-SNAPSHOT-bootable.jar --deployment=target/dist-web-app-1.0.0-SNAPSHOT.war

java -jar target/dist-web-app-1.0.0-SNAPSHOT-bootable.jar -Djboss.http.port=8081 -Djboss.management.http.port=9991 --deployment=target/dist-web-app-1.0.0-SNAPSHOT.war
```

## Test

```bash
curl http://localhost:8080/dist-web-app-1.0.0-SNAPSHOT/hello

curl http://localhost:8081/dist-web-app-1.0.0-SNAPSHOT/hello
```


