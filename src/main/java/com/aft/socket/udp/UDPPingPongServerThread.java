package com.aft.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Andrew Kew
 */
public class UDPPingPongServerThread extends Thread {
    protected DatagramSocket socket = null;
    protected boolean moreQuotes = true;
    protected int port = 0;

    public UDPPingPongServerThread(int port) throws IOException {
        super("UDPServerThread");
        this.port = port;
        socket = new DatagramSocket(this.port);
    }

    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[4];
                byte[] send;

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Packet received with data: " + received);
                send = this.concatenateByteArrays(packet.getData(), "-bounced".getBytes());
                String sending = new String(send, 0, send.length);

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(send, send.length, address, port);
                System.out.println("Sending exact packet back with extra data: " + sending);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    private byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
