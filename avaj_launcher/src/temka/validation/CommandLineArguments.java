package temka.validation;

import temka.exceptions.ExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandLineArguments {

    ExceptionHandler    exceptionHandler;

    private String[]    arguments;
    private String      fname;
    private Integer     arg_num;
    private File        file;

    public  Boolean     fileIsMd5 = false;

    public CommandLineArguments(String[] args) {

        this.arg_num  = args.length;

        if (this.arg_num > 0) {
            this.arguments          = args;
            this.fname           = args[0];
            this.file               = new File(this.fname);
            this.exceptionHandler   = new ExceptionHandler(this.fname, this.arg_num);
            isValidArg();
        } else {
            System.out.println((char)27 + "[34;31m [" + args.length + "] Arguments Given, But [1] Expected." + (char)27 + "[0m");
            System.exit(0);
        }
    }

    public void isValidArg() {

        if (arg_num != 1) {
            exceptionHandler.errorMessage("ArgNum", true);
        }

        if (!isPath()) {
            exceptionHandler.errorMessage("InvalidPath", true);
        }

        if (!isFile()) {
            if (isDir()) {
                exceptionHandler.errorMessage("Directory", true);
            }
            exceptionHandler.errorMessage("InvalidFile", true);
        }

        if (!fileNotEmpty()) {
            exceptionHandler.errorMessage("FileNotPopulated", true);
        }
    }

    public Boolean isMd5() {
        String[] fnameSplitted = this.fname.trim().split("\\.");
            if (fnameSplitted.length == 2 &&
                    fnameSplitted[fnameSplitted.length - 1].toLowerCase().equals("md5")) {
                return (true);
            }
        return (false);
    }

    private Boolean isPath() {
        return (this.file.exists());
    }

    private Boolean isFile() {
        return (this.file.isFile());
    }

    private Boolean isDir() {
        return (this.file.isDirectory());
    }

    private Boolean fileNotEmpty() {
        return ((this.file.length() > 0) ? true : false);
    }

    public String[] getArgs() {
        return (this.arguments);
    }

    public void setArgs(String[] _newargs) {
        this.arguments      = _newargs;
    }
}