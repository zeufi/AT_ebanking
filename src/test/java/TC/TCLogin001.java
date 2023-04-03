package TC;

import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedList;

public class TCLogin001 extends BaseClass {
    public TCLogin001() throws IOException {
    }

    @Test
    @Parameters({"browser", "env"})
    public void loginTest(String br, String env) throws IOException, InterruptedException {
        logger.info("============== TC_LoginTest_001 Started ================");
        logger.info("*************** URL is Open ********************");
        logger.info("Browser parameter value is: " + br);
        logger.info("Environment parameter value is: " + env);
        LoginPage lp = new LoginPage(driver);
        if (driver.getPageSource().contains("Your connection isn't private") ||
                driver.getPageSource().contains("Your connection is not private")){
            lp.setAdvanced();
            lp.setContinue();
        }else {
            logger.info("*************** Enter credentials ********************");
            lp.setTxtUsername(username);
            lp.setTxtPassword(password);
            lp.setBtnLogin();
            logger.info("*************** Start validation ********************");
            LinkedList<String> currentTitle = new LinkedList<>();
            if("firefox".equals(br)){
                currentTitle.add("Guru99 Bank Home Page");
            }else if("chrome".equals(br)){
                currentTitle.add("Guru99 Bank Manager HomePage");
            }
            System.out.println(driver.getTitle());
            if (driver.getTitle().equals(currentTitle.get(0))) {
                Assert.assertTrue(true);
                logger.info("*************** LoginTest Passed ********************");
            } else {
                logger.info("*************** LoginTest Failed ********************");
                captureScreen(driver,"loginTest");
			    Assert.fail();
			    logger.info("Login test failed");
            }
        }
    }
}
