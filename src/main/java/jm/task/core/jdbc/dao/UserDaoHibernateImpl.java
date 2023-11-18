package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS" +
                            "(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE TINYINT)")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() { // если удаляем таблу,которой нет,не дб исключений
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("Пользователь с именем " + name + " добавлен в базу");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                session.getTransaction().begin();
                session.delete(user);
                session.getTransaction().commit();
            } else {
                System.out.println("Пользователя с таким id не существует");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() { // очищаем таблу, но саму не удаляем
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
