package ir.maktab.article.repository.base;

import java.sql.SQLException;
import java.util.List;

public interface Category {
    boolean insertCategory(String title, String description) throws SQLException;

    List<Category> getAllCategory() throws SQLException;
    List<Category> getAllCategory(int count,int step) throws SQLException;

    ir.maktab.article.entity.Category getCategoryById(int id) throws SQLException;

    boolean removeCategory(int id) throws SQLException;

    boolean updateCategory(int id, String title, String description) throws SQLException;
}
