package org.ds.lmax;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketReader {
    public static class ClientHandler extends Thread {

        final DataInputStream dis;
        final Socket s;

        public ClientHandler(Socket s, DataInputStream dis)
        {
            this.s = s;
            this.dis = dis;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                while(true) {

                    System.out.println("read from socket");
                    String received = in.readLine();
                    System.out.println("Got this " + received);
                }
            } catch(Throwable t) {
                t.printStackTrace();
                return;
            }
        }
    }

    public static void main(String... args) throws Exception {
        ServerSocket ss = new ServerSocket(5056);
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        Thread t = new ClientHandler(s, dis);
        t.start();
    }
}
