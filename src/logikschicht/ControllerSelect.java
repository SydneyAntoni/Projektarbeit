package logikschicht;

import datenhaltungsschicht.DBZugriff;
import datenhaltungsschicht.DBZugriffSelect;
import datenhaltungsschicht.Logger;
import praesentationsschicht.Ausgabe;

import java.util.Objects;

public class ControllerSelect {
//      Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen user123 abonniert haben.
    private static String sqlString1 =
            "SELECT Benutzername\n" +
            "FROM (Konto k JOIN Abonniert a ON k.Benutzername = a.AbonnentID)\n" +
            "WHERE a.abonniertid = ?";

//      Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen user123 verfasst, erstellt
//      und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.
    private static String sqlString2 ="SELECT 'Kommentar' AS Typ, k.KommentarID AS ID, v.Titel AS VideoTitel, " +
        "    k.Inhalt AS KommentarInhalt, k.Verfassungsdatum " +
        "FROM Kommentar k INNER JOIN Video v ON k.VideoID = v.VideoID\n" +
        "WHERE k.Benutzername = ?\n" +
        "UNION\n" +
        "SELECT 'Video' AS Typ, v.VideoID AS ID, v.Titel AS VideoTitel, NULL AS KommentarInhalt, v.Veröffentlichungsdatum\n" +
        "FROM Video v\n" +
        "WHERE v.Benutzername = ?\n" +
        "UNION\n" +
        "SELECT 'Bewertung' AS Typ, b.VideoID AS ID, v.Titel AS VideoTitel, b.Typ AS BewertungTyp, NULL AS Verfassungsdatum\n" +
        "FROM Bewertet b INNER JOIN Video v ON b.VideoID = v.VideoID\n" +
        "WHERE b.Benutzername = ?\n" +
        "UNION\n" +
        "SELECT 'Geschaut' AS Typ, g.VideoID AS ID, v.Titel AS VideoTitel, NULL AS KommentarInhalt, g.Datum AS Ansichtsdatum\n" +
        "FROM Geschaut g\n" +
        "INNER JOIN Video v ON g.VideoID = v.VideoID\n" +
        "WHERE g.Benutzername = ?;";

//      Finde alle Videos, die die Werbung des Werbepartners X geschaltet haben und zur Kategorie Y gehören.
    private static String sqlString3 = "SELECT v.VideoID, v.Titel, v.Kategorie, v.Videolaenge, w.Werbepartner\n" +
        "FROM Video v\n" +
        "   INNER JOIN Monetarisiert m ON v.VideoID = m.VideoID\n" +
        "   INNER JOIN Werbung w ON m.WerbungID = w.WerbungID\n" +
        "WHERE w.Werbepartner = ? AND v.Kategorie = ?;";

//      Sortiere alle Konten absteigend basierend auf ihrer Abonnenten Anzahl
    private static String sqlString4 = "SELECT k.Benutzername, COUNT(a.AbonnentID) AS AbonnentenAnzahl\n" +
        "FROM Konto k LEFT JOIN Abonniert a ON k.Benutzername = a.AbonniertID\n" +
        "GROUP BY k.Benutzername\n" +
        "ORDER BY AbonnentenAnzahl DESC;";

//      Finde alle Kommentare die am 4.09.2024 verfasst wurden
    private static String sqlString5 = "SELECT KommentarID, Inhalt, Verfassungsdatum, Benutzername, VideoID, ElternKommentarID\n" +
        "FROM Kommentar\n" +
        "WHERE Verfassungsdatum = STR_TO_DATE(?, '%d.%m.%Y');";

//      Abfrage mit Tiefensuche


    /**
     * Executes a SQL query to retrieve the usernames of accounts that have subscribed to the account with the username "user123".
     *
     * @param benutzername the username of the account to retrieve subscribed accounts for
     * @throws Exception if the query fails to execute or returns no results
     */
    public static void executeQuery1(String benutzername) throws Exception {
        try {
            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery1(sqlString1, benutzername)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }

    /**
     * Executes a SQL query to retrieve data based on the provided username.
     *
     * @param benutzername the username to use in the query
     * @throws Exception if the query fails to execute or returns no results
     */
    public static void executeQuery2(String benutzername) throws Exception {
        try {
            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery2(sqlString2, benutzername)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }
    /**
     * Executes a SQL query to retrieve data based on the provided advertising partner and category.
     *
     * @param werbepartner the advertising partner to use in the query
     * @param kategorie the category to use in the query
     * @throws Exception if the query fails to execute or returns no results
     */
    public static void executeQuery3(String werbepartner, String kategorie) throws Exception {
        try {
            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery3(sqlString3, werbepartner, kategorie)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }

    /**
     * Executes a SQL query to retrieve data.
     *
     * @throws Exception if the query fails to execute or returns no results
     */
    public static void executeQuery4() throws Exception {
        try {

            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery4(sqlString4)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }

    /**
     * Executes a SQL query to retrieve data based on the provided date.
     *
     * @param datum the date to use in the query
     * @throws Exception if the query fails to execute or returns no results
     */
    public static void executeQuery5(String datum) throws Exception {
        try {
        DBZugriff.connect();
        Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery5(sqlString5, datum)));
        DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }
}
