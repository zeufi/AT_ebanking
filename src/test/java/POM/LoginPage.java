package POM;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
    WebDriver ldriver;

    public LoginPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
    }
    @FindBy(xpath = "//*[@id='details-button']") WebElement Advanced;
    @FindBy(xpath = "//*[@id='proceed-link']") WebElement Continue;
    @FindBy(name = "uid") WebElement txtUsername;
    @FindBy(name = "password") WebElement txtPassword;
    @FindBy(css = "table:nth-child(2) tbody:nth-child(1) tr:nth-child(3) td:nth-child(2) > input:nth-child(1)") WebElement btnLogin;
    @FindBy(linkText = "Log out") WebElement logout;

    public void setTxtUsername(String Username) {
        txtUsername.sendKeys(Username);
    }
    public void setTxtPassword(String Password) {
        txtPassword.sendKeys(Password);
    }
    public void setBtnLogin() {
        JavascriptExecutor executor = (JavascriptExecutor)ldriver;
        executor.executeScript("arguments[0].click();", btnLogin);
    }
    public void setAdvanced() {
        Advanced.click();
    }
    public void setContinue() {
        Continue.click();
    }
    public void clickLogout() {
        logout.click();
    }
}
