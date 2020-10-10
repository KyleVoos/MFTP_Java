package Server;

import Server.Commands.PASV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientControlConnection implements Runnable {

    private Socket clientSocket;
    private String username;
    private DataInputStream dataInputStreamControl;
    private DataOutputStream dataOutputStreamControl;
    private ServerSocket clientDataCon;
    private DataInputStream dataInputStreamDataCon;
    private DataOutputStream dataOutputStreamDataCon;

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
        ParseClientInput inputParser = new ParseClientInput();
        ServerSocket dataSocket;
        while (!inputParser.getCommand().equals("QUIT")) {
            try {
                inputParser.parse(dataInputStreamControl.readUTF());

                switch (inputParser.getCommand()) {
                    case "ABOR": // abort an active file transfer
                        break;
                    case "CWD": // change working directory
                        break;
                    case "DELE": // delete file
                        break;
                    case"DSIZ": // get directory size
                        break;
                    case "HELP": // reuturn usage doc on command if specified, otherwise general help doc
                        break;
                    case "LIST": // return info on file/dir if included, otherwise CWD
                        break;
                    case "MDTM": // return lst modified time of specified file
                        break;
                    case "MKD": // make directory
                        break;
                    case "MLSD": // list contents of directory if specified
                        break;
                    case "NLST": // return list of file names in specified directory
                        break;
                    case "PASS": // authentication password
                        break;
                    case "PASV": // enter passive mode
                        PASV pasv = new PASV(dataOutputStreamControl);
                        dataSocket = pasv.executePASV();
                        if (dataSocket != null) {
                            sendResponse(String.format(FTPServerReturnCodes.PASSIVE_MODE,
                                FTPServer.serverAddress.toString().replace('.', ','), pasv.getP1(),
                                pasv.getP2()));
                        }
                        else
                            sendResponse(FTPServerReturnCodes.OPEN_DATA_CON_FAILED);
                        break;
                    case "PORT": // addess and port server should connect to (active mode)
                        break;
                    case "REIN": // reinitialize connection
                        break;
                    case "RETR": // retrieve a copy of the file
                        break;
                    case "RMD": // remove a directory
                        break;
                    case "RMDA": // remove a directory tree
                        break;
                    case "RNTO": // rename to
                        break;
                    case "SIZE": // return size of file
                        break;
                    case "STAT": // reutrn info on server status
                        break;
                    case "STOR": // accept the data and to store the data as a file at the server site
                        break;
                    case "USER": // authentication username
                        break;
                    default:
                        dataOutputStreamControl.writeUTF(FTPServerReturnCodes.NOT_IMPLEMENTED);
                        break;
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public void sendResponse(String response) {
        try {
            dataOutputStreamControl.writeUTF(response);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void quit() {

        try {
            if (clientSocket != null)
                clientSocket.close();
            if (clientDataCon != null)
                clientDataCon.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
