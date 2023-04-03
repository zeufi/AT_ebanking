
package POM;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegistrationPage {
    WebDriver ldriver;
    public RegistrationPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
    }
    @FindBy(xpath = "//*[@id='details-button']")
    WebElement Advanced;
    @FindBy(xpath = "//*[@id='proceed-link']") WebElement Continue;
    @FindBy(css = "table:nth-child(6) tbody:nth-child(1) tr:nth-child(5) td:nth-child(2) > input:nth-child(1)") WebElement txtEmail;
    @FindBy(css = "table:nth-child(6) tbody:nth-child(1) tr:nth-child(6) td:nth-child(2) > input:nth-child(1)") WebElement btnSubmit;
    @FindBy(xpath = "/html/body/h4/center") WebElement textDisplayed;
    public void setTxtUserEmail(String Useremail) {
        txtEmail.sendKeys(Useremail);
    }
    public void setBtnSubmit() {
        JavascriptExecutor executor = (JavascriptExecutor)ldriver;
        executor.executeScript("arguments[0].click();", btnSubmit);
    }
    public void setAdvanced() {
        Advanced.click();
    }
    public void setContinue() {
        Continue.click();
    }
    public boolean setTextDisplayed() {
        textDisplayed.getText().contains("Please take SCREESNSHOT of this page for future reference.");
        return true;
    }
}
