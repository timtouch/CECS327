import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ttouc on 4/13/2017.
 */
public class ServerThread extends Thread{
    Socket socket;
    NextNumber number;
    ServerThread(Socket socket, NextNumber number){
        this.socket = socket;
        this.number = number;
    }

    public void run(){
        try{
            String message = null;
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("User " + bufferedReader.readLine() + " is now connected to the server.");
            while((message = bufferedReader.readLine()) != null){
                System.out.println("Incoming client message: " + message);
                printWriter.println("Server echoing Client message: " + number.processInput(message));
            }

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
