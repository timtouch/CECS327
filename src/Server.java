import java.io.BufferedReader;
import java.io.*;
import java.net.*;

public class Server{

    NextNumber number;
    Server(NextNumber number){
        this.number = number;
    }

    public static final int PORT = 4444;

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(PORT);
        NextNumber number = new NextNumber();
        Server server = new Server(number);
        System.out.println("Server up and ready for connections...");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket, server.number).start();
            }
        }
        finally {
            serverSocket.close();
        }
    }
}
