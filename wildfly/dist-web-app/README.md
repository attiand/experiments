# dist-web-app

## Build

```bash
./mvnw clean package
```

## Run

```bash
java -jar target/dist-web-app-1.0.0-SNAPSHOT-bootable.jar -Dinfinispan.server.host=<host> -Dinfinispan.server.port=<port> -Dinfinispan.server.user=<user> -Dinfinispan.server.password=<password> --deployment=target/dist-web-app-1.0.0-SNAPSHOT.war
```

## Test

```bash
http://localhost:8080/dist-web-app-1.0.0-SNAPSHOT/api/hello
```


