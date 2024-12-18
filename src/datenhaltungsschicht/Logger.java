package datenhaltungsschicht;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Logger {
    private static final String LOG_FILE_PATH = System.getProperty("user.dir") + "/";
    private static final String LOG_FILE_NAME = "log.txt";

    /**
     * Logs an error message and the associated stack trace to the application log file.
     *
     * @param e the Exception object containing the error message and stack trace to be logged
     */
    public static void logError(Exception e) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH + LOG_FILE_NAME, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)){

            SimpleDateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date timestamp = new Date();

            printWriter.println("Timestamp: " + dateFormat.format(timestamp));
            printWriter.println("Error Message: " + e.getMessage());
            printWriter.println("Stack Trace:");
            e.printStackTrace(printWriter);
            printWriter.println("\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Logs the execution of an SQL command to the application log file.
     *
     * @param befehl the SQL command to be logged
     */
    //TODO
    public static void logCommand (String befehl) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH + LOG_FILE_NAME, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)){

            SimpleDateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date timestamp = new Date();

            printWriter.println("Timestamp: " + dateFormat.format(timestamp));
            printWriter.println("SQL-Command: " + befehl);
            printWriter.println("\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
