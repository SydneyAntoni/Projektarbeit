package praesentationsschicht;

import datenhaltungsschicht.Logger;
import logikschicht.Controller;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eingabe {
    public static void start() {
        try {
            Scanner sc = new Scanner(System.in);
            boolean auswahlAktion = true;
            while (auswahlAktion) {
                System.out.println("""
                        Sie können sich durch eine Eingabe einer Zahl von 1-3 zwischen den folgenden Aktionen
                        1. Eine SQL-Abfrage auswählen,
                        2. Ein Tupel in eine Rekursive Beziehung einfügen
                        3. Das Programm beenden
                        """);
                System.out.print("Eingabe:");
                int eingabeAktion = sc.nextInt();
                System.out.println("\n");
                switch (eingabeAktion) {
                    case 1 -> sqlAnfragen(sc);
                    case 2 -> rekursiveBeziehung(sc);
                    case 3 -> auswahlAktion = false;
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
                    Controller.executeQuery1(benutzername);
                }
                case 2 -> {
                    System.out.print("\nWählen sie einen Benutzernamen für die Abfrage aus:");
                    String benutzername = sc.next();
                    Controller.executeQuery2(benutzername);
                }
                case 3 -> {
                    System.out.print("\nWählen sie einen Werbepartner für die Abfrage aus:");
                    String werbepartner = sc.next();
                    System.out.print("\nWählen sie eine Kategorie für die Abfrage aus:");
                    String kategorie = sc.next();
                    Controller.executeQuery3(werbepartner, kategorie);
                }
                case 4 -> Controller.executeQuery4();
                case 5 -> {
                    System.out.print("\nWählen sie ein Datum für die Abfrage aus:");
                    String datum = sc.next();
                    Controller.executeQuery5(Date.valueOf(datum));
                }
                default ->
                        System.out.println("\nIhre Eingabe ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 1 und 5\n");
            }

            System.out.println("\nWollen sie eine weitere DB-Anfrage auswählen? (j/n)");
            boolean fehlgeschlagen;
            do {
                String auswahl = sc.next();
                if (auswahl.equals("n")) {
                    weiteren = false;
                    fehlgeschlagen = false;
                    System.out.println("\n");
                } else if (auswahl.equals("j")) {
                    fehlgeschlagen = false;
                    System.out.println("\n");
                } else {
                    System.out.println("Ihre Eingabe '" + auswahl + "' ist fehlerhaft bitte wählen Sie entweder 'j' oder 'n'");
                    fehlgeschlagen = true;
                }
            }
            while (fehlgeschlagen);
        }
    }

    public static void rekursiveBeziehung (Scanner sc) throws Exception{
        boolean weiteresTupel = true;
        while (weiteresTupel) {
            System.out.println("""
                    Wollen Sie ein Tupel
                    1. In die Tabelle Kommentar einfügen
                    2. In die Tabelle Konto einfügen
                    3. Zurück zur Auswahl
                    """);
            System.out.print("Eingabe:");
            int eingabeAktionTupel = sc.nextInt();
            System.out.println("\n");
            switch (eingabeAktionTupel) {
                case 1 -> {
                    boolean weiteren = true;
                    while (weiteren) {

                        Controller.executeInsert(" TODO ");

                        System.out.println("\nWollen sie ein weiteres Tupel einfügen? (j/n)");
                        boolean fehlgeschlagen;
                        do {
                            String auswahl = sc.next();
                            if (auswahl.equals("n")) {
                                weiteren = false;
                                fehlgeschlagen = false;
                                System.out.println("\n");
                            } else if (auswahl.equals("j")) {
                                fehlgeschlagen = false;
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

                }
                case 3 -> weiteresTupel = false;

                default ->
                        System.out.println("Ihre Eingabe '" + eingabeAktionTupel + "' ist fehlerhaft bitte wählen Sie eine Ziffer zwischen 1 und 3");
            }
        }
    }
}
