package ir.maktab.article.repository.service;

import ir.maktab.article.entity.Tag;
import ir.maktab.article.entity.User;
import ir.maktab.article.repository.base.TagManger;

import java.sql.SQLException;
import java.util.List;

public class TagManagerImplement implements TagManger {
    @Override
    public boolean insertTag(String title) throws SQLException {
        return false;
    }

    @Override
    public List<Tag> getAllTag() throws SQLException {
        return null;
    }

    @Override
    public List<Tag> getAllTag(int limit, int step) throws SQLException {
        return null;
    }

    @Override
    public User getTagById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeTag(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(int id, String title) throws SQLException {
        return false;
    }
}
