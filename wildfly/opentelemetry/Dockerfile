FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.22-1.1743605859@sha256:919e0f42342b48b692543bbc62ee7e8beeebc8f00f36b768d47b1542ba619ab0

USER root

ENV TZ=Europe/Stockholm HOME=/opt/test LANG=C.utf8

RUN groupadd --system --gid=1000 test && \
    useradd --system --no-log-init --gid test --uid=1000 test && \
    mkdir /var/test && chown test:test /var/test && \
    mkdir /opt/test && chown test:test /opt/test

USER test

COPY target/test-bootable.jar /opt/test/
COPY target/test.war /opt/test/

WORKDIR /var/test

EXPOSE 8080
EXPOSE 9990

CMD ["java", \
     "-jar", \
     "/opt/test/test-bootable.jar", \
     "-Djboss.bind.address=0.0.0.0", \
     "--deployment=/opt/test/test.war" \
]
