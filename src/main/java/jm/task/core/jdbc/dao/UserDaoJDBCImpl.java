package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { //если табла уже есть, не дб исключений
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS" +
                    "(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { // если удаляем таблу,которой нет,не дб исключений
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // добавить юзера в таблу
        try (PreparedStatement prepstate = connection.prepareStatement("INSERT INTO USERS(NAME, LASTNAME, AGE) VALUES (?, ?, ?)")) {
            prepstate.setString(1, name);
            prepstate.setString(2, lastName);
            prepstate.setByte(3, age);
            prepstate.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepstate = connection.prepareStatement("DELETE FROM USERS WHERE ID = ?")) {
            prepstate.setLong(1, id);
            prepstate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USERS")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("NAME"), resultSet.getString("LASTNAME"), resultSet.getByte("AGE"));
                user.setId(resultSet.getLong("ID"));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() { // очищаем таблу, но саму не удаляем
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE USERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
