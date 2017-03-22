/**
 * Author: Timothy Touch
 *
 *
 * Description:
 *  A client program that sends a series of numbers from 1-20 to the specified server.  After it has sent a message
 *  it will wait for a response from the server, print out the response, and proceed to send the next sequential
 *  number.
 */
import java.io.*;
import java.net.*;

public class Assignment3Client {

    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            System.out.println("Usage: java Assignment3Client <hostname>");
            return;
        }
        DatagramSocket socket = new DatagramSocket();

        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName(args[0]); // Save address of server that we want to send a message to
        try {
            for (Integer i = 1; i <= 20; i++) { // Sends a sequence of numbers to server
                String number = i.toString();
                buf = number.getBytes(); // Basically convert the int to a String to a byte to send in the Datagram Packet
                //These DatagramPackets are a wrapper class
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);  // Send packet to server

                packet = new DatagramPacket(buf, buf.length); // Create a new packet
                socket.receive(packet);  // Wait to receive a response from the server

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Number echoed: " + received);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
