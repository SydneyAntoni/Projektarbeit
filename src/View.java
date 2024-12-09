import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class View {
    public static void main(String[] args) {

    }
    public static void executeAndPrint(String sqlString) {
        System.out.println("\nErgebnis:");
        Model.execute(sqlString);
        System.out.println();
    }

    public static void printTableLine(int[] colWidths) {
        System.out.print("+");
        for (int width : colWidths) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }
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

            // Gibt Spaltenkopf aus
            printTableLine(colWidths);
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
            }
            printTableLine(colWidths);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
