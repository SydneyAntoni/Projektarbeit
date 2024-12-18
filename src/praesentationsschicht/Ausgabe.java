package praesentationsschicht;

import datenhaltungsschicht.DBZugriff;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Ausgabe {

    /**
     * Prints a horizontal line to separate rows in a table output.
     * The line is constructed using the provided column widths, with a "+" character at the beginning and end of the line.
     *
     * @param colWidths an array of integers representing the width of each column in the table
     */
    public static void printTableLine(int[] colWidths) {
        System.out.print("+");
        for (int width : colWidths) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }
    /**
     * Prints the contents of a SQL ResultSet in a formatted table.
     * The table is constructed with column widths adjusted to fit the longest value in each column, including the column names.
     * A horizontal line is printed to separate each row in the table.
     *
     * @param results the SQL ResultSet to be printed
     */
    public static void print(ResultSet results) {
        try {
            ResultSetMetaData metaData = results.getMetaData();
            int colCount = metaData.getColumnCount();

            // Bestimmt die Breite jedes Spaltennamens
            int[] colWidths = new int[colCount];
            for (int i = 1; i <= colCount; i++) {
                colWidths[i - 1] = metaData.getColumnName(i).length();
            }

            // Bestimmt den breitesten Inhalt einer Spalte inkl. Spaltennamen
            while (results.next()) {
                for (int i = 1; i <= colCount; i++) {
                    String value = results.getString(i);
                    if (value != null) {
                        colWidths[i - 1] = Math.max(colWidths[i - 1], value.length());
                    }
                }
            }

            System.out.println("\n\nErgebnis:");
            // Gibt Spaltenkopf aus
            System.out.println();
            System.out.print("|");
            for (int i = 1; i <= colCount; i++) {
                System.out.printf(" %-" + colWidths[i - 1] + "s |", metaData.getColumnName(i));
            }
            System.out.println();
            printTableLine(colWidths);

            // Gibt Spaltendaten aus
            results.beforeFirst();
            while (results.next()) {
                System.out.print("|");
                for (int i = 1; i <= colCount; i++) {
                    String value = results.getString(i);
                    System.out.printf(" %-" + colWidths[i - 1] + "s |", (value != null ? value : ""));
                }
                System.out.println();
                printTableLine(colWidths);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
