package datenhaltungsschicht;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBZugriffInsert extends DBZugriff {
    public static boolean executeKommentar(String query, String inhalt, int elternKommentarID, int videoID, String benutzername) throws SQLException {
        try {
            connect();
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, inhalt);
            preparedBefehl.setDate(2, new java.sql.Date(new Date().getTime()));

            if (elternKommentarID == 0) {
                preparedBefehl.setNull(3, java.sql.Types.INTEGER);
            } else {
                preparedBefehl.setInt(3, elternKommentarID);
            }
            preparedBefehl.setInt(4, videoID);
            preparedBefehl.setString(5, benutzername);
            preparedBefehl.executeUpdate();
            close();
            return true;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
    public static boolean executeAbonniert(String query, String abonnent, String abonniert) throws SQLException {
        try {
            connect();
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setString(1, abonnent);
            preparedBefehl.setString(2, abonniert);
            preparedBefehl.executeUpdate();
            close();
            return true;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
}
