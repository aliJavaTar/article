package ir.maktab.article.repository.base;

import ir.maktab.article.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ArticleTag {
    boolean insertArticleTag(int articleId,int tagId) throws SQLException;
    List<Tag> findArticleTag(int articleId) throws SQLException;
}
