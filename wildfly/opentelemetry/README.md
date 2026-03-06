# Wildfly OpenTelemetry

## Run
```bash
java -jar target/test-bootable.jar --deployment=target/test.war
``` 

## Test
```bash
curl http://localhost:8080/test/api/hello
```

## Check attribute

```bash
jboss-cli.sh --connect
/subsystem=opentelemetry:read-attribute(name=sampler-type,resolve-expressions=true
```