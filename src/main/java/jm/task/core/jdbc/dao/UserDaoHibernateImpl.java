package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS USERS (" +
                    "  `id`       BIGINT       PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "  `name`     VARCHAR(250) DEFAULT NULL," +
                    "  `lastname` VARCHAR(250) DEFAULT NULL," +
                    "  `age`      TINYINT      DEFAULT NULL)");
            session.getTransaction().commit();
            System.out.println("Таблица Users создана");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS USERS").executeUpdate();
            session.getTransaction().commit();
            System.out.println("База данных удалена");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем –" + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.delete((User)session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("User remove");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User").list();
            session.getTransaction().commit();
            System.out.println("User get");
            System.out.println(userList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("User clean");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
