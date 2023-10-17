package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    static ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME)
    );

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startTest(Method method){
        logger.info("Started test ---->" + method.getName());
    }

    @AfterMethod
    public void stopTest(Method method){

        logger.info("Finished test ---->" + method.getName());
        logger.info("==================================================" );

    }

    @BeforeSuite
    public void setup() {

        app.init();
    }

    @AfterSuite
    public void stop() {
        app.tearDown();
    }
}
