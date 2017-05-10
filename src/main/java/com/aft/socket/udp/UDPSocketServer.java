package com.aft.socket.udp;

import java.io.IOException;

/**
 * @author Andrew Kew
 */
public class UDPSocketServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java -DinputFilePath=/path/to/one-liners-file -jar SocketTester-udpServer.jar <port>");
            return;
        }
        new UDPServerThread(Integer.parseInt(args[0])).start();
    }
}