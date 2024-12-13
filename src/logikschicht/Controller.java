package logikschicht;

import datenhaltungsschicht.DBZugriff;
import datenhaltungsschicht.DBZugriffInsert;
import datenhaltungsschicht.DBZugriffSelect;
import datenhaltungsschicht.Logger;
import praesentationsschicht.Ausgabe;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class Controller {
//      Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen user123 abonniert haben.
    private static String sqlString1 =
            "SELECT Benutzername " +
            "FROM (Konto k JOIN Abonniert a ON k.Benutzername = a.AbonnentID) " +
            "WHERE a.abonniertid = ?";

//      Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen user123 verfasst, erstellt
//      und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.
    //TODO: Daten werden Doppelt ausgegeben
    private static String sqlString2 ="SELECT " +
        "    v.videoID AS erstellt_videoid," +
        "    v.titel," +
        "    k.kommentarid," +
        "    k.inhalt," +
        "    b.videoid AS bewertet_videoid," +
        "    b.typ," +
        "    v2.videoid AS geschaut_videoid," +
        "    v2.titel AS geschaut_titel " +
        "FROM (" +
        "    (" +
        "        video v " +
        "        LEFT JOIN kommentar k ON v.benutzername = k.benutzername" +
        "    ) " +
        "    LEFT JOIN bewertet b ON k.benutzername = b.benutzername" +
        ") " +
        "LEFT JOIN (" +
        "    geschaut g " +
        "    INNER JOIN video v2 ON g.videoid = v2.videoid" +
        ") ON b.benutzername = g.benutzername " +
        "WHERE v.benutzername = ? " +
        "UNION " +
        "SELECT " +
        "    v.videoID AS erstellt_videoid, " +
        "    v.titel, " +
        "    k.kommentarid, " +
        "    k.inhalt, " +
        "    b.videoid AS bewertet_videoid, " +
        "    b.typ, " +
        "    v2.videoid AS geschaut_videoid, " +
        "    v2.titel AS geschaut_titel " +
        "FROM (" +
        "    (" +
        "        kommentar k " +
        "        RIGHT JOIN video v ON v.benutzername = k.benutzername" +
        "    ) " +
        "    RIGHT JOIN bewertet b ON k.benutzername = b.benutzername" +
        ") " +
        "RIGHT JOIN (" +
        "    geschaut g " +
        "    INNER JOIN video v2 ON g.videoid = v2.videoid" +
        ") ON b.benutzername = g.benutzername " +
        "WHERE v.benutzername = ?";

//      Finde alle Videos, die die Werbung des Werbepartners Nvidia geschaltet haben und zur Kategorie Gaming gehören.
    private static String sqlString3 = "";

//      Sortiere alle Konten absteigend basierend auf ihrer Abonnenten Anzahl
    private static String sqlString4 = "";

//      Finde alle Kommentare die am 4.09.2024 verfasst wurden
    private static String sqlString5 = "";

//      Abfrage mit Tiefensuche


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
    public static void executeQuery3(String werbepartner, String kategorie) throws Exception {
        try {
            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery3(sqlString2, werbepartner, kategorie)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }

    public static void executeQuery4() throws Exception {
        try {

            DBZugriff.connect();
            Ausgabe.print(Objects.requireNonNull(DBZugriffSelect.executeQuery4(sqlString2)));
            DBZugriff.close();
        } catch (NullPointerException ex) {
            String errorMessage = "Die Anfrage hat nichts zurückgeliefert";
            System.out.println(errorMessage);
            Logger.logError(ex);
            throw new NullPointerException(errorMessage);
        }
    }

    public static void executeQuery5(Date datum) throws Exception {
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

    public static boolean executeInsert(String query) throws SQLException {
        Ausgabe.print(Objects.requireNonNull(DBZugriffInsert.execute(query, "r")));
        return true;
    }
}
