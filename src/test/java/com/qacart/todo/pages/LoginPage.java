package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="[data-testid=\"email\"]")
    private WebElement emailInput;

    @FindBy(css="[data-testid=\"password\"]")
    private WebElement passwordInput;
    @FindBy(css="[data-testid=\"submit\"]")
    private WebElement submit;

    @Step

    public TodoPage login(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submit.click();
        return new TodoPage(driver);

    }
    @Step("Load the Login Page")
    public LoginPage load(){

        driver.get(ConfigUtils.getInstance().getBaseUrl());
        return this;
    }

}

