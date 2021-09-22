package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 22);
        userService.saveUser("Stas", "Jona", (byte) 33);
        userService.saveUser("Pety", "Malahov", (byte) 44);
        userService.saveUser("Caramel", "Nut", (byte) 55);
        System.out.println(userService.getAllUsers());
        //userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        //userService.dropUsersTable();
    }
}
