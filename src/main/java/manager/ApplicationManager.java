package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);


    WebDriver wd;

    HelperUser helperUser;

    public void init() {
        String link = "https://telranedu.web.app/home";
        wd = new ChromeDriver();
        wd.navigate().to(link);
      //  wd.navigate().to("https://telranedu.web.app/home");
        logger.info("Navigate to the link---->" + link);
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        helperUser = new HelperUser(wd);
    }

    public void tearDown() {
        wd.quit();
        logger.info("Test completed");
    }

    public HelperUser getHelperUser() {

        return helperUser;
    }
}
