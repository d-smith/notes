package org.ds.lmax;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PositionPub {
    public static void main(String[] args ) throws Exception {

        Connection nc = Nats.connect("nats://localhost:4222");

        for(;;) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String line = reader.readLine();
            nc.publish("positions", line.getBytes(StandardCharsets.UTF_8));
        }
    }

}
