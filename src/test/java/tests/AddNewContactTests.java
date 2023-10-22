package tests;

import manager.ProviderData;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

   @BeforeMethod(alwaysRun = true)
   public void preconditions(){
       if(!app.getHelperUser().isLogged()) app.getHelperUser().login(
//               User.builder()
//                       .email("katy@mail.ru")
//                       .password("Kk12345!")
//                       .build()
app.getEmail(), app.getPassword()
       );
   }

    @Test(invocationCount = 1, groups ={"positive", "smoke"})
    public void addNewContactPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Lola")
                .lastName("Boba")
                .phone("12345678" + i)
                .email("lola_" + i + "@mail.ru")
                .address("Rehovot")
                .discription("Pirate")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        Assert.assertTrue(app.getHelperContact().isContactCreated(contact));
    }
    @Test( groups ={"positive", "smoke"}, dataProvider = "contactDTO", dataProviderClass = ProviderData.class)
    public void addNewContactPositiveDTO(Contact contact) {

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        Assert.assertTrue(app.getHelperContact().isContactCreated(contact));
    }


}
