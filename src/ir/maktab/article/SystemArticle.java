package ir.maktab.article;

import com.mysql.cj.jdbc.ConnectionImpl;
import ir.maktab.article.entity.Article;
import ir.maktab.article.entity.Category;
import ir.maktab.article.entity.User;
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

    public void startMenu() throws SQLException
    {
        systemMenu();
        System.out.println("choice one of them: (between 1 or 4 )");
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
            default:
                startMenu();
        }
    }

    private void loginAdmin() {}

    private void showArticle() throws SQLException
    {
        ArticleManagerImplement articles = new ArticleManagerImplement();
        List<Article> articleList = articles.getAllArticle();
        Article article;
        for (int i = 0; i < articleList.size(); i++)
        {
            System.out.println("id: " + articleList.get(i).getId() + " title : "
                    + articleList.get(i).getTitle() + " Brief: " + articleList.get(i).getBrief());
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("which one do you like to see complete ?");
        System.out.print("enter id: ");
        int id = input.nextInt();
        article = articles.getArticleById(id);

        //id == articleList.get(i).getId()

        if (article.getId() != 0)
            System.out.println(article.toString());
        else
            System.out.println("not found");
    }

    private void register() throws SQLException
    {
        UserManagerImplement userRegister = new UserManagerImplement();
        System.out.println("register page...");
        User user = inputUserField();
        userRegister.insertUser(user.getUsername(), user.getNationalCode(), user.getBirthday(), user.getIsActive());
        System.out.println("register successful \n your password is your NationalCode \n waiting for authentication.......");

        startMenu();
    }
    int authId;
    private void loginUser() throws SQLException
    {
        UserManagerImplement userDb=new UserManagerImplement();
        User user ;
        System.out.println("enter you username ");
        String username=input.next();
        System.out.println("enter you password ");
        String password=input.next();
        user =  userDb.getUserByUserName(username);
        authId= user.getId();
        if (user.getId()==0)
        {
            System.out.println("user not exist");
            System.exit(0);
        }
        if (password.equals(user.getPassword()))
        {
            if (!user.getIsActive())
            {
                System.out.println("wait for account verify..");
                System.exit(0);
            }
            userLoginMenu(user);
        }
        else
            System.out.println("username or password false");
    }

    private void userLoginMenu(User user) throws SQLException
    {
        showUserLoginMenu();
        System.out.println("choice one of them: ");
        int select=input.nextInt();
        switch (select)
        {
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
                //edit password or edit all property
                System.out.println();
                break;
            default:userLoginMenu(user);
            break;
        }
    }


    private void showSpecificArticle() throws SQLException
    {
     ArticleManagerImplement article = new ArticleManagerImplement();
     ArrayList<Article> articles = (ArrayList<Article>) article.getArticlesByUserId(authId);
        for (int i = 0; i <articles.size(); i++)
        {
            System.out.println(articles.get(i));
        }

    }


    private void createNewArticle() throws SQLException
    {
        Article article = new Article();
        Category category = new Category();
        ArticleManagerImplement creatArticle = new ArticleManagerImplement();
        //  showCategory
        CategoryManagerImplement  createCategory= showCategory();
        System.out.println("Do you want new category? (yes or no)");
        String answer=input.next();
        if (answer.equals("yes"))
        {
              category= inputCategoryField(category);
              createCategory.insertCategory(category.getTitle(),category.getDescription());
              showCategory();
              createArticle( createCategory,article, creatArticle,authId);

        }  else if (answer.equals("no"))
        {
            createArticle( createCategory,article, creatArticle,authId);
        }
    }
    private void createArticle(CategoryManagerImplement createCategory, Article article , ArticleManagerImplement creatArticle,int authId)
            throws SQLException
    {
        System.out.println("Which one category do you want [ Enter Id Category ] ?");
        int categoryId =input.nextInt();
        Category categoryById = createCategory.getCategoryById(categoryId);
        article = inputArticleField(article);
        if (categoryById!=null)
        {
            // userId=0

            creatArticle.insertArticle(article.getTitle(), article.getBrief(), article.getContent(),authId, categoryById.getId());

        }else System.out.println("category id not exist");
    }
   private Category inputCategoryField(Category category)
   {
       System.out.println("enter title category: ");
       String titleCategory=input.next();
       category.setTitle(titleCategory);
       System.out.println("enter title description: ");
       String description=input.next();
       category.setDescription(description);

       return category;
   }
    private Article inputArticleField(Article article)
    {
        System.out.println("enter articleTitle");
        String articleTitle=input.next();
        article.setTitle(articleTitle);

        System.out.println("enter articleBrief");
        String articleBrief=input.next();
        article.setBrief( articleBrief);

        System.out.println("Enter articleContent");
        String articleContent=input.next();
        article.setContent(articleContent);

        return article;
    }
    private CategoryManagerImplement showCategory() throws SQLException
    {
        CategoryManagerImplement category = new CategoryManagerImplement();
        List<Category> categories =category.getAllCategory();
        for (int i = 0; i < categories.size(); i++)
        {
            System.out.println(categories.get(i));
        }
        return category;
    }

    private void editSpecificArticle()
    {
        ArticleManagerImplement articleUpdate = new ArticleManagerImplement();
      //  articleUpdate.update()
    }

    private void showUserLoginMenu() {
        System.out.println("1) Watch your article");
        System.out.println("2) Edit your article");
        System.out.println("3) Create new article");
        System.out.println("4) Do you want change you password ?");
    }

    boolean cheek = false, cheekUserName = false, cheekNationalCode = false, cheekBirthday = false;
    // int id = 11;
    private User inputUserField() {
        User user = new User();

        do {
            if (!cheekUserName)
            {
                System.out.println("please enter your username: ");
                String username = input.next();
                cheekUserName = cheekUserName(username);
                if (cheekUserName)
                    user.setUsername(username);
                else System.out.println("userName false");
            }
            ///////////////
            if (!cheekNationalCode)
            {
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
            if (cheekBirthday  && cheekUserName && cheekNationalCode )
                cheek = true;
            else System.out.println("try again.............");

        } while (!cheek);
   //     id++;
   //     user.setId(id);
        return user;
    }

    public void systemMenu()
    {
        System.out.println("1) Login User ");
        System.out.println("2) Register ");
        System.out.println("3) Watch Article ");
        System.out.println("4) Login Admin");
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
