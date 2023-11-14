package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    // all my code
    private static Connection connection = null;
    private static Util util = null;
    private static final String BD_USERNAME = "root";
    private static final String BD_PASSWORD = "root";
    private static final String BD_URL = "jdbc:mysql://localhost:3306/katabd";


    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(BD_URL, BD_USERNAME, BD_PASSWORD);
            //System.out.println("connection ok");
        } catch (SQLException e) {
            System.out.println("connection error");
        }
        return connection;
    }

    public static Util getInstance() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

}
