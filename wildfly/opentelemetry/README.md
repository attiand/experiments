# Wildfly OpenTelemetry

## Run
```bash
java -jar target/test-bootable.jar --deployment=target/test.war
``` 

## Check attribute

```bash
jboss-cli.sh --connect
/subsystem=opentelemetry:read-attribute(name=sampler-type,resolve-expressions=true
```