package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," + "lastname VARCHAR(255)," + "age BIGINT)");
        } catch (SQLException ignore) {
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        }   catch (SQLException ignore) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertUser = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertUser);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException ignore) {
        }
    }

    public List<User> getAllUsers() {
        ResultSet rs = null;
        List<User> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                resultList.add(user);
            }
        } catch (SQLException ignore) {
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute( "DELETE FROM users");
        } catch (SQLException ignore) {
        }
    }

}
