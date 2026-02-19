package test;

import org.testcontainers.containers.Network;

public class TestNetwork {

    private TestNetwork() {

    }

    public static Network network() {
        String existingNw = System.getProperty("se.uhr.nya.integration.ladok3.test.network");

        if(existingNw != null){
            return new Network() {
                @Override
                public String getId() {
                    return existingNw;
                }

                @Override
                public void close() {
                    // responsibly of the owner
                }
            };
        }
        else {
            // I.e. no network
            return null;
        }
    }
}
