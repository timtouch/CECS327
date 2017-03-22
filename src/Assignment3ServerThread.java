/**
 * Author: Timothy Touch
 *
 * Description:
 *  Server Thread meant to listen for requests from clients.  Once it receives a request, it echoes the message that the
 *  client sent back to the client.
 */
import java.io.*;
import java.net.*;

public class Assignment3ServerThread extends Thread {
    protected DatagramSocket socket = null;
    protected int listenSocket = 4445;

    public Assignment3ServerThread() throws IOException {
        this("ServerThread");
    }

    public Assignment3ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(listenSocket);
    }

    public void run() {
        try {
            System.out.println("The server is listening in port " + socket.getLocalPort());
            while (true) { // The server wil always listen
                try {
                    byte[] buf = new byte[256];

                    DatagramPacket packet = new DatagramPacket(buf, buf.length); // The message content: sent/received
                    socket.receive(packet);  // Waiting for message

                    String dString = new String(packet.getData(), 0, packet.getLength()); // Grab the message from the Datagram
                    System.out.printf("Received \"%s\" from client address: %s%n", dString, packet.getAddress());

                    buf = dString.getBytes(); // Convert string to bytes
                    InetAddress address = packet.getAddress(); // Get address of client we are going to send back to
                    int port = packet.getPort(); // Get port of client
                    packet = new DatagramPacket(buf, buf.length, address, port);  //Constructs the DatagramPacket address to the client
                    System.out.printf("Sending \"%s\" to client address: %s%n", dString, packet.getAddress());
                    socket.send(packet);  // Sending the packet back to client

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            socket.close();
        }

    }

}
