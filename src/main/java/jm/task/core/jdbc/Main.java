package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserDao userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Name1", "LastName1", (byte) 1);
        userDaoJDBC.saveUser("Name2", "LastName2", (byte) 2);
        userDaoJDBC.saveUser("Name3", "LastName3", (byte) 3);
        userDaoJDBC.saveUser("Name4", "LastName4", (byte) 4);

        userDaoJDBC.removeUserById(1);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }
}
