package org.acme;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import io.quarkus.example.Greeter;
import io.quarkus.example.HelloRequest;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Client {

    public static void main(String args[]) {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @GrpcClient
        Greeter hello;

        @Override
        public int run(String... args) throws Exception {
            if(args.length == 1) {
                var response = hello.sayHello(HelloRequest.newBuilder().setName(args[0]).build()).await().atMost(Duration.of(10, ChronoUnit.SECONDS));

                System.out.println(response.getMessage());

                return 0;
            }
            else {
                System.err.println("Usage: provide exactly 1 argument");
                return -1;
            }
        }
    }
}
