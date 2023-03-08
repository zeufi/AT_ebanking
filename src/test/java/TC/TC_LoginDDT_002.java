package TC;

import POM.LoginPage;
import Utilities.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass{
    public TC_LoginDDT_002() throws IOException {
    }
	@Test(groups = {"Chrome", "Firefox"}, dataProvider="LoginData")
	public void loginDDT(String username, String password) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setTxtUsername(username);
		logger.info("user name provided");
		lp.setTxtPassword(password);
		logger.info("password provided");
		lp.setBtnLogin();

		Thread.sleep(3000);

		if(isAlertPresent())
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent(); //Focus on the main page
            Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();

		}
	}




	@DataProvider(name="LoginData")
    Object[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\LoginData.xlsx";

		int rownum= XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);

		String[][] logindata = new String[rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
			    //Because 2 dimensional array start from 0, and our raw data start from the raw 1
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
			}
		}
	return logindata;
	}
}
