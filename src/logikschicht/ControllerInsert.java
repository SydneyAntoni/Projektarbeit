package logikschicht;

import datenhaltungsschicht.DBZugriffInsert;

import java.sql.SQLException;

public class ControllerInsert {
    private static final String kommentarInsert = "INSERT INTO Kommentar (inhalt, verfassungsDatum, elternKommentarID, videoID, benutzername) VALUES (?, ?, ?, ?, ?)";
    private static final String abonniertInsert = "INSERT INTO Abonniert (abonniertID, abonnentID) VALUES (?, ?)";

    public static boolean executeInsertKommentar(String inhalt, int elternKommentarID, int videoID, String benutzername) throws SQLException {
        return DBZugriffInsert.executeKommentar(kommentarInsert, inhalt, elternKommentarID, videoID, benutzername);
    }
    public static boolean executeInsertAbonniert(String abonnent, String abonnierter) throws SQLException {
        return DBZugriffInsert.executeAbonniert(abonniertInsert, abonnent, abonnierter);
    }
}
