package ir.maktab.article.database;

import ir.maktab.article.entity.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbArticle {
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

    public Article findArticleById(int id) throws SQLException {
        openConnection();
        Article article = new Article();
        String query = "select * from articles where id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            article.setId(id);
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            article.setContent(resultSet.getString("content"));
            article.setCreateDate(resultSet.getString("create_date"));
            article.setPublished(resultSet.getBoolean("published"));
            article.setPublishDate(resultSet.getString("publish_date"));
            article.setLastUpdate(resultSet.getString("last_update"));
            article.setUserId(resultSet.getInt("user_id"));
            article.setCategoryId(resultSet.getInt("category_id"));
        }
        closeConnection();
        return article;
    }

    public boolean updateArticle(int id, Article article) throws SQLException {
        openConnection();
        String query = "update articles set title=?,brief=?,content=?,is_published=?,publish_date=?,category_id=? where id=?";
        statement = connection.prepareStatement(query);

        statement.setString(1, article.getTitle());
        statement.setString(2, article.getBrief());
        statement.setString(3, article.getContent());
        statement.setBoolean(4, article.getIsPublished());
        statement.setString(5, article.getPublishDate());
        statement.setInt(6, article.getCategoryId());
        statement.setInt(7, id);

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

    public boolean deleteArticle(int id) throws SQLException {
        openConnection();
        String query = "delete from  articles where id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        if (statement.executeUpdate() > 0) {
            System.out.println("delete done");
            return true;
        } else return false;
    }

    public List<Article> findAllArticle() throws SQLException {
        openConnection();

        String query = "select * from  articles";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Article> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(new Article(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("brief"),
                    resultSet.getString("content"),
                    resultSet.getString("create_date"),
                    resultSet.getBoolean("is_published"),
                    resultSet.getString("last_update"),
                    resultSet.getString("publish_date"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("category_id")));
        }
        closeConnection();
        return arrayList;
    }


    public boolean insertArticle(Article article) throws SQLException {
        openConnection();
        String query = "insert into articles(title,brief,content,user_id,category_id) values (?,?,?,?,?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, article.getTitle());
        statement.setString(2, article.getBrief());
        statement.setString(3, article.getContent());
        statement.setInt(4, article.getUserId());
        statement.setInt(5, article.getCategoryId());

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

    public ArrayList<Article> findAllArticle(int limit, int step) throws SQLException {
        openConnection();
        int offset = (step - 1) * limit;
        String query = "select * from order  by id articles limit?,?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, offset);
        statement.setInt(2, limit);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Article> articles = new ArrayList<>();

        while (resultSet.next())
        {
            articles.add(new Article(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("brief"),
                    resultSet.getString("content"),
                    resultSet.getString("createDate"),
                    resultSet.getBoolean("isPublished"),
                    resultSet.getString("lastUpdate"),
                    resultSet.getString("publishDate"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("category_id")));
        }
        closeConnection();
        return articles;
    }
}
