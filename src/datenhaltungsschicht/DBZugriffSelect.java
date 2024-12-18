package datenhaltungsschicht;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBZugriffSelect extends DBZugriff {
    /**
     * Executes a SQL query and returns the result set.
     *
     * @param query        the SQL query to execute
     * @param benutzername the username to use in the query
     * @return the result set of the executed query
     * @throws SQLException if an error occurs while executing the query
     */
    public static ResultSet executeQuery1(String query, String benutzername) throws SQLException {
        try {
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, benutzername);
            ResultSet resultSet = preparedBefehl.executeQuery();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    /**
     * Executes a SQL query and returns the result set.
     *
     * @param query        the SQL query to execute
     * @param benutzername the username to use in the query
     * @return the result set of the executed query
     * @throws SQLException if an error occurs while executing the query
     */
    public static ResultSet executeQuery2(String query, String benutzername) throws SQLException {
        try {
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, benutzername);
            preparedBefehl.setString(2, benutzername);
            preparedBefehl.setString(3, benutzername);
            preparedBefehl.setString(4, benutzername);
            ResultSet resultSet = preparedBefehl.executeQuery();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    /**
     * Executes a SQL query and returns the result set.
     *
     * @param query        the SQL query to execute
     * @param werbepartner the advertising partner to use in the query
     * @param kategorie    the category to use in the query
     * @return the result set of the executed query
     * @throws SQLException if an error occurs while executing the query
     */
    public static ResultSet executeQuery3(String query, String werbepartner, String kategorie) throws SQLException {
        try {
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, werbepartner);
            preparedBefehl.setString(2, kategorie);
            ResultSet resultSet = preparedBefehl.executeQuery();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    /**
     * Executes a SQL query and returns the result set.
     *
     * @param query the SQL query to execute
     * @return the result set of the executed query
     * @throws SQLException if an error occurs while executing the query
     */
    public static ResultSet executeQuery4(String query) throws SQLException {
        try {
            ResultSet resultSet = befehl.executeQuery(query);
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    /**
     * Executes a SQL query and returns the result set.
     *
     * @param query the SQL query to execute
     * @param datum the date parameter to use in the query
     * @return the result set of the executed query
     * @throws SQLException if an error occurs while executing the query
     */
    public static ResultSet executeQuery5(String query, String datum) throws SQLException {
        try {
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, datum);
            ResultSet resultSet = preparedBefehl.executeQuery();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
}
