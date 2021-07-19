package ir.maktab.article.repository;

import com.mysql.cj.exceptions.StreamingNotifiable;
import ir.maktab.article.entity.User;
import ir.maktab.article.repository.service.UserManagerImplement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private String username ;
    private String password ;

    Scanner input = new Scanner(System.in);

    public Admin() {
        this.username="aliErfagh";
        this.password="007";
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    public void verifyUser() throws SQLException
    {

        User user;
        UserManagerImplement userManage=new UserManagerImplement();
       ArrayList<User> userList = (ArrayList<User>) userManage.getAllUser();
        System.out.println("all user list .............");
        for (int i = 0; i <userList.size() ; i++)
        {
            System.out.println(userList.get(i));
        }
        System.out.println("----------------------------------------------------");
        System.out.println("which one do you want change Activity: [Enter username ] ");
        String name=input.next();
        user =  userManage.getUserByUserName(name);
        System.out.println("is Active before change : "+user.getIsActive());
        System.out.println("if you want change enter 1(active) or 0(No active): ");

        int active=input.nextInt();
        if (active==1 || active==0)
        {
            if (active==1)
             user.setActive(true);
            else user.setActive(false);
        }else System.out.println("number false");

        userManage.update(user.getId(),user.getUsername(),user.getPassword(),
                user.getNationalCode(),user.getBirthday(),user.getIsActive(),user.getWallet());
        System.out.println("is Active after change : "+user.getIsActive());
    }
}
