package ir.maktab.article.entity;

public class Article {
    private  int id;
    private  String title;
    private String brief;
    private String content;
    private String createDate;
    private boolean isPublished;
    private String lastUpdate;
    private String publishDate;

    public boolean getIsPublished() {
        return isPublished;
    }

    public Article() {
    }

    public Article(String title, String brief, String content, String createDate, boolean isPublished, String lastUpdate, String publishDate) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.lastUpdate = lastUpdate;
        this.publishDate = publishDate;
    }

    public Article(int id, String title, String brief, String content, String createDate, boolean isPublished, String lastUpdate, String publishDate) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.lastUpdate = lastUpdate;
        this.publishDate = publishDate;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
