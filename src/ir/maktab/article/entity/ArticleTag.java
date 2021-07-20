package ir.maktab.article.entity;

public class ArticleTag {
    private int articleId;
    private int tagId;

    public ArticleTag() {
    }

    public ArticleTag(int articleId, int tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
