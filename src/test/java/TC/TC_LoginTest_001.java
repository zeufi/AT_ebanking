package TC;

import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedList;

public class TC_LoginTest_001 extends BaseClass{
    public TC_LoginTest_001() throws IOException {
    }

    @Test(groups = {"Chrome", "Firefox"})
    @Parameters("browser")
    public void loginTest(String br) throws IOException {
        logger.info("============== TC_LoginTest_001 Started ================");
        logger.info("*************** URL is Open ********************");
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
