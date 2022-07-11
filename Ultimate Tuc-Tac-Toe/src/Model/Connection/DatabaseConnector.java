package Model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static void connect(){

        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tuctactoe",
                "newuser", "12345")){

            System.out.println(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
