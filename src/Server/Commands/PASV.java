package Server.Commands;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

public class PASV {

    private static final int MIN_PORT_NUM = 1024;
    private static final int MAX_PORT_NUM = 65535;
    private int p1;
    private int p2;

    public PASV() {
    }

//    public ServerSocket executePASV() {
//
//        for (int i = MIN_PORT_NUM; i <= MAX_PORT_NUM; i++) {
//            try {
//                ServerSocket dataSocket = new ServerSocket(i);
//                setPort(dataSocket.getLocalPort());
//                return dataSocket;
//            }
//            catch (BindException bEx) {
//
//            }
//            catch (IOException ex){
//                ex.printStackTrace();
//            }
//        }
//
//        return null;
//    }

    private void setPort(int port) {
        p1 = port / 256;
        p2 = port % 256;
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }
}
