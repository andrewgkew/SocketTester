package com.aft.socket.udp;

import java.io.IOException;

/**
 * @author Andrew Kew
 */
public class UDPPingPongServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java -jar SocketTester-udpPingPongServer.jar <port>");
            return;
        }
        new UDPPingPongServerThread(Integer.parseInt(args[0])).start();
    }
}