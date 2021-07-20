package ir.maktab.article.database;

import ir.maktab.article.entity.ArticleTag;
import ir.maktab.article.entity.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbArticleTag {
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

    public boolean insertRelationTag(int articleId, int tagId) throws SQLException {
        openConnection();
        String query = "insert into article_tag values (?,?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, articleId);
        statement.setInt(2, tagId);
        if (statement.executeUpdate() > 0)
        {
            System.out.println("done");
            return true;
        }
        else return false;
    }
    public List<Tag> getArticleTags(int id) throws SQLException {

        List<Tag> tagList = new ArrayList<>();
        openConnection();
        String sql="select DISTINCT t.id,t.title from articles as a, tags as t , article_tag as r" +
                " where ? = r.article_id and t.id = r.tag_id ";
        statement=connection.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            tagList.add(new Tag(resultSet.getInt("id"),
                    resultSet.getString("title")));
        }
        return tagList;
    }
}
