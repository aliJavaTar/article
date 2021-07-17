package ir.maktab;


import ir.maktab.article.SystemArticle;
import ir.maktab.article.entity.Article;
import ir.maktab.article.entity.Category;
import ir.maktab.article.repository.service.ArticleManagerImplement;
import ir.maktab.article.repository.service.CategoryManagerImplement;
import ir.maktab.article.repository.service.UserManagerImplement;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        SystemArticle system =new SystemArticle();
        system.systemMenu();
        system.startMenu();
    }
}
