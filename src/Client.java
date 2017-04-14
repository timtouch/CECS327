
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;


public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException{
        if(args.length == 1){
            String name = args[0];
            Socket socket = new Socket("localhost", 4444);
            BufferedReader bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(name);
            BufferedReader bufferedReader = new java.io.BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.println("Enter something: ");
                String readerInput = bufferedReader.readLine();
                printWriter.println(readerInput);
                System.out.println(bufferedReaderFromClient.readLine());
            }
        }else{
            System.out.println("Usage: Client <name>");
        }
    }
}