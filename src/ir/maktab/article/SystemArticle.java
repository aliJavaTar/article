package ir.maktab.article;

import ir.maktab.article.entity.*;
import ir.maktab.article.repository.Admin;
import ir.maktab.article.repository.service.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class SystemArticle {
    static Scanner input = new Scanner(System.in);

    public void startMenu() throws SQLException {
        systemMenu();
        System.out.println("choice one of them: (between 1 or 5 )");
        int select = input.nextInt();
        switch (select) {
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
            case 5:
                chargeWallet();
                break;
            default:
                startMenu();
        }
    }

    private void chargeWallet() throws SQLException {
        UserManagerImplement userEdit = new UserManagerImplement();
        System.out.println(" سر کیفی عزیز ; پول بریز جون دل");
        System.out.println("enter username");
        String username = input.next();

        User user = userEdit.getUserByUserName(username);

        if (user.getId() == 0) {
            System.out.println("user not exist...");
            System.exit(0);
        }
        System.out.println("how much money جون دل");
        int money = input.nextInt();
        if (money > 0) {
            money += user.getWallet();
            user.setWallet(money);

            userEdit.update(user.getId(), username, user.getPassword(),
                    user.getNationalCode(), user.getBirthday(),
                    user.getIsActive(), user.getWallet());

            if (user.getWallet() > 5000)
                System.out.println("جون منی جون دل");
            else System.out.println("همش همین");

        } else System.out.println("خودتی");
    }

    private void loginAdmin() throws SQLException {
        Admin admin = new Admin();
        System.out.println("enter you username");
        String username = input.next();
        System.out.println("enter your password");
        String password = input.next();
        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword()))
            admin.verifyUser();

        else System.out.println("username or password false");

    }

    //////////////////////////////////////////////////////////////////////////////
    String condition = "";

    private void showArticle() throws SQLException {
        ArticleManagerImplement articles = new ArticleManagerImplement();
        List<Article> articleList = articles.getAllArticle();
        Article article;
        System.out.println("what kind of article do you want? \n 1) MonetaryArticles \n 2) FreeArticles");
        int select = input.nextInt();
        if (select == 1) {
            showMonetaryArticles(articleList);
        } else if (select == 2)
            showFreeArticles(articleList);
        else System.out.println("number false ....");

        System.out.println("---------------------------------------------------------");

        System.out.println("which one do you like to see complete ?");
        System.out.print("enter id: ");
        int id = input.nextInt();

        article = articles.getArticleById(id, condition);

        if (article.getId() != 0)
            System.out.println(article.toString());
        else
            System.out.println("not found");
    }

    private void showMonetaryArticles(List<Article> articleList) throws SQLException {
        ArticleTagMangerImplement tags = new ArticleTagMangerImplement();
        condition = "money";
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getPrice() > 0) {
                System.out.println("id: " + articleList.get(i).getId() + " title : "
                        + articleList.get(i).getTitle() + " Brief: " +
                        articleList.get(i).getBrief() + " price: " + articleList.get(i).getPrice());
                System.out.println("Tags : " + tags.findArticleTag(articleList.get(i).getId()));
            }
        }
    }

    private void showFreeArticles(List<Article> articleList) throws SQLException {
        condition = "free";
        for (int i = 0; i < articleList.size(); i++) {
            ArticleTagMangerImplement tags = new ArticleTagMangerImplement();
            if (articleList.get(i).getPrice() == 0) {
                System.out.println("id: " + articleList.get(i).getId() + " title : "
                        + articleList.get(i).getTitle() + " Brief: " +
                        articleList.get(i).getBrief() + " price: " + articleList.get(i).getPrice());
                System.out.println("Tags : " + tags.findArticleTag(articleList.get(i).getId()));
            }
        }
    }

    ////////////////////////////////////////////////////////////
    private void register() throws SQLException {
        UserManagerImplement userRegister = new UserManagerImplement();
        System.out.println("register page...");
        User user = inputUserField();
        boolean done = userRegister.insertUser(user.getUsername(), user.getNationalCode(), user.getBirthday(), user.getIsActive());
        if (done)
            System.out.println("register successful \n your password is your NationalCode \n waiting for authentication.......");
        else System.out.println("userName or national_code is duplicate");

        startMenu();
    }

    int authId;

    private void loginUser() throws SQLException {
        UserManagerImplement userDb = new UserManagerImplement();
        User user;
        System.out.println("enter you username ");
        String username = input.next();
        System.out.println("enter you password ");
        String password = input.next();
        user = userDb.getUserByUserName(username);
        authId = user.getId();
        System.out.println("1 "+authId);
        if (user.getId() == 0) {
            System.out.println("user not exist");
            System.exit(0);
        }
        if (password.equals(user.getPassword())) {
            if (!user.getIsActive()) {
                System.out.println("wait for account verify...");
                System.exit(0);
            }
            userLoginMenu(user);
        } else
            System.out.println("username or password false");
    }

    private void userLoginMenu(User user) throws SQLException {
        showUserLoginMenu();
        System.out.println("choice one of them: ");
        int select = input.nextInt();
        switch (select) {
            case 1:
                showSpecificArticle();
                break;
            case 2:
                editSpecificArticle();
                break;
            case 3:
                createNewArticle();
                break;
            case 4:
                changePassword();
                System.out.println();
                break;
            default:
                userLoginMenu(user);
                break;
        }
    }

    private void changePassword() throws SQLException {
        UserManagerImplement update = new UserManagerImplement();
        User user;
        System.out.println("enter your last password: ");
        String lastPassword = input.next();
        user = update.getUserById(authId);
        System.out.println("2 "+authId);

        if (lastPassword.equals(user.getPassword())) {
            System.out.println("enter your new password \n your password need (number,lowerCase,UpperCase,character like(#@...)");
            String password = input.next();
            if (cheekPassword(password)) {
                user.setPassword(password);
                update.update(authId, password);
                System.out.println("3 "+authId);

                System.out.println("password changed");
                System.out.println("-----_____-------_______------______");
                System.out.println();
                startMenu();
            } else {
                System.out.println("your password need (number,lowerCase,UpperCase,character like(#@...) ");
                changePassword();
            }
        } else System.out.println("last password false");
    }


    private void showSpecificArticle() throws SQLException {
        ArticleManagerImplement article = new ArticleManagerImplement();
        ArrayList<Article> articles = (ArrayList<Article>) article.getArticlesByUserId(authId);
        System.out.println("4 "+authId);

        for (int i = 0; i < articles.size(); i++) {
            System.out.println(articles.get(i));
        }

    }

    private void createNewArticle() throws SQLException {
        Article article = new Article();
        Category category = new Category();
        Tag tag = new Tag();
        ArticleManagerImplement createArticle = new ArticleManagerImplement();
        //  showCategory
        CategoryManagerImplement createCategory = showCategory();
        System.out.println("Do you want new category? (yes or no)");
        String answer = input.next();
        if (answer.equals("yes")) {
            category = inputCategoryField(category);
            createCategory.insertCategory(category.getTitle(), category.getDescription());
            showCategory();
            createArticle(createCategory, article, createArticle, authId);
            System.out.println("5 "+authId);

            addTag();
        } else if (answer.equals("no")) {
            createArticle(createCategory, article, createArticle, authId);
            System.out.println("6 "+authId);

            addTag();
        }
    }

    //////////////////////////////////
    boolean flag = true, tagFlag = true;

    private void addTag() throws SQLException {
        Tag tag = new Tag();
        TagManagerImplement tagManage = new TagManagerImplement();
        ArticleTagMangerImplement tagRel = new ArticleTagMangerImplement();
        ArticleManagerImplement articleMange = new ArticleManagerImplement();
        String answer;
        showTag();
        do {
            System.out.println("Do you want new TAG? (yes or no)");
            answer = input.next();
            if (answer.equals("yes")) {
                tag = inputTagField(tag);
                tagManage.insertTag(tag.getTitle());
                showTag();
                addTag();
            } else if (answer.equals("no"))
                flag = false;
        } while (flag);

        addTagToArticle(tag, tagManage, tagRel, articleMange);


    }

    private void addTagToArticle(Tag tag, TagManagerImplement tagManage,
                                 ArticleTagMangerImplement tagRel,
                                 ArticleManagerImplement articleMange) throws SQLException {
        String answer;
        int tagId;
        showTag();
        do {
            System.out.println("would you want add new tag to article ? (yes or no) ");
            answer = input.next();
            if (answer.equals("yes")) {
                System.out.println("which tag do you want add to article ? (enter id) ");
                tagId = input.nextInt();
                tag = tagManage.getTagById(tagId);
                if (tag.getId() == tagId) {
                    tagRel.insertArticleTag(articleMange.getMaxId(), tagId);
                    showTag();
                } else System.out.println("tag not found ! ");
            } else
                tagFlag = false;

        } while (tagFlag);
    }


    //////////////////////////////

    private List<Tag> showTag() throws SQLException {
        TagManagerImplement tag = new TagManagerImplement();
        List<Tag> tags = tag.getAllTag();
        for (int i = 0; i < tags.size(); i++) {
            System.out.println(tags.get(i));
        }
        return tags;
    }

    private Tag inputTagField(Tag tag) {
        System.out.println("enter title Tag: ");
        String titleTag = input.next();
        tag.setTitle(titleTag);

        return tag;
    }


    private void createArticle(CategoryManagerImplement createCategory, Article article,
                               ArticleManagerImplement creatArticle, int authId) throws SQLException {
        System.out.println("7 "+authId);

        System.out.println("Which one category do you want [ Enter Id Category ] ?");
        int categoryId = input.nextInt();
        Category categoryById = createCategory.getCategoryById(categoryId);
        article = inputArticleField(article);
        if (categoryById != null) {
            // userId=0
            creatArticle.insertArticle
                    (article.getTitle(), article.getBrief(), article.getContent(), authId,
                            categoryById.getId(), article.getPrice());

            System.out.println("8 "+authId);

        } else System.out.println("category id not exist");
    }

    private Category inputCategoryField(Category category) {
        System.out.println("enter title category: ");
        String titleCategory = input.next();
        category.setTitle(titleCategory);
        System.out.println("enter title description: ");
        String description = input.next();
        category.setDescription(description);

        return category;
    }

    private Article inputArticleField(Article article) {
        System.out.println("enter articleTitle");
        String articleTitle = input.next();
        article.setTitle(articleTitle);

        System.out.println("enter articleBrief ");
        String articleBrief = input.next();
        article.setBrief(articleBrief);

        System.out.println("Enter articleContent ");
        String articleContent = input.next();
        article.setContent(articleContent);

        System.out.println("Enter price ( 0 for free )");
        int price = input.nextInt();
        if (price >= 0)
            article.setPrice(price);

        return article;
    }

    private CategoryManagerImplement showCategory() throws SQLException {
        CategoryManagerImplement category = new CategoryManagerImplement();
        List<Category> categories = category.getAllCategory();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(categories.get(i));
        }
        return category;
    }

    int idArticle;

    private void editSpecificArticle() throws SQLException
    {
        ArticleManagerImplement articleUpdate = new ArticleManagerImplement();
        ArticleTagMangerImplement articleTag = new ArticleTagMangerImplement();
        Article article = new Article();
        List<Article> articles = articleUpdate.getArticlesByUserId(authId);
        System.out.println("9 "+authId);

        for (int i = 0; i < articles.size(); i++)
        {
            System.out.println(articles.get(i));
            articleTag.findArticleTag(articles.get(i).getId());
        }
        System.out.println("which one article do you want update? (entre id)");
        idArticle = input.nextInt();
        System.out.println("enter title: ");
        String title = input.next();
        System.out.println("enter brief: ");
        String brief = input.next();
        System.out.println("enter content");
        String content = input.next();
        System.out.println("do you want published (on or off) ");
        String published = input.next();
        boolean isPublished = false;
        Timestamp publishDate = null;
        if (published.equals("on")) {
            publishDate= new Timestamp(System.currentTimeMillis())  ;
            isPublished = true;
        } else System.out.println();
        System.out.println("enter price : ");
        int price = input.nextInt();
        showCategory();
        System.out.println("which category {enter id} ");
        int categoryId = input.nextInt();
        System.out.println("10 "+authId);

        articleUpdate.update
                (idArticle, title, brief, content, isPublished, publishDate, categoryId, price, authId);
        System.out.println("11 "+authId);

    }

    private void showUserLoginMenu() {
        System.out.println("1) Watch your article");
        System.out.println("2) Edit your article");
        System.out.println("3) Create new article");
        System.out.println("4) Change your password");
    }

    boolean cheek = false, cheekUserName = false, cheekNationalCode = false, cheekBirthday = false;

    // int id = 11;
    private User inputUserField() {
        User user = new User();

        do {
            if (!cheekUserName) {
                System.out.println("please enter your username: ");
                String username = input.next();
                cheekUserName = cheekUserName(username);
                if (cheekUserName)
                    user.setUsername(username);
                else System.out.println("userName false");
            }
            ///////////////
            if (!cheekNationalCode) {
                System.out.println("please enter your nationalCode: ");
                int nationalCode = input.nextInt();
                cheekNationalCode = cheekNationalCode(nationalCode);
                if (cheekNationalCode)
                    user.setNationalCode(nationalCode);
                else System.out.println("nationalCode false");
            }
            ////////////
            if (!cheekBirthday) {
                System.out.println("please enter your Birthday Date");
                int birthday = input.nextInt();
                cheekBirthday = cheekBirthdayDate(birthday);
                if (cheekBirthday)
                    user.setBirthday(birthday);
                else System.out.println("birthday date  false");
            }
            if (cheekBirthday && cheekUserName && cheekNationalCode)
                cheek = true;
            else System.out.println("try again.............");

        } while (!cheek);
        //     id++;
        //     user.setId(id);
        return user;
    }

    private void systemMenu() {
        System.out.println("1) Login User ");
        System.out.println("2) Register ");
        System.out.println("3) Watch Article ");
        System.out.println("4) Login Admin");
        System.out.println("5) Charge wallet");
    }


    //for update
    private boolean cheekPassword(String text) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
        boolean isTrue = Pattern.matches(regex, text);
        return isTrue;
    }

    private boolean cheekNationalCode(int nationalCode) {
        if (nationalCode > 99)
            return true;
        else return false;
    }

    private boolean cheekBirthdayDate(int birthday) {
        if (birthday > 1920 || birthday < 2022)
            return true;
        else return false;
    }

    private boolean cheekUserName(String text) {
        String regex = "^[a-zA-Z]([._-](?![._-])|[a-zA-Z0-9]){3,16}[a-zA-Z0-9]$";
        boolean isTrue = Pattern.matches(regex, text);
        return isTrue;
    }
}
