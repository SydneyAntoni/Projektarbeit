package datenhaltungsschicht;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBZugriffSearch extends DBZugriff{
    /**
     * Executes a search query with the provided SQL query and comment ID.
     *
     * @param query The SQL query to execute.
     * @param kommentarID The ID of the comment to use in the query.
     * @return The ResultSet containing the search results.
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    public static ResultSet executeSearch(String query, int kommentarID) throws SQLException {
        try {
            preparedBefehl = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedBefehl.setInt(1, kommentarID);
            ResultSet resultSet = preparedBefehl.executeQuery();
            return resultSet;
        } catch (Exception ex) {
            String errorMessage = "Es ist ein Fehler beim Ausführen der SQL-Anweisung aufgetreten.";
            System.out.println(errorMessage);
            Logger.logCommand(preparedBefehl.toString());
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
}
