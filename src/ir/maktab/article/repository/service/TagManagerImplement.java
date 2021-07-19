package ir.maktab.article.repository.service;

import ir.maktab.article.database.DbTag;
import ir.maktab.article.entity.Tag;
import ir.maktab.article.repository.base.TagManger;
import java.sql.SQLException;
import java.util.List;

public class TagManagerImplement implements TagManger
{
    DbTag dataBase;
    public TagManagerImplement()
    {
        dataBase=new DbTag();
    }
    @Override
    public boolean insertTag(String title) throws SQLException
    {
        Tag tag = new Tag(title);
        return dataBase.insertTag(tag);
    }

    @Override
    public List<Tag> getAllTag() throws SQLException {
        return dataBase.findAllTag();
    }

    @Override
    public List<Tag> getAllTag(int limit, int step) throws SQLException {
        return dataBase.findAllTag(limit, step);
    }

    @Override
    public Tag getTagById(int id) throws SQLException {
        return dataBase.findTagById(id);
    }

    @Override
    public boolean removeTag(int id) throws SQLException {
        return dataBase.deleteTag(id);
    }

    @Override
    public boolean update(int id, String title) throws SQLException {
        Tag tag = new Tag(title);
        return dataBase.updateTag(id,tag);
    }
}
