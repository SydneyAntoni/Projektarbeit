import java.sql.*;

public class Model {
    private static String url = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    private static String user = "C##FBPOOL31";
    private static String password = "oracle";

    public static ResultSet execute(String query) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            con.setAutoCommit(true);
            Statement befehl = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet results = befehl.executeQuery(query);
            befehl.close();
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
