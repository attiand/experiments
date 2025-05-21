FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.20-2.1726695169@sha256:4c90561565a199aeec97bad3628030c0deede8b797f3899c45c3761d3e242604


USER root

ENV TZ=Europe/Stockholm HOME=/opt/slask LANG=C.utf8

RUN groupadd --system --gid=1000 slask && \
    useradd --system --no-log-init --gid slask --uid=1000 slask && \
    mkdir /var/slask && chown slask:slask /var/slask && \
    mkdir /opt/slask && chown slask:slask /opt/slask


USER slask

COPY --chown=slask:slask target/java-mbean-0.0.1-SNAPSHOT.jar /opt/slask

WORKDIR /var/slask

CMD java -cp /opt/slask/java-mbean-0.0.1-SNAPSHOT.jar test.Server