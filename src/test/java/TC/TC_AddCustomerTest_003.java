package TC;

import java.io.IOException;
import POM.AddCustomerPage;
import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AddCustomerTest_003 extends BaseClass
{
    public TC_AddCustomerTest_003() throws IOException {
    }

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setTxtUsername(username);
		logger.info("user name provided");
		lp.setTxtPassword(password);
		logger.info("password provided");
		lp.setBtnLogin();
		Thread.sleep(3000);

		AddCustomerPage addcust=new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();

		logger.info("providing customer details....");
		addcust.custName("Jojo");
		addcust.custgender("male");
		addcust.custdob("10","15","1985");
		Thread.sleep(3000);
		addcust.custaddress("NewYork");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");

		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		Thread.sleep(3000);

		logger.info("validation started....");
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

		if(res)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
		}
		else
		{
			if(isAlertPresent())
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent(); //Focus on the main page
            Assert.fail();
			logger.warn("Login failed");
		}
		}
	}
}
