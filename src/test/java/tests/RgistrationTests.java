package tests;

import manager.ProviderData;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RgistrationTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }

    @Test(groups ={"positive"})
    public void registrationPositiveTest(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "katy_" + i + "@mail.ru";
        String password = "Kk12345!";
     //   User user = new User("katy_" + i + "@mail.ru", "Kk12345!");
        app.getHelperUser().openLoginRegistrationForm();

        // int i = (int)(System.currentTimeMillis()/1000)%3600;
       // app.getHelperUser().fillRegistrationForm("kat_" + i + "@mail.ru", "Kk12345!");
     //   app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(5000);
        logger.info("registrationPositiveTest" + email + "&" + password);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }
    @Test(groups ={"positive"}, dataProvider = "registrationCSV", dataProviderClass = ProviderData.class)
    public void registrationPositiveTestCSV(User user){
        String email = user.getEmail();
        String password = user.getPassword();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(5000);
        logger.info("registrationPositiveTest" + email + "&" + password);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }


    @Test(groups = {"negative"})
    public void registrationNegativeTestWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katymail.ru","Kk12345!");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }

    @Test(groups = {"negative"})
    public void registrationNegativeTestWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy@mail.ru", "Kk12345");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }



}
