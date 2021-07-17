package ir.maktab.article.repository.base;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Category {
    boolean insertCategory(String title, String description) throws SQLException;

    ArrayList<ir.maktab.article.entity.Category> getAllCategory() throws SQLException;
    ArrayList<ir.maktab.article.entity.Category> getAllCategory(int count, int step) throws SQLException;

    ir.maktab.article.entity.Category getCategoryById(int id) throws SQLException;

    boolean removeCategory(int id) throws SQLException;

    boolean updateCategory(int id, String title, String description) throws SQLException;
}
