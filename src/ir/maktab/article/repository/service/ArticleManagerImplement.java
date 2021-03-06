package ir.maktab.article.repository.service;

import ir.maktab.article.database.DbArticle;
import ir.maktab.article.entity.Article;
import ir.maktab.article.repository.base.ArticleManage;

import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.List;

public class ArticleManagerImplement implements ArticleManage
{
    DbArticle dbArticle ;
    public ArticleManagerImplement()
    {
        dbArticle = new DbArticle();
    }
    @Override
    public boolean insertArticle(String title, String brief, String content, int userId, int categoryId,int price) throws SQLException
    {
      return   dbArticle.insertArticle(new Article( title, brief, content, userId, categoryId,price));
    }

    @Override
    public List<Article> getAllArticle() throws SQLException {
       return dbArticle.findAllArticle();
    }

    @Override
    public List<Article> getAllArticle(int count, int step) throws SQLException {
       return dbArticle.findAllArticle(count, step);
    }

    @Override
    public Article getArticleById(int id,String condition) throws SQLException {
       return dbArticle.findArticleById(id,condition);
    }

    @Override
    public boolean removeArticle(int id) throws SQLException {
        return dbArticle.deleteArticle(id);
    }

    @Override
    public boolean update(int id ,String title, String brief, String content,
                          boolean isPublished, Timestamp publishDate,
                          int categoryIde,int price,int userId) throws SQLException
    {
        return dbArticle.updateArticle(id,new Article( title,  brief,  content,  isPublished,  publishDate,  categoryIde,price,userId));
    }

    @Override
    public List<Article> getArticlesByUserId(int userId) throws SQLException {
        return dbArticle.findArticlesByUserid(userId);
    }

    @Override
    public int getMaxId() throws SQLException {
        return dbArticle.findMaxId();
    }

}
