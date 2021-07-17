package ir.maktab.article.repository.base;

import ir.maktab.article.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserManage {

    boolean insertUser(String username, String password, int nationalCode, int birthday) throws SQLException;

    List<User> getAllUser() throws SQLException;

     List<User> getAllUser(int limit,int step) throws SQLException;

    User getUserById(int id) throws SQLException;

    boolean removeUser(int id) throws SQLException;

    boolean update(int id,String username, String password, int nationalCode, int birthday) throws SQLException;


}
