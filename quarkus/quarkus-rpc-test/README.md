# Build

`./mvnw package`

## docker image

`./mvnw clean package -Dquarkus.container-image.build=true`


# Run

`java -jar target/quarkus-app/quarkus-run.jar`


## docker 

`podman run -it --rm -p 9000:9000 maan0496/quarkus-rpc-test:1.0.0-SNAPSHOT`

