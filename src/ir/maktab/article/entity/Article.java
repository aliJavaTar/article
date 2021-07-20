package ir.maktab.article.entity;

import java.sql.Timestamp;

public class Article {
    private  int id;
    private  String title;
    private String brief;
    private String content;
    private String createDate;
    private boolean isPublished;
    private String lastUpdate;
    private Timestamp publishDate;
    private int userId;
    private int categoryId;
    private int price;

    public Article(String title,
                   String brief, String content, boolean isPublished,
                   Timestamp publishDate, int categoryId, int price, int userId)
    {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.isPublished = isPublished;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.price=price;
        this.userId=userId;

    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public Article() {
    }


    public Article(String title, String brief, String content,
                   boolean isPublished, Timestamp publishDate, int categoryId,int price) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.isPublished = isPublished;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.price=price;
    }

    public Article(String title, String brief, String content, int userId, int categoryId,int price)
    {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.userId = userId;
        this.categoryId = categoryId;
        this.price=price;
    }

    public Article(int id, String title, String brief, String content, String createDate,
                   boolean isPublished, String lastUpdate, Timestamp publishDate, int userId, int categoryId,int price) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.lastUpdate = lastUpdate;
        this.publishDate = publishDate;
        this.userId = userId;
        this.categoryId = categoryId;
        this.price=price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public void setPublished(boolean published) {
        isPublished = published;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", isPublished=" + isPublished +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", price=" + price +
                '}';
    }
}
