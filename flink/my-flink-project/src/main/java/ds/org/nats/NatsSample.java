package ds.org.nats;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;


import java.nio.charset.StandardCharsets;

public class NatsSample {

    public static void main(String[] args) throws Exception {
        System.out.println("connect");
        Connection nc = Nats.connect("nats://localhost:4222");

        System.out.println("create dispatcher");
        Dispatcher d = nc.createDispatcher(msg -> {
            System.out.println(msg.getSubject());
            String response = new String(msg.getData(), StandardCharsets.UTF_8);
            System.out.println("got " + response);
        });

        System.out.println("subscribe");
        d.subscribe("quotes.a");

        System.out.println("publish");
        nc.publish("quotes.a", "12.3".getBytes(StandardCharsets.UTF_8));
        nc.publish("quotes.b", "132.3".getBytes(StandardCharsets.UTF_8));
        nc.publish("quotes.c", "555.44".getBytes(StandardCharsets.UTF_8));
        nc.publish("quotes.a", "123.3".getBytes(StandardCharsets.UTF_8));

    }
}
