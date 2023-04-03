package SecurityTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import Utilities.Readconfig;
import org.zaproxy.clientapi.core.ClientApiException;
import java.io.IOException;


public class ZapSecurityTest {
    static final String ZAP_PROXY_ADDRESS = "127.0.0.1";
    static final int ZAP_PROXY_PORT = 8080;
    static final String ZAP_API_KEY = "ba3urdi74op81ehptf2lkq2o5j";
    Readconfig readconfig = new Readconfig();
    public String baseURL = readconfig.getAppURL();
    private WebDriver driver;
    private ClientApi api;

    public ZapSecurityTest() throws IOException {
    }

    @BeforeMethod
    public void setup(){
        String proxyServerUrl = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServerUrl);
        proxy.setSslProxy(proxyServerUrl);

        ChromeOptions co = new ChromeOptions();
        co.setAcceptInsecureCerts(true);
        co.setProxy(proxy);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(co);
        api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
    }

    @Test
    public void demoGuru99(){
        driver.get(baseURL);
        Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
    }

    @AfterMethod
    public void tearDown() {
        if(api != null) {
            try {
                String title = "Guru99 ZAP Security Report";
                String template = "traditional-html";
                String description = "This is Guru99 ZAP Security Report";
                String reportfilename = "/zap_scan/Guru99-zap-report.html";
                String reportdir = System.getProperty("user.dir");

                // Generate the ZAP report
                ApiResponse response = api.reports.generate(title, template, null, description, null, null, null,
                        null, null, reportfilename, null,
                        reportdir, null);

                // Check the response status code
                if(response != null) {
                    System.out.println("ZAP report generated at this location: " + response);
                } else {
                    System.err.println("Failed to generate ZAP report: " + response.toString());
                }
            } catch (ClientApiException e) {
                System.err.println("Error generating ZAP report: " + e.getMessage());
            }
        }
        driver.quit();
    }
}
