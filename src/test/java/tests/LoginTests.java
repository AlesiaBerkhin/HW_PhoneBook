package tests;

import manager.NGListener;
import manager.ProviderData;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NGListener.class)
public class LoginTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }

    @Test(groups ={"positive"})
    public void loginPositiveTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy@mail.ru", "Kk12345!");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }

    @Test(groups ={"positive"})
    public void loginPositiveTestModel1(){

        User user = User.builder()
                .email("katy@mail.ru")
                .password("Kk12345!")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }
    @Test(groups ={"positive"})
    public void loginPositiveTestModelProps(){

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(app.getEmail(), app.getPassword());
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }
    @Test(groups ={"positive"}, dataProvider = "userDTO", dataProviderClass = ProviderData.class)
    public void loginPositiveTestUserDTO(User user){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user.getEmail(), user.getPassword());
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.tagName("button")));
    }

    @Test(groups = {"negative", "smoke"})
    public void loginNegativeTestWrongEmail(){

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy_mail.ru","Kk12345!");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }

    @Test(groups = {"negative"})
    public void loginNegativeTestWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("katy@mail.ru","Kk12345");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(3000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
    }

}
