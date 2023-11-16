import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    public Database(){}

    public static Connection getConnection() {
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

    public static void insertData(String description){
        String sql = "insert into to_do (description) values (?)";

        try(
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(sql)
        ) {
            prepStatement.setString(1, description);
            prepStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<ToDo> fetchTodos(){
        String sql = "select * from to_do";
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                ){
            List<ToDo> todos = new ArrayList<>();

            while (result.next()){
                ToDo todo = new ToDo();
                todo.setId(result.getInt("id"));
                todo.setDescription(result.getString("description"));
                todo.setCompleted(result.getBoolean("completed"));
                todo.setDateCreated(result.getDate("date_created"));
                todo.setLastModified(result.getDate("last_modified"));
                todos.add(todo);
            }
            return todos;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
