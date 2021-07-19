package ir.maktab.article.repository.base;

import ir.maktab.article.entity.Article;
import ir.maktab.article.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagManage {
    boolean insertTag(String title) throws SQLException;

    List<Article> getAllTag() throws SQLException;

    List<Article> getAllTag(int count, int step) throws SQLException;

    Article getTagById(int id) throws SQLException;

    boolean removeTag(int id) throws SQLException;

    boolean update(int id, String title) throws SQLException;

    List<Tag> getTagsByUserId(int tagId) throws SQLException;

}
