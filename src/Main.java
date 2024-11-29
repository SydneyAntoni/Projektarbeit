import java.sql.*;
import java.util.Scanner;

public class Main {
    private static String url = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    private static String user = "C##FBPOOL31";
    private static String password = "oracle";

    public static void main(String[] args) {
//      Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen user123 abonniert haben.
        String sqlString1 =
                "SELECT Benutzername " +
                "FROM (Konto k JOIN Abonniert a ON k.Benutzername = a.AbonnentID) " +
                "WHERE a.abonniertid = 'Max'";

//      Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen user123 verfasst, erstellt
//      und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.
        String sqlString2 =
                "SELECT v.videoID as erstellt_videoid, v.titel, k.kommentarid, k.inhalt, " +
                    "b.videoid as bewertet_videoid, b.typ, v2.videoid as geschaut_videoid, v2.titel as geschaut_titel " +
                "FROM (((video v FULL OUTER JOIN kommentar k ON v.benutzername = k.benutzername) " +
                    "FULL OUTER JOIN bewertet b ON k.benutzername = b.benutzername) " +
                    "FULL OUTER JOIN (geschaut g INNER JOIN Video v2 ON g.videoid = v2.videoid) ON b.benutzername = g.benutzername) " +
                "WHERE v.benutzername = 'Max'";

//      Finde alle Videos, die die Werbung des Werbepartners Nvidia geschaltet haben und zur Kategorie Gaming gehören.
        String sqlString3 = "";

//      Sortiere alle Konten absteigend basierend auf ihrer Abonnenten Anzahl
        String sqlString4 = "";

        Scanner sc = new Scanner(System.in);
        boolean weiteren = true;
        while (weiteren) {
            System.out.println("Sie können sich durch eine Eingabe einer Zahl von 1-4 zwischen den folgenden DB-Anfragen entscheiden oder mit 0 das Programm beenden");
            System.out.println("1.");
            System.out.println("Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen user123 abonniert haben. \n");
            System.out.println("2.");
            System.out.println("Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen user123 verfasst, erstellt\n" +
                                "und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.\n");
            System.out.println("3.");
            System.out.println("4.");
            System.out.print("Eingabe(0-4):");
            String eingabe = sc.next();
            switch (eingabe){
                case "1":
                    System.out.println("\nErgebnis:");
                    execute(sqlString1);
                    System.out.println();
                    break;
                case "2":
                    System.out.println("\nErgebnis:");
                    execute(sqlString2);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("\nErgebnis:");
                    execute(sqlString3);
                    System.out.println();
                    break;
                case "4":
                    System.out.println("\nErgebnis:");
                    execute(sqlString4);
                    System.out.println();
                    break;
                default:
                    System.out.println("Ihre Eingabe '" + eingabe + "' ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 0 und 4 aus");
            }
            System.out.println("Wollen sie eine weitere DB-Anfrage auswählen? (j/n)");
            boolean fehlgeschlagen = false;
            do {
                String auswahl = sc.next();
                if (auswahl.equals("n")){
                    weiteren = false;
                    fehlgeschlagen = false;
                } else if (auswahl.equals("j")){
                    fehlgeschlagen = false;
                } else {
                    System.out.println("Ihre Eingabe '" + auswahl + "' ist fehlerhaft bitte wählen Sie entweder 'j' oder 'n'");
                    fehlgeschlagen = true;
                }
            }
            while (fehlgeschlagen);

        }
    }

    public static void execute(String query) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            con.setAutoCommit(true);
            Statement befehl = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet results = befehl.executeQuery(query);
            ResultSetMetaData metaData = results.getMetaData();
            int colCount = metaData.getColumnCount();

            // Bestimmt die Breite jedes Spaltennamens
            int[] colWidths = new int[colCount];
            for (int i = 1; i <= colCount; i++) {
                colWidths[i-1] = metaData.getColumnName(i).length();
            }

            // Bestimmt den breitesten Inhalt einer Spalte inkl. Spaltennamen
            while (results.next()) {
                for (int i = 1; i <= colCount; i++) {
                    String value = results.getString(i);
                    if (value != null) {
                        colWidths[i-1] = Math.max(colWidths[i-1], value.length());
                    }
                }
            }

            // Gibt Spaltenkopf aus
            printTableLine(colWidths);
            System.out.print("|");
            for (int i = 1; i <= colCount; i++) {
                System.out.printf(" %-" + colWidths[i-1] + "s |", metaData.getColumnName(i));
            }
            System.out.println();
            printTableLine(colWidths);

            // Gibt Spaltendaten aus
            results.beforeFirst();
            while (results.next()) {
                System.out.print("|");
                for (int i = 1; i <= colCount; i++) {
                    String value = results.getString(i);
                    System.out.printf(" %-" + colWidths[i-1] + "s |", (value != null ? value : ""));
                }
                System.out.println();
            }
            printTableLine(colWidths);

            befehl.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void printTableLine(int[] colWidths) {
        System.out.print("+");
        for (int width : colWidths) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }
}