package datenhaltungsschicht;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBZugriffInsert extends DBZugriff {
    /**
     * Executes a SQL query to insert a new comment into the database.
     *
     * @param query The SQL query to execute.
     * @param inhalt The content of the comment.
     * @param elternKommentarID The ID of the parent comment, or 0 if this is a top-level comment.
     * @param videoID The ID of the video the comment is associated with.
     * @param benutzername The username of the user who posted the comment.
     * @return true if the comment was successfully inserted, false otherwise.
     * @throws SQLException if an error occurs while executing the SQL query.
     */
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
    /**
     * Executes a SQL query to insert a new subscription relationship into the database.
     *
     * @param query The SQL query to execute.
     * @param abonnent The username of the subscriber.
     * @param abonniert The username of the subscribed-to user.
     * @return true if the subscription was successfully inserted, false otherwise.
     * @throws SQLException if an error occurs while executing the SQL query.
     */
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
