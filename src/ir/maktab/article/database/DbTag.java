package ir.maktab.article.database;

import ir.maktab.article.entity.Category;
import ir.maktab.article.entity.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbTag {
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

    public Tag findTagById(int id) throws SQLException {
        openConnection();
        Tag tag = new Tag();
        String query = "select * from tags where id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            tag.setId(id);
            tag.setTitle(resultSet.getString("title"));

        }
        closeConnection();
        return tag;
    }

    public boolean updateTag(int id, Tag tag) throws SQLException {
        openConnection();
        String query = "update categoryes set title=? where id=?";
        statement = connection.prepareStatement(query);
        statement.setString(1, tag.getTitle());
        statement.setInt(2, id);
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

    public boolean deleteTag(int id) throws SQLException {
        openConnection();
        String query = "delete from  tags where id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        if (statement.executeUpdate() > 0) {
            System.out.println("delete done");
            return true;
        } else return false;
    }

    public List<Tag> findAllTag() throws SQLException {
        openConnection();

        String query = "select * from  tags";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Tag> tags = new ArrayList<>();
        while (resultSet.next()) {
            tags.add(new Tag(resultSet.getString("title")));
        }
        closeConnection();
        return tags;
    }

    public boolean insertTag(Tag tag) throws SQLException {
        openConnection();
        String query = "insert into categoryes(title) values (?)";
        statement = connection.prepareStatement(query);

        statement.setString(1, tag.getTitle());

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

    public List<Tag> findAllUser(int limit, int step) throws SQLException {
        openConnection();
        int offset = (step - 1) * limit;
        String query = "select * from order  by id tags limit?,?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, offset);
        statement.setInt(2, limit);


        ResultSet resultSet = statement.executeQuery();

        ArrayList<Tag> tags = new ArrayList<>();

        while (resultSet.next()) {
            tags.add(new Tag(resultSet.getString("title")));
        }
        closeConnection();
        return tags;
    }
}
