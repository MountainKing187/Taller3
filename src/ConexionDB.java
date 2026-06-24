import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static Connection conectar() throws SQLException {
        ConfigLoader config = new ConfigLoader(".env");

        String DB_URL = config.get("DB_URL");
        String USER = config.get("DB_USER");
        String PASS = config.get("DB_PASS");

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}