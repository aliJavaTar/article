package ir.maktab.article;

import com.mysql.cj.jdbc.ConnectionImpl;
import ir.maktab.article.entity.Article;
import ir.maktab.article.entity.Category;
import ir.maktab.article.entity.User;
import ir.maktab.article.repository.Admin;
import ir.maktab.article.repository.service.ArticleManagerImplement;
import ir.maktab.article.repository.service.CategoryManagerImplement;
import ir.maktab.article.repository.service.UserManagerImplement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    private void loginAdmin()
            throws SQLException {
        Admin admin = new Admin();
        System.out.println("enter you username");
        String username = input.next();
        System.out.println("enter your password");
        String password = input.next();
        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            admin.verifyUser();
        } else System.out.println("username or password false");

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

    private void showMonetaryArticles(List<Article> articleList) {
        condition = "money";
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getPrice() > 0) {
                System.out.println("id: " + articleList.get(i).getId() + " title : "
                        + articleList.get(i).getTitle() + " Brief: " +
                        articleList.get(i).getBrief() + " price: " + articleList.get(i).getPrice());
            }
        }
    }

    private void showFreeArticles(List<Article> articleList) {
        condition = "free";
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getPrice() == 0) {
                System.out.println("id: " + articleList.get(i).getId() + " title : "
                        + articleList.get(i).getTitle() + " Brief: " +
                        articleList.get(i).getBrief() + " price: " + articleList.get(i).getPrice());
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
        User user ;
        System.out.println("enter your last password: ");
        String lastPassword=input.next();
         user =update.getUserById(authId);
         if (lastPassword.equals(user.getPassword()))
         {
             System.out.println("enter your new password \n your password need (number,lowerCase,UpperCase,character like(#@...)");
             String password=input.next();
             if (cheekPassword(password))
             {
                 user.setPassword(password);
                 update.update(authId, password);
                 System.out.println("password changed");
                 System.out.println("-----_____-------_______------______");
                 System.out.println();
                 startMenu();
             }else {
                 System.out.println("your password need (number,lowerCase,UpperCase,character like(#@...) ");
                 changePassword();
             }
         }else System.out.println("last password false");
    }


    private void showSpecificArticle() throws SQLException {
        ArticleManagerImplement article = new ArticleManagerImplement();
        ArrayList<Article> articles = (ArrayList<Article>) article.getArticlesByUserId(authId);
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(articles.get(i));
        }

    }


    private void createNewArticle() throws SQLException {
        Article article = new Article();
        Category category = new Category();
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

        } else if (answer.equals("no")) {
            createArticle(createCategory, article, createArticle, authId);
        }
    }

    private void createArticle(CategoryManagerImplement createCategory,
                               Article article, ArticleManagerImplement creatArticle, int authId)
            throws SQLException {
        System.out.println("Which one category do you want [ Enter Id Category ] ?");
        int categoryId = input.nextInt();
        Category categoryById = createCategory.getCategoryById(categoryId);
        article = inputArticleField(article);
        if (categoryById != null) {
            // userId=0
            creatArticle.insertArticle(article.getTitle(), article.getBrief(),
                    article.getContent(), authId, categoryById.getId(), article.getPrice());

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
        String articleContent = input.nextLine();
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

    private void editSpecificArticle() {
        ArticleManagerImplement articleUpdate = new ArticleManagerImplement();
        //  articleUpdate.update()
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

    public void systemMenu() {
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

    private boolean cheekNationalCode(int nationalCode)
    {
        if (nationalCode > 99)
            return true;
        else return false;
    }

    private boolean cheekBirthdayDate(int birthday)
    {
        if (birthday > 1920 || birthday < 2022)
            return true;
        else return false;
    }

    private boolean cheekUserName(String text)
    {
        String regex = "^[a-zA-Z]([._-](?![._-])|[a-zA-Z0-9]){3,16}[a-zA-Z0-9]$";
        boolean isTrue = Pattern.matches(regex, text);
        return isTrue;
    }
}
