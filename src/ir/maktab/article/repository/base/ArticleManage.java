package ir.maktab.article.repository.base;

import ir.maktab.article.entity.Article;
import ir.maktab.article.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ArticleManage {

    boolean insertArticle(String title, String brief, String content
            , int userId, int categoryId,int price) throws SQLException;

    List<Article> getAllArticle() throws SQLException;

    List<Article> getAllArticle(int count, int step) throws SQLException;

    Article getArticleById(int id,String condition) throws SQLException;

    boolean removeArticle(int id) throws SQLException;

    boolean update(int id, String title, String brief, String content,
                   boolean isPublished, String publishDate, int categoryIde,int price) throws SQLException;

    List<Article> getArticlesByUserId(int userId) throws SQLException;

}
