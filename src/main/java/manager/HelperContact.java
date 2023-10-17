package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperContact extends HelperBase{

    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.xpath("//a[.='ADD']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDiscription());
    }

    public void submitContactForm() {
        click(By.xpath("//button[.='Save']"));
    }

    public boolean isContactCreated(Contact contact){
        String phone = wd.findElement(By.xpath(
                "//div[@class='contact-item_card__2SOIM'][last()]//h3")).getText();
        return phone.equals(contact.getPhone());
    }

}
