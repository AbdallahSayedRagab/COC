package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtiles {
    public static String Logs_path = "E:\\Automation course\\PAGE_OBJECT_MODEL\\src\\test\\Logs";

    public static void trace (String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).trace(message);

    }
    public static void debug (String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).debug(message);

    }
    public static void info  (String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).info(message);

    }
    public static void warn (String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).warn(message);

    }
}
