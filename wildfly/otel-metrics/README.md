# otel-metrics

## standalone

```bash
bin/jboss-cli.sh -c <<EOF
    if (outcome != success) of /subsystem=opentelemetry:read-resource
        /extension=org.wildfly.extension.opentelemetry:add()
        /subsystem=opentelemetry:add()
    end-if
    /extension=org.wildfly.extension.microprofile.telemetry:add
    /subsystem=microprofile-telemetry:add
    reload
EOF
```

## enable

```bash
bin/standalone.sh -Dotel.sdk.disabled=false
```