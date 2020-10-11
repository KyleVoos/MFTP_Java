package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientDataConnection implements Runnable {

    private Socket clientDataSocket;
    private DataInputStream disclientDataCon;
    private DataOutputStream dosClientDataCon;
    private String command;
    private String arg;

    public ClientDataConnection(Socket dataCon) {

        if (dataCon == null)
            throw new NullPointerException("Client Data Socket is NULL");
        clientDataSocket = dataCon;
        try {
            disclientDataCon = new DataInputStream(clientDataSocket.getInputStream());
            dosClientDataCon = new DataOutputStream(clientDataSocket.getOutputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {


    }


}
