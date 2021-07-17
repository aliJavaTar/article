package ir.maktab.article.database;

import ir.maktab.article.entity.Category;
import ir.maktab.article.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbCategory {
        private String url = "jdbc:mysql://localhost:3306/ali";
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

        public Category findCategoryById(int id) throws SQLException {
            openConnection();
            Category category = new Category();
            String query = "select * from categoryes where id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                category.setTitle(resultSet.getString("title"));
                category.setDescription(resultSet.getString("description"));
            }
            closeConnection();
            return category;
        }

        public boolean updateCategory(int id, Category category) throws SQLException {
            openConnection();
            String query = "update categoryes set title=?,description=? where id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, category.getTitle());
            statement.setString(2,  category.getDescription());
            if (statement.executeUpdate() > 0) {
                System.out.println("update done");
                closeConnection();
                return true;
            } else
                {
                System.out.println("update filed");
                closeConnection();
                return false;
            }
        }

        public boolean deleteCategory(int id) throws SQLException {
            openConnection();
            String query = "delete from  categoryes where id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                System.out.println("delete done");
                return true;
            } else return false;
        }

        public List<Category> findAllCategory() throws SQLException {
            openConnection();

            String query = "select * from  categoryes";
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Category> categoryList = new ArrayList<>();
            while (resultSet.next())
            {
                categoryList.add(new Category(
                        resultSet.getString("title"),
                        resultSet.getString("description")));
            }
            closeConnection();
            return categoryList;
        }

        public boolean insertCategory(Category category) throws SQLException
        {
            openConnection();
            String query="insert into categoryes(title,description) values (?,?)";
            statement=connection.prepareStatement(query);

            statement.setString(1,category.getTitle());
            statement.setString(2,category.getDescription());

            if (statement.executeUpdate()>0)
            {
                System.out.println("insert done ");
                closeConnection();
                return true;
            }else
            {
                System.out.println("insert filed");
                closeConnection();
                return false;
            }

        }

        public List<Category> findAllUser(int limit,int step) throws SQLException
        {
            openConnection();
            int offset=(step-1)*limit;
            String query = "select * from order  by id categoryes limit?,?";

            statement.setInt(1,offset);
            statement.setInt(2,limit);

            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Category> categories = new ArrayList<>();

            while (resultSet.next())
            {
                categories.add(new Category(
                        resultSet.getString("title"),
                        resultSet.getString("description")));
            }
            closeConnection();
            return categories;
        }
}
