package datenhaltungsschicht;

import java.sql.*;

public class DBZugriff {
    protected static Connection con;
    private static String url = "jdbc:mysql://localhost/feedback?";
    private static String user = "sqluser";
    private static String password = "sqluserpw";
    protected static Statement befehl;
    protected static PreparedStatement preparedBefehl;

    /**
     * Establishes a connection to the database using the configured URL, username, and password.
     * If the connection is successfully established, a statement object is created and the auto-commit mode is set to true.
     * If an error occurs during the connection process, an error message is printed and the exception is logged.
     *
     * @throws SQLException if an error occurs while connecting to the database
     */
    public static void connect() throws SQLException {
        try {
            con = DriverManager.getConnection(url, user, password);
            befehl = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Herstellen der Verbindung zur Datenbank aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }

    /**
     * Closes the database connection if it is not null.
     * If an error occurs while closing the connection, an error message is printed and the exception is logged.
     *
     * @throws SQLException if an error occurs while closing the database connection
     */
    public static void close() throws SQLException {
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Schließen der Verbindung zur Datenbank aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
}
