package com.aft.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Andrew Kew
 */
public class UDPSocketClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Usage: java -jar SocketTester-udpClient <hostname> <port>");
            return;
        }
        String hostName = args[0];
        int port = Integer.parseInt(args[1]);

        try (
            // get a datagram socket
            DatagramSocket socket = new DatagramSocket()
        ) {

            // send request
            byte[] buf = new byte[256];
            InetAddress address = InetAddress.getByName(hostName);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // display response
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Moment: " + received);

            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
