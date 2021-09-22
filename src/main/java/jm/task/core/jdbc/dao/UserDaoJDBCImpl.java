package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
                "  `id`       BIGINT       PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "  `name`     VARCHAR(250) DEFAULT NULL," +
                "  `lastname` VARCHAR(250) DEFAULT NULL," +
                "  `age`      TINYINT      DEFAULT NULL)";
        try {
            util.getStatement().executeUpdate(sql);
            System.out.println("База данных Users создана");
        } catch (SQLException e) {
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE USERS";
        try {
            util.getStatement().executeUpdate(sql);
            System.out.println("База данных удалена");
        } catch (SQLException e) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        String sql = "INSERT INTO USERS(`name`, `lastName`, `age`) VALUES ('" + user.getName().toString() + "', '" + user.getLastName().toString() + "', " + user.getAge().toString() + ")";
        try {
            util.getStatement().executeUpdate(sql);
            System.out.println("User с именем –" + user.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE id = " + id;
        try {
            util.getStatement().executeUpdate(sql);
            System.out.println("User remove");
        } catch (SQLException e) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS ORDER BY id";
        try {
            ResultSet resultSet = util.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                userList.add(user);
            }
            System.out.println("User get");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USERS";
        try {
            util.getStatement().executeUpdate(sql);
            System.out.println("User clean");
        } catch (SQLException e) {
        }
    }
}
