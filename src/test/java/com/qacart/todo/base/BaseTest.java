package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.CookieUtils;
import io.qameta.allure.Allure;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    public WebDriver getDriver(){
        return this.driver;
    }

    @BeforeMethod
    public void setup (){
        driver = new DriverFactory().initializeDriver();
    }
    @AfterMethod
    public void teardown(ITestResult result){
        String TestCaseName= result.getMethod().getMethodName();
        File destFile= new File("screenshots"+File.separator+TestCaseName+".png");
        takeScreenshot(destFile);
        driver.quit();
    }
    public void injectCookiesToBrowser(List<Cookie> restAssuredCookies){
      List<org.openqa.selenium.Cookie> seleniumCookies= CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAssuredCookies);
      for(org.openqa.selenium.Cookie cookie: seleniumCookies){
          driver.manage().addCookie(cookie);


      }
    }
    public void takeScreenshot(File destFile){
        File file=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,destFile);
            InputStream is = new FileInputStream(destFile);
            Allure.addAttachment("screenshot",is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
