package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
//                "  `id`       BIGINT       PRIMARY KEY NOT NULL AUTO_INCREMENT," +
//                "  `name`     VARCHAR(250) DEFAULT NULL," +
//                "  `lastname` VARCHAR(250) DEFAULT NULL," +
//                "  `age`      TINYINT      DEFAULT NULL)";
//        try  {
//            Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            Query query = session.createSQLQuery(sql).addEntity(User.class);
//            transaction.commit();
//            session.close();
//        } catch (Exception e) {
//        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
