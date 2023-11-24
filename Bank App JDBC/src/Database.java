import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Database {
    public static Connection getConnection() {

        Connection connection = null;
        try(FileInputStream input = new FileInputStream("src/db.properties")){

            Properties props = new Properties();
            props.load(input);

            connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("user"),
                    props.getProperty("password")
            );

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
