package TC;

import Utilities.Readconfig;
import Utilities.RandomEmailGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    Readconfig readconfig = new Readconfig();
    RandomEmailGenerator randomEmailGenerator= new RandomEmailGenerator();
    public String baseURL = readconfig.getAppURL();
    public String baseURL_Staging = readconfig.getAppURL_Staging();
    public String username = readconfig.getusername();
    public String password = readconfig.getpassword();
    public String email = randomEmailGenerator.generateEmail();

    public static WebDriver driver;
    public static Logger logger;

    public BaseClass() throws IOException {
    }

    @Parameters({"browser", "env"})
    @BeforeTest
    public void setup(String br, String env){
        logger = Logger.getLogger("eBanking");
        PropertyConfigurator.configure("log4j.properties");
        String driverExtention = "";
        if(System.getenv("RUNNER_OS") != null) {
            driverExtention = "-linux";
        }
        if ("firefox".equals(br)) {
            System.setProperty("webdriver.gecko.driver", readconfig.getfirefoxpath() + driverExtention);
            driver = new FirefoxDriver(getFirefoxOptions());
        } else if ("edge".equals(br)) {
            System.setProperty("webdriver.edge.driver", readconfig.getedgepath() + driverExtention);
            driver = new EdgeDriver();
        } else if ("chrome".equals(br)){
            System.setProperty("webdriver.chrome.driver", readconfig.getchromepath() + driverExtention);
//            System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
//            System.setProperty("webdriver.chrome.driver.wait", "30");
            driver = new ChromeDriver(getChromeOptions());
        }
        //System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");

        if ("staging".equals(env)) {
            driver.get(baseURL_Staging);
        } else if("prod".equals(env)) {
            driver.get(baseURL);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
//        options.addArguments("--user-data-dir=/tmp/chrome-data");
        options.addArguments("--headless");
//        options.setBinary("/usr/bin/google-chrome");    //chrome binary location
        options.addArguments("--disable-dev-shm-usage");

        // Default headless mode off, set to true based on env var
        var headless = Boolean.parseBoolean(System.getenv("HEADLESS_CHROME")) | false;
        options.setHeadless(headless);
        return options;
    }
    private FirefoxOptions getFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        // Default headless mode off, set to true based on env var
        var headless = Boolean.parseBoolean(System.getenv("HEADLESS_FIREFOX")) | false;
        options.setHeadless(headless);
        return options;
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        //driver.quit();
    }
    public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\VideosAndScreenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}

	public String randomestring()
	{
        return(RandomStringUtils.randomAlphabetic(8));
	}

	public static String randomeNum() {
        return (RandomStringUtils.randomNumeric(4));
	}
}
