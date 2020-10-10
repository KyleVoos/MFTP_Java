package Server;

import java.util.HashSet;

public class ParseClientInput {

    private static String command;
    private String argument;
    public HashSet<String> singleArgs;
    int numArgs = 0;

    public ParseClientInput() {
        singleArgs = new HashSet<>();
        command = "";
    }

    public void parse(String clientInput) {

        String[] input = clientInput.split(" ", 1);
        setCommand(input[0]);

    }

    public void setCommand(String cmd) {
        command = cmd.toUpperCase();
    }

    public void setArgument(String arg) {
        argument = arg;
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

}
