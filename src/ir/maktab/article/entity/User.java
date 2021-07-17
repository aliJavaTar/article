package ir.maktab.article.entity;

public class User {
    private int id;
    private String username;
    private String password;
    private int nationalCode;
    private int birthday;
    private boolean isActive=false;
    public User() {
    }

    public User(String username, String password, int nationalCode, int birthday, boolean isActive) {
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.isActive = isActive;
    }

    public User(int id, String username, String password, int nationalCode, int birthday, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nationalCode=" + nationalCode +
                ", birthday=" + birthday +
                ", isActive=" + isActive +
                '}';
    }
}
