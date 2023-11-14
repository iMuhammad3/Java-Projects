import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Database {
    public Database(){}

    public static Connection getConnection() throws Exception {
        Connection connection = null;

        try(FileInputStream input = new FileInputStream("src/db.properties")){
            Properties props = new Properties();
            props.load(input);

            // get connection from the specific url, user
            // & password read from the db.properties file
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
