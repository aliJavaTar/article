package ir.maktab;

import ir.maktab.article.SystemArticle;
import ir.maktab.article.repository.service.TagManagerImplement;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SystemArticle system = new SystemArticle();
        system.startMenu();
    }
}