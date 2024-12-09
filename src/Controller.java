import java.util.Objects;
import java.util.Scanner;

public class Controller {
//      Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen user123 abonniert haben.
    private static String sqlString1 =
            "SELECT Benutzername " +
                    "FROM (Konto k JOIN Abonniert a ON k.Benutzername = a.AbonnentID) " +
                    "WHERE a.abonniertid = 'Max'";

//      Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen user123 verfasst, erstellt
//      und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.
    private static String sqlString2 =
            "SELECT v.videoID as erstellt_videoid, v.titel, k.kommentarid, k.inhalt, " +
                    "b.videoid as bewertet_videoid, b.typ, v2.videoid as geschaut_videoid, v2.titel as geschaut_titel " +
                    "FROM (((video v FULL OUTER JOIN kommentar k ON v.benutzername = k.benutzername) " +
                    "FULL OUTER JOIN bewertet b ON k.benutzername = b.benutzername) " +
                    "FULL OUTER JOIN (geschaut g INNER JOIN Video v2 ON g.videoid = v2.videoid) ON b.benutzername = g.benutzername) " +
                    "WHERE v.benutzername = 'Max'";

//      Finde alle Videos, die die Werbung des Werbepartners Nvidia geschaltet haben und zur Kategorie Gaming gehören.
    private static String sqlString3 = "";

//      Sortiere alle Konten absteigend basierend auf ihrer Abonnenten Anzahl
    private static String sqlString4 = "";

//      Finde alle Kommentare die am 4.09.2024 verfasst wurden
    private static String sqlString5 = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean weiteren = true;
        while (weiteren) {
            System.out.println("Sie können sich durch eine Eingabe einer Zahl von 1-5 zwischen den folgenden DB-Anfragen entscheiden");
            System.out.println("1.");
            System.out.println("Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen user123 abonniert haben. \n");
            System.out.println("2.");
            System.out.println("Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen user123 verfasst, erstellt\n" +
                    "und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.\n");
            System.out.println("3.");
            System.out.println("Finde alle Videos, die die Werbung des Werbepartners Nvidia geschaltet haben und zur Kategorie Gaming gehören.\n");
            System.out.println("4.");
            System.out.println("Sortiere alle Konten absteigend basierend auf ihrer Abonnenten Anzahl.\n");
            System.out.println("5.");
            System.out.println("Finde alle Kommentare die am 4.09.2024 verfasst wurden.\n");
            System.out.print("Eingabe(1-5):");
            String eingabe = sc.next();
            switch (eingabe) {
                //TODO
                case "1" -> View.print(Objects.requireNonNull(Model.execute(sqlString1)));
                case "2" -> View.print(Objects.requireNonNull(Model.execute(sqlString2)));
                case "3" -> View.print(Objects.requireNonNull(Model.execute(sqlString3)));
                case "4" -> View.print(Objects.requireNonNull(Model.execute(sqlString4)));
                case "5" -> View.print(Objects.requireNonNull(Model.execute(sqlString5)));
                default -> System.out.println("Ihre Eingabe '" + eingabe + "' ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 0 und 4 aus");
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
}
