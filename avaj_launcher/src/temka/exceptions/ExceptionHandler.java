package temka.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler extends Exception {

    Map<String, String> errMap = new HashMap<String, String>();

    public ExceptionHandler(String filename, Integer arg_num) {
        fillErrMessage(filename, arg_num);
    }

    public ExceptionHandler() {
        super();
        fillErrMessage("file", 1);
    }

    private void fillErrMessage(String filename, Integer arg_num) {
        errMap.put("EmptyFile",         " > " + filename + " <  Has No Content!");
        errMap.put("ArgNum",            " [" + arg_num + "] Arguments Given But [1] Expected.");
        errMap.put("InvalidFile",            " > " + filename + " <  Is Not A Valid File!");
        errMap.put("Directory",         " > " + filename + " <  Is A Directory But File Was Expected!");
        errMap.put("InvalidPath",            " > " + filename + " <  Is Not A Valid Path!");
        errMap.put("[InvCharacters]",          "Contains Invalid Characters!");
        errMap.put("[InvInt]",      "Illegal value of simulations!");
        errMap.put("", "");
    }

    public ExceptionHandler(String message) {
        super(message);
    }

    public ExceptionHandler(Throwable cause) {
        super(cause);
    }

    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public void errorMessage(String err_message, Boolean _exit_) {
        System.out.println((char)27 + "[34;31m" + errMap.get(err_message) + (char)27 + "[0m");
        if (_exit_) {
            System.exit(0);            
        }
    }
}