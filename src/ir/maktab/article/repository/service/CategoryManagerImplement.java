package ir.maktab.article.repository.service;

import ir.maktab.article.database.DbCategory;
import ir.maktab.article.repository.base.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryManagerImplement implements Category {
    DbCategory dbCategory;

    public CategoryManagerImplement()
    {
        dbCategory=new DbCategory();
    }
    @Override
    public boolean insertCategory(String title, String description) throws SQLException {
        return dbCategory.insertCategory(new ir.maktab.article.entity.Category(title,description));
    }

    @Override
    public ArrayList<ir.maktab.article.entity.Category> getAllCategory() throws SQLException {
       return(ArrayList) dbCategory.findAllCategory();
    }

    @Override
    public ArrayList<ir.maktab.article.entity.Category> getAllCategory(int count, int step) throws SQLException {
        return(ArrayList) dbCategory.findAllUser(count, step);
    }

    @Override
    public ir.maktab.article.entity.Category getCategoryById(int id) throws SQLException {
        return  dbCategory.findCategoryById(id);
    }

    @Override
    public boolean removeCategory(int id) throws SQLException {
       return dbCategory.deleteCategory(id);
    }

    @Override
    public boolean updateCategory(int id, String title, String description) throws SQLException {
        return dbCategory.updateCategory(id,new ir.maktab.article.entity.Category(id,title,description));
    }
}
