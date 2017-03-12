/**
 * Created by ttouc on 2/27/2017.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Assignment3Client {

    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            System.out.println("Usage: java Assignment3Client <hostname>");
            return;
        }
        DatagramSocket socket = new DatagramSocket();

        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName(args[0]);
        try {
            for (Integer i = 1; i <= 20; i++) { // Sends a sequence of numbers to server
                String number = i.toString();
                buf = number.getBytes();
                //These DatagramPackets are a wrapper class
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Number echoed: " + received);
            }
        } finally {
            socket.close();
        }
    }
}
