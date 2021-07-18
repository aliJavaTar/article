package ir.maktab.article.repository.service;

import ir.maktab.article.database.DbUser;
import ir.maktab.article.entity.User;
import ir.maktab.article.repository.base.UserManage;

import java.sql.SQLException;
import java.util.List;

public class UserManagerImplement implements UserManage {
    DbUser database;
    public UserManagerImplement()
    {
        database=new DbUser();
    }
    @Override
    public boolean insertUser(String username, int nationalCode, int birthday, boolean isActive) throws SQLException
    {
      return   database.insertUser(new User(username,nationalCode,birthday,isActive));

    }

   public User getUserByUserName(String username) throws SQLException {
       return database.findUserByUsername(username);
   }

    @Override
    public List<User> getAllUser() throws SQLException {
       return database.findAllUser();
    }

    @Override
    public List<User> getAllUser(int limit, int step) throws SQLException {
        return database.findAllUser(limit, step);
    }

    @Override
    public User getUserById(int id) throws SQLException {
       return database.findUserById(id);
    }

    @Override
    public boolean removeUser(int id) throws SQLException {
        return database.deleteUser(id);
    }

    @Override
    public boolean update(int id, String username, String password, int nationalCode, int birthday,boolean isActive) throws SQLException
    {
      return database.updateUser(id,new User(username,password,nationalCode,birthday,isActive));
    }
}
