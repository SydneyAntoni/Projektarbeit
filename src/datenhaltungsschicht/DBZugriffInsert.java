package datenhaltungsschicht;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBZugriffInsert extends DBZugriff {
    public static ResultSet execute(String query, String benutzername) throws SQLException {
        try {
            connect();
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, benutzername);
            ResultSet resultSet = preparedBefehl.executeQuery();
            close();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
}
