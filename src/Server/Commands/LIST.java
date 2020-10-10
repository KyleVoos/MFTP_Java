package Server.Commands;

import java.io.DataOutputStream;

public class LIST {

    private DataOutputStream dataOutputStreamControl;

    public LIST(DataOutputStream dataOutputStreamControl) {
        this.dataOutputStreamControl = dataOutputStreamControl;
    }
}
