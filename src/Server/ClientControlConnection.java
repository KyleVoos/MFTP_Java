package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientControlConnection implements Runnable {

    Socket clientSocket;
    ServerSocket clientDataCon;
    String username;
    DataInputStream dataInputStreamControl;
    DataOutputStream dataOutputStreamControl;
    DataInputStream dataInputStreamDataCon;
    DataOutputStream dataOutputStreamDataCon;

    public ClientControlConnection(Socket clientCon) {
        try {
            clientSocket = clientCon;
            dataInputStreamControl = new DataInputStream(clientCon.getInputStream());
            dataOutputStreamControl = new DataOutputStream(clientCon.getOutputStream());
            dataOutputStreamControl.writeUTF(FTPServerReturnCodes.NEW_USER);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
//        String command = null;
//        while (true) {
//            command = getClientInput();
//        }
    }

//    private String getClientInput() {
//
//        try {
//            String command = dataInputStreamControl.readUTF();
//            command = command.trim().toUpperCase();
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        return comm
//    }
}
