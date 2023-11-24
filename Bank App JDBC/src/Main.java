import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = Database.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");

            while(rs.next()){
                System.out.println(rs.getString("username"));
                System.out.println(rs.getDouble("balance"));
                System.out.println(rs.getInt("id"));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}