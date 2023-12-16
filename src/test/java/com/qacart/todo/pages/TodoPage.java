package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.production.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoPage extends BasePage {
    public TodoPage(WebDriver driver){
        super(driver);
    }
    @FindBy(css="[data-testid=\"welcome\"]")
    private WebElement welcomeMessage;
    @FindBy(css="[data-testid=\"add\"]")
    private WebElement addButton;



    @FindBy(css="[data-testid=\"todo-item\"]")
    private WebElement todoItem;
    @FindBy(css="[data-testid=\"delete\"]")
    private WebElement deleteButton;
    @FindBy(css="[data-testid=\"no-todos\"]")
    private WebElement noTodosMessage;
    public boolean isWelcomeMessageDisplayed(){
        return welcomeMessage.isDisplayed();
    }
    public NewTodoPage ClickOnAddButton(){
        addButton.click();
        return new NewTodoPage(driver);
    }
@Step
    public String getTodoText(){
      return  todoItem.getText();
    }
    @Step
    public TodoPage ClickOnDeleteButton(){
        deleteButton.click();
        return this;
    }
    public boolean isNoTodosMessageDisplayed(){

        return noTodosMessage.isDisplayed();
    }
    @Step
    public TodoPage load(){
        driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoint.TODO_PAGE_ENDPOINT);
        return this;
    }
}
