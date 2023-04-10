package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/dbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Driver getDriver(){
        Driver driver = null;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.err.println("Произошла ошибка при создании драйвера");
        }
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.err.println("Произошла ошибка при регистрации драйвера");
        }
        return driver;
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось создать соединение");
        }
        return connection;
    }

}
