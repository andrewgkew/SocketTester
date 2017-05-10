package com.aft.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author Andrew Kew
 */
public class UDPPingPong {
    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.out.println("Usage: java -jar SocketTester-udpPingPong <hostname> <port> <count>");
            return;
        }
        String hostName = args[0];
        int port = Integer.parseInt(args[1]);
        int maxCount = Integer.parseInt(args[2]);
        int currentCount = 0;

        try (
                // get a datagram socket
                DatagramSocket socket = new DatagramSocket()
        ) {
            socket.setSoTimeout(60000);
            // send request
            String data = "data";
            byte[] receiveData = new byte[12];
            byte[] sendData = data.getBytes();

            InetAddress address = InetAddress.getByName(hostName);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            System.out.println("Sending packet to " + address.getHostAddress() + "@" + port + "with data " + data);

            while (currentCount < maxCount) {
                socket.send(sendPacket);

                // get response
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Waiting for response");
                try {
                    socket.receive(receivePacket);
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout reached: " + e);
                    socket.close();
                    System.exit(1);
                }

                // display response
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Response [" + currentCount +"]: " + received);
                currentCount++;
                Thread.currentThread().sleep(1000);
            }
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException | InterruptedException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}