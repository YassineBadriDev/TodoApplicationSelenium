package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.production.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTodoPage extends BasePage {
    public NewTodoPage(WebDriver driver) {

        super(driver);
    }
    @FindBy(css="[data-testid=\"new-todo\"]")
    private WebElement NewTodoInput;

    @FindBy(css="[data-testid=\"submit-newTask\"]")
    private WebElement NewTodoSubmit;
 public NewTodoPage load(){
     driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoint.NEW_TODO_END);
     return this;
 }
 @Step("Add new task")
    public TodoPage addNewTask(String item){
        NewTodoInput.sendKeys(item);
        NewTodoSubmit.click();
        return new TodoPage(driver);

    }
}
