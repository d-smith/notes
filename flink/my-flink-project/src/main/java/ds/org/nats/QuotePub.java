package ds.org.nats;


import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class QuotePub {
    public static void main(String[] args ) throws Exception {

        Connection nc = Nats.connect("nats://localhost:4222");

        Dispatcher d = nc.createDispatcher(msg -> {
            System.out.println(msg.getSubject());
            String response = new String(msg.getData(), StandardCharsets.UTF_8);
            System.out.println("got " + response);
        });

        System.out.println("subscribe");
        d.subscribe("quotes.a");

        for(;;) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String line = reader.readLine();

            String[] tokens = line.split(",");
            String topic = "quotes." + tokens[0];
            System.out.println("publish topic " + topic + "with data " + tokens[1]);
            nc.publish(topic, tokens[1].getBytes(StandardCharsets.UTF_8));
        }
    }

}
