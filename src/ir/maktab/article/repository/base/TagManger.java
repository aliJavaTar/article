package ir.maktab.article.repository.base;

import ir.maktab.article.entity.Tag;
import ir.maktab.article.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface TagManger {
    boolean insertTag(String title) throws SQLException;

    List<Tag> getAllTag() throws SQLException;

    List<Tag> getAllTag(int limit,int step) throws SQLException;

    User getTagById(int id) throws SQLException;

    boolean removeTag(int id) throws SQLException;

    boolean update(int id,String title) throws SQLException;
}
