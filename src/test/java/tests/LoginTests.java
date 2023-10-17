package tests;

import manager.NGListener;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NGListener.class)
public class LoginTests extends TestBase{



    @Test
    public void loginPositiveTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy@mail.ru", "Kk12345!");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }

    @Test
    public void loginPositiveTestModel1(){

        User user = User.builder()
                .email("abc@def.com")
                .password("$Abcdef12345")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }

    @Test
    public void loginNegativeTestWrongEmail(){

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katymail.ru","Kk12345!");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }

    @Test
    public void loginNegativeTestWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy@mail.ru","Kk12345");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }

}
