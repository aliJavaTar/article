package ir.maktab.article.repository.service;

import ir.maktab.article.database.DbArticleTag;
import ir.maktab.article.entity.Tag;
import ir.maktab.article.repository.base.ArticleTag;

import java.sql.SQLException;
import java.util.List;

public class ArticleTagMangerImplement implements ArticleTag {
    DbArticleTag db;
    public ArticleTagMangerImplement(){
        this.db=new DbArticleTag();
    }

    @Override
    public boolean insertArticleTag(int articleId, int tagId) throws SQLException {
        return db.insertRelationTag(articleId,tagId);
    }

    @Override
    public List<Tag> findArticleTag(int articleId) throws SQLException {
        return db.getArticleTags(articleId);
    }
}
