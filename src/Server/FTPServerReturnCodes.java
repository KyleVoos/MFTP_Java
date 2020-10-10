package Server;

public final class FTPServerReturnCodes {

    // 100 Series - requested action initiated, expect another reply before new command
    public final static String DATA_CON_ALREADY_OPEN = "125 Data connection already open; transfer starting.";
    public final static String FILE_OK_CREATE_DATA_CON = "150 File status okay; about to open data connection.";

    // 200 Series - Requested action completed successfully
    public final static String OKAY = "200 Command okay.";
    public final static String NOT_IMPLEMENTED = "202 Command not implemented, superfluous at this site.";
    final static String NEW_USER = "220 Service ready for new user.";
    public final static String CLOSING_CONTROL_CON = "221 Service closing control connection.";
    public final static String CON_OPEN_NO_TRANSFER = "225 Data connection open; no transfer in progress.";
    public final static String CLOSING_DATA_CON = "226 Closing data connection.";
    public final static String PASSIVE_MODE = "227 Entering Passive Mode (%s,%d,%d)";

    // 300 Series - Command accepted, but action on hold until further information is received
    public final static String NEED_PASSWORD = "331 User name okay, need password.";
    public final static String NEED_ACCOUNT = "332 Need account for login.";

    // 400 Series - Command not accepted and action didn't take place. Temp error and action may be repeated.
    public final static String OPEN_DATA_CON_FAILED = "425 Can't open data connection.";
    public final static String TRANSFER_ABORTED = "426 Connection closed; transfer aborted.";
    public final static String FILE_ACTION_NOT_TAKEN = "450 Requested file action not taken.";
    public final static String ACTION_ABORTED_LOCAL_ERROR = "451 Requested action aborted: local error in processing.";
    public final static String ACTION_NOT_TAKEN = "452 Requested action not taken.";

    // 500 Series -
    public final static String SYNTAX_ERROR = "500 Syntax error, command unrecognized.";
    public final static String SYNTAX_ERROR_PARAM_OR_ARG = "501 Syntax error in parameters or arguments.";
    public final static String COMMAND_NOT_IMPLEMENTED = "502 Command not implemented.";
    public final static String UNAUTHORIZED = "530 Not logged in.";


}
