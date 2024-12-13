package datenhaltungsschicht;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class DBZugriffSelect extends DBZugriff {
    public static ResultSet executeQuery1(String query, String benutzername) throws SQLException {
        try {
            connect();
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
    public static ResultSet executeQuery2(String query, String benutzername) throws SQLException {
        try {
            connect();
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, benutzername);
            preparedBefehl.setString(2, benutzername);
            ResultSet resultSet = preparedBefehl.executeQuery();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    public static ResultSet executeQuery3(String query, String werbepartner, String kategorie) throws SQLException {
        try {
            connect();
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
    public static ResultSet executeQuery4(String query) throws SQLException {
        try {
            connect();
            ResultSet resultSet = befehl.executeQuery(query);
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    public static ResultSet executeQuery5(String query, Date datum) throws SQLException {
        try {
            connect();
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setDate(1, datum);
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
