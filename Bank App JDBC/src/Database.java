import org.mindrot.jbcrypt.BCrypt;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public static void insertUser(String username, String password, String fullname){
        String hashedPassword = hashPassword(password);
        String sql = "insert into users(username, hashed_password, fullname, balance) values(?,?,?,?);";
        Connection connection = getConnection();
        int rowAffected;
        try(PreparedStatement prepStatement = connection.prepareStatement(sql)){

            prepStatement.setString(1, username);
            prepStatement.setString(2, hashedPassword);
            prepStatement.setString(3, fullname);
            prepStatement.setDouble(4, 0.0);

            rowAffected = prepStatement.executeUpdate();

            if(rowAffected > 0){
                System.out.println("Account created successfully!");
            } else {
                System.out.println("Failed to create account");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
