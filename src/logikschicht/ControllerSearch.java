package logikschicht;

import datenhaltungsschicht.DBZugriff;
import datenhaltungsschicht.DBZugriffSearch;
import datenhaltungsschicht.Logger;
import praesentationsschicht.Ausgabe;

import java.util.Objects;

public class ControllerSearch {
    private static String sqlString = "WITH RECURSIVE KommentarHierarchie AS (\n" +
            "    -- Start der Rekursion: Vorgegeben Kommentar.\n" +
            "    SELECT  KommentarID, ElternKommentarID, VideoID, Benutzername, Inhalt, Verfassungsdatum,\n" +
            "        CAST(KommentarID AS CHAR(255)) AS HierarchiePfad -- Initialisierung mit ausreichender Länge\n" +
            "    FROM Kommentar\n" +
            "    WHERE KommentarID = ?\n" +
            "    UNION ALL\n" +
            "    -- Rekursiver Schritt: Hole alle Unterkommentare.\n" +
            "    SELECT child.KommentarID, child.ElternKommentarID, child.VideoID, child.Benutzername, child.Inhalt, child.Verfassungsdatum,\n" +
            "        CAST(CONCAT(KommentarHierarchie.HierarchiePfad, ' -> ', child.KommentarID) AS CHAR(255)) AS HierarchiePfad\n" +
            "    FROM Kommentar child\n" +
            "    INNER JOIN KommentarHierarchie \n" +
            "        ON child.ElternKommentarID = KommentarHierarchie.KommentarID\n" +
            ")\n" +
            "-- Endgültige Abfrage: Sortiere die Ergebnisse, um die Hierarchie zu sehen.\n" +
            "SELECT KommentarID, ElternKommentarID, VideoID, Benutzername, Inhalt, Verfassungsdatum, HierarchiePfad\n" +
            "FROM KommentarHierarchie\n" +
            "ORDER BY HierarchiePfad;";

    /**
     * Executes a search query to retrieve a hierarchy of comments based on the provided comment ID.
     * The method connects to the database, executes the search query, and prints the results.
     * If the query returns no results, an error message is logged and a NullPointerException is thrown.
     *
     * @param kommentarID the ID of the comment to start the search from
     * @throws Exception if an error occurs during the database connection or query execution
     */
    public static void executeSearch(int kommentarID) throws Exception{
        try {
            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSearch.executeSearch(sqlString, kommentarID)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }

}
