import java.sql.Connection;
import java.sql.DriverManager;

public class TestMySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/chat";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to MySQL successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
