package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "40adogavO=";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    private static final Connection connection;
    private static final Statement statement;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch(SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

}
