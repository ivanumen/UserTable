package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        Statement statement = null;
        try {
            statement = util.getStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (" +
                    "  `id`       BIGINT       PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "  `name`     VARCHAR(250) DEFAULT NULL," +
                    "  `lastname` VARCHAR(250) DEFAULT NULL," +
                    "  `age`      TINYINT      DEFAULT NULL)");
            try {
                util.getConnection().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("База данных Users создана");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Statement statement = null;
        try {
            statement = util.getStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
            try {
                util.getConnection().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("База данных удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        PreparedStatement statement = null;
        try {
            statement = util.getConnection().prepareStatement("INSERT INTO USERS(`name`, `lastName`, `age`) " +
                    "VALUES ('"
                    + user.getName().toString()
                    + "', '" + user.getLastName().toString()
                    + "', "
                    + user.getAge().toString()
                    + ")");
            statement.executeUpdate();
            try {
                util.getConnection().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //util.getStatement().executeUpdate(sql);
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        PreparedStatement statement = null;
        try {
            statement = util.getConnection().prepareStatement("DELETE FROM USERS WHERE id = " + id);
            statement.executeUpdate();
            try {
                util.getConnection().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //util.getStatement().executeUpdate(sql);
            System.out.println("User remove");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = util.getStatement().executeQuery("SELECT * FROM USERS ORDER BY id");
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                userList.add(user);
            }
            try {
                util.getConnection().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("User get");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Statement statement = null;
        try {
            statement = util.getStatement();
            statement.executeUpdate("TRUNCATE TABLE USERS");
            try {
                util.getConnection().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("User clean");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
