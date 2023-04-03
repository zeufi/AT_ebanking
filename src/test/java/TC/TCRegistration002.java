package TC;

import POM.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;


public class TCRegistration002 extends BaseClass {
    public TCRegistration002() throws IOException {
    }

    @Test
    @Parameters({"browser", "env"})
    public void loginTest(String br, String env) throws IOException {
        logger.info("============== TC_LoginTest_001 Started ================");
        logger.info("*************** URL is Open ********************");
        logger.info("Browser parameter value is: " + br);
        logger.info("Environment parameter value is: " + env);
        RegistrationPage reg = new RegistrationPage(driver);
        if (driver.getPageSource().contains("Your connection isn't private") ||
                driver.getPageSource().contains("Your connection is not private")){
            reg.setAdvanced();
            reg.setContinue();
        }else {
            logger.info("*************** Enter Email ********************");
            reg.setTxtUserEmail(email);
            reg.setBtnSubmit();
            logger.info("*************** Start validation ********************");
            String expectedText = "Please take SCREESNSHOT of this page for future reference.";
            if (reg.setTextDisplayed()) {
                Assert.assertTrue(true);
                logger.info("*************** RegistrationTest Passed ********************");
            } else {
                logger.info("*************** RegistrationTest Failed ********************");
                captureScreen(driver,"RegistrationTest");
			    Assert.fail();
			    logger.info("Registration Test Failed");
            }
        }
    }
}
