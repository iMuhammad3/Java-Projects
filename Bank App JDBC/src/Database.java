import org.mindrot.jbcrypt.BCrypt;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static Connection getConnection() {

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

    public static User checkUser(String usernameInput, String passwordInput){
        User user = null;
        String sql = "select * from users;";
        Connection connection = getConnection();
        try(ResultSet rs = connection.createStatement().executeQuery(sql)){

            while(rs.next()){
                if(
                    rs.getString("username").equals(usernameInput)
                        &&
                            // check user inputted password against hashed password in db
                    BCrypt.checkpw(passwordInput, rs.getString("hashed_password"))
                ){
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String fullName = rs.getString("fullname");
                    double balance = rs.getDouble("balance");
                    user = new User(id, username, fullName, balance);
                }
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static int updateBalance(int id, double amount){
        String sql = "update users set balance = ? where id = ?";
        Connection connection = getConnection();
        int rowAffected = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(sql)){

            prepStatement.setDouble(1, amount);
            prepStatement.setInt(2, id);

            rowAffected = prepStatement.executeUpdate();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rowAffected;
    }

    private static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
