package praesentationsschicht;

import datenhaltungsschicht.Logger;
import logikschicht.ControllerInsert;
import logikschicht.ControllerSearch;
import logikschicht.ControllerSelect;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eingabe {
    /**
     * Starts the main program loop, allowing the user to select and execute various actions such as SQL queries, inserting tuples into recursive relationships, and performing depth-first searches on comments.
     * The method prompts the user to enter a number from 1 to 4 to select the desired action, and then executes the corresponding logic. The loop continues until the user chooses to exit the program.
     * If the user enters an invalid input, the method will catch the exception and prompt the user to try again.
     */
    public static void start() {
        try {
            Scanner sc = new Scanner(System.in);
            boolean auswahlAktion = true;
            while (auswahlAktion) {
                System.out.println("""
                        Sie können sich durch eine Eingabe einer Zahl von 1-3 zwischen den folgenden Aktionen
                        1. Eine SQL-Abfrage auswählen,
                        2. Ein Tupel in eine Rekursive Beziehung einfügen
                        3. Tiefensuche bei den Kommentaren durchführen
                        4. Das Programm beenden
                        """);
                System.out.print("Eingabe:");
                int eingabeAktion = sc.nextInt();
                System.out.println("\n");
                switch (eingabeAktion) {
                    case 1 -> sqlAnfragen(sc);
                    case 2 -> rekursiveBeziehung(sc);
                    case 3 -> tiefensucheKommentare(sc);
                    case 4 -> auswahlAktion = false;
                    default ->
                            System.out.println("Ihre Eingabe '" + eingabeAktion +"' ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 1 und 3\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ihre Eingabe ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 1 und 3\n");
            start();
            Logger.logError(e);
        } catch (Exception e){
            Logger.logError(e);
        }
    }

    /**
     * Allows the user to select and execute various SQL queries from a menu.
     * The user is presented with a list of 5 different SQL queries they can choose from.
     * For each query, the user is prompted to enter any necessary input parameters, such as a username or a date.
     * The selected query is then executed and the results are displayed.
     * The method continues to prompt the user for new queries until the user chooses to exit.
     *
     * @param sc the Scanner object used for user input
     * @throws Exception if an error occurs during the query execution
     */
    public static void sqlAnfragen (Scanner sc) throws Exception{
        boolean weiteren = true;
        while (weiteren) {
            System.out.println("Sie können sich durch eine Eingabe einer Zahl von 1-5 zwischen den folgenden DB-Anfragen entscheiden");
            System.out.println("1. Finde alle Benutzernamen der Konten, die das Konto mit dem Benutzernamen XY abonniert haben.\n");
            System.out.println("2. Finde alle Kommentare, Videos und Bewertungen, die das Konto mit dem Benutzernamen XY verfasst, erstellt\n" +
                    "   und bewertet hat. Finde auch alle Videos, die das Konto geschaut hat.\n");
            System.out.println("3. Finde alle Videos, die die Werbung des Werbepartners X geschaltet haben und zur Kategorie Y gehören.\n");
            System.out.println("4. Sortiere alle Konten absteigend basierend auf ihrer Abonnenten Anzahl.\n");
            System.out.println("5. Finde alle Kommentare die am DD.MM.YYYY verfasst wurden.\n");
            System.out.print("Eingabe(1-5):");

            int eingabe = sc.nextInt();

            switch (eingabe) {
                case 1 -> {
                    System.out.print("\nWählen sie einen Benutzernamen für die Abfrage aus:");
                    String benutzername = sc.next();
                    ControllerSelect.executeQuery1(benutzername);
                }
                case 2 -> {
                    System.out.print("\nWählen sie einen Benutzernamen für die Abfrage aus:");
                    String benutzername = sc.next();
                    ControllerSelect.executeQuery2(benutzername);
                }
                case 3 -> {
                    System.out.print("\nWählen Sie einen Werbepartner für die Abfrage aus:");
                    String werbepartner = sc.next();
                    System.out.print("\nWählen Sie eine Kategorie für die Abfrage aus:");
                    String kategorie = sc.next();
                    ControllerSelect.executeQuery3(werbepartner, kategorie);
                }
                case 4 -> ControllerSelect.executeQuery4();
                case 5 -> {
                    System.out.print("\nWählen Sie ein Datum für die Abfrage aus:");
                    String datum = sc.next();
                    ControllerSelect.executeQuery5(datum);
                }
                default ->
                        System.out.println("\nIhre Eingabe ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 1 und 5\n");
            }

            System.out.println("\nWollen Sie eine weitere DB-Anfrage auswählen? (j/n)");
            System.out.print("Eingabe:");
            boolean fehlgeschlagen = false;
            do {
                String auswahl = sc.next();
                if (auswahl.equals("n")) {
                    weiteren = false;
                    System.out.println("\n");
                } else if (auswahl.equals("j")) {
                    System.out.println("\n");
                } else {
                    System.out.println("Ihre Eingabe '" + auswahl + "' ist fehlerhaft bitte wählen Sie entweder 'j' oder 'n'");
                    fehlgeschlagen = true;
                }
            }
            while (fehlgeschlagen);
        }
    }

    /**
     * Allows the user to insert new tuples into the Kommentar and Abonniert tables.
     * The user is presented with a menu to choose between inserting a new comment or a new subscription.
     * For inserting a new comment, the user is prompted to enter the message content, the ID of the parent comment (or 0 if it's a top-level comment), the ID of the video being commented on, and the username of the commenter.
     * For inserting a new subscription, the user is prompted to enter the username of the subscriber and the username of the subscribed user.
     * The method continues to prompt the user for new tuples until the user chooses to exit.
     *
     * @param sc the Scanner object used for user input
     * @throws Exception if an error occurs during the insert operations
     */
    public static void rekursiveBeziehung (Scanner sc) throws Exception{
        boolean weiteresTupel = true;
        while (weiteresTupel) {
            System.out.println("""
                    Wollen Sie ein Tupel
                    1. In die Tabelle Kommentar einfügen
                    2. In die Tabelle Abonniert einfügen
                    3. Zurück zur Auswahl
                    """);
            System.out.print("Eingabe:");
            int eingabeAktionTupel = sc.nextInt();
            System.out.println("\n");
            switch (eingabeAktionTupel) {
                case 1 -> {
                    boolean weiteren = true;
                    while (weiteren) {
                        System.out.print("\nGeben Sie einen Nachricht an:");
                        String inhalt = sc.next();
                        System.out.print("\nFalls Sie einen Kommentar unterkommentieren wollen, geben sie bitte dessen ID an oder '0' falls nicht:");
                        int elternKommentarID = sc.nextInt();
                        System.out.print("\nGeben Sie die ID des zu kommentierenden Videos an:");
                        int videoID = sc.nextInt();
                        System.out.print("\nGeben Sie ihren Benutzernamen an:");
                        String benutzernamen = sc.next();
                        if (ControllerInsert.executeInsertKommentar(inhalt, elternKommentarID, videoID, benutzernamen)) {
                            System.out.println("\nIhr Tupel wurde erfolgreich eingefügt");
                        }

                        System.out.println("\nWollen Sie ein weiteres Tupel einfügen? (j/n)");
                        boolean fehlgeschlagen = false;
                        do {
                            String auswahl = sc.next();
                            if (auswahl.equals("n")) {
                                weiteren = false;
                                System.out.println("\n");
                            } else if (auswahl.equals("j")) {
                                System.out.println("\n");
                            } else {
                                System.out.println("Ihre Eingabe '" + auswahl + "' ist fehlerhaft bitte wählen Sie entweder 'j' oder 'n'");
                                fehlgeschlagen = true;
                            }
                        }
                        while (fehlgeschlagen);
                    }
                }
                case 2 -> {
                    boolean weiteren = true;
                    while (weiteren) {
                        System.out.print("\nGeben Sie den Benutzernamen des Abonnenten an:");
                        String abonnent = sc.next();
                        System.out.print("\nGeben Sie den Benutzernamen des Abonnierten an:");
                        String abonnierter = sc.next();
                        if (ControllerInsert.executeInsertAbonniert(abonnent, abonnierter)) {
                            System.out.println("\nIhr Tupel wurde erfolgreich eingefügt");
                        }
                        System.out.println("\nWollen Sie ein weiteres Tupel einfügen? (j/n)");
                        boolean fehlgeschlagen = false;
                        do {
                            String auswahl = sc.next();
                            if (auswahl.equals("n")) {
                                weiteren = false;
                                System.out.println("\n");
                            } else if (auswahl.equals("j")) {
                                System.out.println("\n");
                            } else {
                                System.out.println("Ihre Eingabe '" + auswahl + "' ist fehlerhaft bitte wählen Sie entweder 'j' oder 'n'");
                                fehlgeschlagen = true;
                            }
                        }
                        while (fehlgeschlagen);
                    }
                }
                case 3 -> weiteresTupel = false;
                default -> System.out.println("Ihre Eingabe '" + eingabeAktionTupel + "' ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 1 und 3");
            }
        }
    }

    /**
     * Performs a depth-first search to display the sub-comments of a given comment.
     * The user is prompted to enter the ID of the comment they want to see the sub-comments for.
     * The method then calls the `ControllerSearch.executeSearch()` method to retrieve and display the sub-comments.
     * The user is then prompted to continue searching for more sub-comments or to exit.
     *
     * @param sc the `Scanner` object used for user input
     * @throws Exception if an error occurs during the search or display of sub-comments
     */
    public static void tiefensucheKommentare(Scanner sc) throws Exception{
        boolean weiteren = true;
        while (weiteren) {
            System.out.print("\nWählen Sie die ID des Kommentars für den Sie die Unterkommentare sehen wollen:");
            int kommentarID = sc.nextInt();
            ControllerSearch.executeSearch(kommentarID);
            System.out.println("\nWollen Sie nach weiteren Unterkommentaren suchen? (j/n)");
            boolean fehlgeschlagen = false;
            do {
                String auswahl = sc.next();
                if (auswahl.equals("n")) {
                    weiteren = false;
                    System.out.println("\n");
                } else if (auswahl.equals("j")) {
                    System.out.println("\n");
                } else {
                    System.out.println("Ihre Eingabe '" + auswahl + "' ist fehlerhaft bitte wählen Sie entweder 'j' oder 'n'");
                    fehlgeschlagen = true;
                }
            }
            while (fehlgeschlagen);
        }
    }
}
