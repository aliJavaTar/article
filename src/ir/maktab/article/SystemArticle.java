package ir.maktab.article;

import ir.maktab.article.entity.Article;
import ir.maktab.article.repository.service.ArticleManagerImplement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemArticle {
    static Scanner input = new Scanner(System.in);

    public void startMenu() throws SQLException {
        System.out.println("choice one of them: (between 1 or 4)");
        int select = input.nextInt();
        switch (select)
        {
            case 1:
                loginUser();
                break;
            case 2:
                register();
                break;
            case 3:
                showArticle();
                break;
            case 4:
                loginAdmin();
                break;
            default:startMenu();
        }
    }

    private void loginAdmin()
    {
    }

    private void showArticle() throws SQLException {
        ArticleManagerImplement articles = new ArticleManagerImplement();
       List<Article> articleList = articles.getAllArticle();
       Article article =new Article();
        for (int i = 0; i <articleList.size(); i++)
        {
            System.out.println("id: "+articleList.get(i).getId()+ " title : "
                    +articleList.get(i).getTitle()+" Brief: "+articleList.get(i).getBrief());
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("which one do you like to see complete ?");
        System.out.print("enter id: ");
        int id =input.nextInt();
        article = articles.getArticleById(id);
        if (article.getId()!=0)
            System.out.println(article.toString());
         else
            System.out.println("not found");


    }

    private void register() {
    }

    private void loginUser() {
    }

    public void systemMenu() {
        System.out.println("1) Login ");
        System.out.println("2) Register ");
        System.out.println("3) Watch Article ");
        System.out.println("4) Login Admin");
    }
}
