package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/dbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String dataBaseName = "dbtest";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            Configuration configuration = new Configuration();
            properties.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(AvailableSettings.URL, URL);
            properties.setProperty(AvailableSettings.USER, USERNAME);
            properties.setProperty(AvailableSettings.PASS, PASSWORD);
            properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.setProperty(AvailableSettings.SHOW_SQL, "true");
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            try {
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
