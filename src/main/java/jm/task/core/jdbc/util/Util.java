package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
    private static SessionFactory sessionFactory;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(BD_URL, BD_USERNAME, BD_PASSWORD);
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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new org.hibernate.cfg.Configuration()
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/katabd")
                        .setProperty("hibernate.connection.username", "root")
                        .setProperty("hibernate.connection.password", "root")
                        .setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                sessionFactory = configuration.addAnnotatedClass(User.class).buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println("Исключение" + e);
            }
        }
        return sessionFactory;
    }
}
