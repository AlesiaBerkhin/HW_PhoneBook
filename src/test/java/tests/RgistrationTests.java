package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RgistrationTests extends TestBase{

    @Test
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



    @Test
    public void registrationNegativeTestWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katymail.ru","Kk12345!");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }

    @Test
    public void registrationNegativeTestWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy@mail.ru", "Kk12345");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }



}
