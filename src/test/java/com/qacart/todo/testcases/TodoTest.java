package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TaskApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
@Feature("Todo Feature")
public class TodoTest extends BaseTest {

    @Test (enabled = false,description="Should be Able to Add new Todo Correctly")
    @Story("Adding new TODO")
    public void ShouldBeAbleToAddNewTodo (){

        RegisterApi registerApi= new RegisterApi();
        registerApi.register();

        injectCookiesToBrowser(registerApi.getCookies());
        NewTodoPage newTodoPage = new NewTodoPage(driver);
        newTodoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());
         String ActualResult= newTodoPage
                 .load()
                .addNewTask("learn java")
                    .getTodoText();

       /* LoginPage loginPage = new LoginPage(driver);
 String ActualResult = loginPage.
                load().
                login(ConfigUtils.getInstance().getEmail(),ConfigUtils.getInstance().getPassword()).
                ClickOnAddButton().
                addNewTask("Learning some").getTodoText();*/





     //   driver.findElement(By.cssSelector("[data-testid=\"submit-newTask\"]")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

     //String ActualResult = todoPage.getTodoText();
     Assert.assertEquals(ActualResult,"learn java");




    }
    @Test (description = "should be Able to delete correctly")
    public void shouldBeAbleToDelete(){

        RegisterApi registerApi= new RegisterApi();
        registerApi.register();
       TaskApi taskApi = new TaskApi();
       taskApi.addTask(registerApi.getToken());

        TodoPage todoPage = new TodoPage(driver);
        todoPage.load();

         injectCookiesToBrowser(registerApi.getCookies());

        boolean isNotTodoMessageDisplayed= todoPage
                .load()
                .ClickOnDeleteButton().isNoTodosMessageDisplayed();



        Assert.assertTrue(isNotTodoMessageDisplayed);



    }
}
