package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {

    public static InetAddress serverAddress;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(21);
            serverAddress = serverSocket.getInetAddress();
            System.out.println("FTP Server started. Listening on port 21.");
            while (true) {
                Socket newClientConnection = serverSocket.accept();
                System.out.println(String.format("new client from %s connected.", newClientConnection.getLocalAddress()));
                new ClientControlConnection(newClientConnection);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (serverSocket != null) {
                    System.out.println("Shutting down FTP server.");
                    serverSocket.close();
                    System.out.println("Server connection closed.");
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
