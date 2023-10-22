package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);


//    WebDriver wd;
    EventFiringWebDriver wd;

    HelperUser helperUser;

    HelperContact helperContact;

    String browser;

    Properties properties;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {

//      properties.load(new FileReader(new File("src/test/resources/prod.properties")));

 //     String link = "https://telranedu.web.app/home";
  //    String link = properties.getProperty("web.baseURL");
        String target = System.getProperty("target","pre_prod" );
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        String link = properties.getProperty("web.baseURL");

//        wd = new ChromeDriver();
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
          logger.info("Test started on Chrome");
        }
        else if (browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Test started on FireFox");
        }
        wd.register(new WDListener());
        wd.navigate().to(link);
      //  wd.navigate().to("https://telranedu.web.app/home");
        logger.info("Navigate to the link---->" + link);
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
    }

    public void tearDown() {
        wd.quit();
        logger.info("Test completed");
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }
    public HelperContact getHelperContact() {
        return helperContact;
    }

    public String getEmail() {
        return properties.getProperty("web.email");
    }
    public String getPassword() {
        return properties.getProperty("web.password");
    }


}
