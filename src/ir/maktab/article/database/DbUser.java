package ir.maktab.article.database;

import ir.maktab.article.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUser {
    private String url = "jdbc:mysql://localhost:3306/hw7";
    private String username = "root";
    private String password = "ALI33";
    private Connection connection;
    private PreparedStatement statement;

    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public User findUserById(int id) throws SQLException {
        openConnection();
        User user = new User();
        String query = "select * from users where id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            user.setId(id);
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setNationalCode(resultSet.getInt("national_code"));
            user.setBirthday(resultSet.getInt("birthday"));
            user.setActive(resultSet.getBoolean("is_active"));
        }
        closeConnection();
        return user;
    }

    public boolean updateUser(int id, User user) throws SQLException {
        openConnection();
        String query = "update users set username=?,password=?,national_code=?,birthday=?,is_active=? where id=?";
        statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getNationalCode());
        statement.setInt(4, user.getBirthday());
        statement.setBoolean(5, user.getIsActive());
        statement.setInt(6, id);


        if (statement.executeUpdate() > 0) {
            System.out.println("update done");
            closeConnection();
            return true;
        } else {
            System.out.println("update filed");
            closeConnection();
            return false;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        openConnection();
        String query = "delete from  users where id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        if (statement.executeUpdate() > 0) {
            System.out.println("delete done");
            return true;
        } else return false;
    }

    public List<User> findAllUser() throws SQLException {
        openConnection();

        String query = "select * from  users";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(resultSet.getInt("id"), resultSet.getString("userName"),
                    resultSet.getString("password"), resultSet.getInt("national_code"),
                    resultSet.getInt("birthday"), resultSet.getBoolean("is_active")));
        }
        closeConnection();
        return users;
    }

    public boolean insertUser(User user) throws SQLException {
        openConnection();
        String query = "insert into users(userName,password,national_code,birthday) values (?,?,?,?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, String.valueOf(user.getNationalCode()));
        statement.setInt(3, user.getNationalCode());
        statement.setInt(4, user.getBirthday());

        if (statement.executeUpdate() > 0) {
            System.out.println("insert done ");
            closeConnection();
            return true;
        } else {
            System.out.println("insert filed");
            closeConnection();
            return false;
        }

    }

    public List<User> findAllUser(int limit, int step) throws SQLException {
        openConnection();
        int offset = (step - 1) * limit;
        String query = "select * from users order by id  limit ?,?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, offset);
        statement.setInt(2, limit);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new User(resultSet.getInt("id"), resultSet.getString("userName"),
                    resultSet.getString("password"), resultSet.getInt("national_code"),
                    resultSet.getInt("birthday"), resultSet.getBoolean("is_active")));
        }
        closeConnection();
        return users;
    }

    public User findUserByUsername(String username) throws SQLException
    {

        openConnection();
        User user = new User();
        String query = "select * from users where username=?";
        statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setNationalCode(resultSet.getInt("national_code"));
            user.setBirthday(resultSet.getInt("birthday"));
            user.setActive(resultSet.getBoolean("is_active"));
        }
        closeConnection();
        return user;
    }
}
