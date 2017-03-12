/**
 * Created by ttouc on 2/27/2017.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Assignment3ServerThread extends Thread {
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;

    public Assignment3ServerThread() throws IOException {
        this("ServerThread");
    }

    public Assignment3ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);

    }

    public void run() {
        try {
            while (true) { // The server wil always listen
                try {
                    byte[] buf = new byte[256];

                    DatagramPacket packet = new DatagramPacket(buf, buf.length); //The message content: sent/received
                    socket.receive(packet);  //Waiting for message

                    String dString = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Received " + dString + " from client address: " + packet.getAddress());

                    buf = dString.getBytes();
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    packet = new DatagramPacket(buf, buf.length, address, port);  //Constructs the DatagramPacket for client
                    socket.send(packet);  //Sending the packet back to client

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            socket.close();
        }

    }

}
