package Server;

import Server.Commands.PASV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ClientControlConnection implements Runnable {

    private static final int MIN_PORT_NUM = 1024;
    private static final int MAX_PORT_NUM = 65535;
    private Socket clientSocket;
    private String username;
    private DataInputStream dataInputStreamControl;
    private DataOutputStream dataOutputStreamControl;
    private ServerSocket clientDataCon;
    private Integer dataPort;
//    private DataInputStream dataInputStreamDataCon;
//    private DataOutputStream dataOutputStreamDataCon;

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

    private void setClientDataCon(ServerSocket serverDataCon) {
        clientDataCon = serverDataCon;
    }

    private ServerSocket getClientDataCon() {
        return clientDataCon;
    }

    @Override
    public void run() {
        ParseClientInput inputParser = new ParseClientInput();
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
                    case "HELP": // return usage doc on command if specified, otherwise general help doc
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
                    case "NOOP": // no operation, return 200 OK
                        sendResponse(FTPServerReturnCodes.OKAY);
                        break;
                    case "PASS": // authentication password
                        break;
                    case "PASV": // enter passive mode
                        startPASV();
//                        dataSocket = startPASV();
                        break;
                    case "PORT": // addess and port server should connect to (active mode)
//                        dataSocket = startPORT();
                        break;
                    case "REIN": // reinitialize connection
                        break;
                    case "RETR": // retrieve a copy of the file
                        if (!dataConSetup())
                            sendResponse(FTPServerReturnCodes.OPEN_DATA_CON_FAILED);
                        acceptClientDataSocket();
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
                        if (!dataConSetup())
                            sendResponse(FTPServerReturnCodes.OPEN_DATA_CON_FAILED);
                        break;
                    case "USER": // authentication username
                        break;
                    case "QUIT":
                        quit();
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

    private boolean dataConSetup() {
        return getClientDataCon() != null;
    }

    private ServerSocket setupPassiveMode() {

        for (int i = MIN_PORT_NUM; i <= MAX_PORT_NUM; i++) {
            try {
                return new ServerSocket(i);
//                ServerSocket dataSocket = new ServerSocket(i);
//                setPort(dataSocket.getLocalPort());
//                return dataSocket;
            }
            catch (BindException bEx) {

            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }

        return null;
    }

    private int getRandomPort(int minPort, int maxPort) {

        Random rand = new Random();

        return rand.nextInt((maxPort - minPort) + 1) + minPort;
    }

    private void acceptClientDataSocket() {

        try {
            new Thread(new ClientDataConnection(getClientDataCon().accept()));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void startPASV() {

        setClientDataCon(setupPassiveMode());
        if (getClientDataCon() != null) {
            sendResponse(String.format(FTPServerReturnCodes.PASSIVE_MODE,
                FTPServer.serverAddress.toString().replace('.', ','),
                getClientDataCon().getLocalPort() / 256, getClientDataCon().getLocalPort() % 256));
        }
        else
            sendResponse(FTPServerReturnCodes.OPEN_DATA_CON_FAILED);
    }

    private ServerSocket startPORT() {
        return null;
    }

    private void startRETR() {

    }

    private void startSTOR() {

    }

    private void sendResponse(String response) {
        try {
            dataOutputStreamControl.writeUTF(response);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void quit() {

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
