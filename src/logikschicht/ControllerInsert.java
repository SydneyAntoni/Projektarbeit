package logikschicht;

import datenhaltungsschicht.DBZugriffInsert;

import java.sql.SQLException;

public class ControllerInsert {
    private static final String kommentarInsert = "INSERT INTO Kommentar (inhalt, verfassungsDatum, elternKommentarID, videoID, benutzername) VALUES (?, ?, ?, ?, ?)";
    private static final String abonniertInsert = "INSERT INTO Abonniert (abonniertID, abonnentID) VALUES (?, ?)";

    /**
     * Executes an SQL insert statement to add a new comment to the database.
     *
     * @param inhalt The content of the comment.
     * @param elternKommentarID The ID of the parent comment, or 0 if this is a top-level comment.
     * @param videoID The ID of the video the comment is associated with.
     * @param benutzername The username of the user who posted the comment.
     * @return True if the insert was successful, false otherwise.
     * @throws SQLException If there is an error executing the SQL statement.
     */
    public static boolean executeInsertKommentar(String inhalt, int elternKommentarID, int videoID, String benutzername) throws SQLException {
        return DBZugriffInsert.executeKommentar(kommentarInsert, inhalt, elternKommentarID, videoID, benutzername);
    }
    /**
     * Executes an SQL insert statement to add a new subscription to the database.
     *
     * @param abonnent The ID of the user who is subscribing.
     * @param abonnierter The ID of the user being subscribed to.
     * @return True if the insert was successful, false otherwise.
     * @throws SQLException If there is an error executing the SQL statement.
     */
    public static boolean executeInsertAbonniert(String abonnent, String abonnierter) throws SQLException {
        return DBZugriffInsert.executeAbonniert(abonniertInsert, abonnent, abonnierter);
    }
}
