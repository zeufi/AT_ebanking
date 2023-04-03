package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Readconfig {
    Properties pro;
    Properties pro_staging;

    public Readconfig() throws IOException {
        File srcProd = new File("./Configuration/PROD/config.properties");
        FileInputStream fis_Prod = new FileInputStream(srcProd);
        pro = new Properties();
        pro.load(fis_Prod);

        File srcStaging = new File("./Configuration/STAGING/config.properties");
        FileInputStream fisStaging = new FileInputStream(srcStaging);
        pro_staging = new Properties();
        pro_staging.load(fisStaging);
    }
    public String getAppURL(){
        return pro.getProperty("baseURL");
    }
    public String getAppURL_Staging(){
        return pro_staging.getProperty("baseURL_Staging");
    }
    public String getusername(){
        return pro.getProperty("username");
    }
    public String getpassword(){
        return pro.getProperty("pwd");
    }
    public String getchromepath(){
        return pro.getProperty("chromepath");
    }
    public String getedgepath(){
        return pro.getProperty("edgepath");
    }
    public String getfirefoxpath(){
        return pro.getProperty("firefoxpath");
    }
}
