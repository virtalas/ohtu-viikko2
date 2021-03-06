package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }
    
    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String arg1, String arg2) throws Throwable {
        command_new_user_is_selected();
        signUpWith(arg1, arg2);
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String arg1, String arg2) throws Throwable {
        command_new_user_is_selected();
        signUpWith(arg1, arg2);
    }
    
    @When("^an invalid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void an_invalid_username_and_password_and_matching_password_confirmation_are_entered(String arg1, String arg2) throws Throwable {
        signUpWith(arg1, arg2);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_too_short_password_and_matching_password_confirmation_are_entered(String arg1, String arg2) throws Throwable {
        signUpWith(arg1,arg2);
    }
    
    @When("^a taken username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_taken_username_and_password_and_matching_password_confirmation_are_entered(String arg1, String arg2) throws Throwable {
        signUpWith(arg1,arg2);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and different password \"([^\"]*)\" are entered$")
    public void a_valid_username_and_password_and_different_password_are_entered(String arg1, String arg2, String arg3) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(arg1);
        element = driver.findElement(By.name("password"));
        element.sendKeys(arg2);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(arg3);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String arg1) throws Throwable {
        pageHasContent(arg1);
    }
    
    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        pageHasContent("info for newbie");
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @When("^incorrect username \"([^\"]*)\" and correct password \"([^\"]*)\" are given$")
    public void incorrect_username_and_correct_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
    
    private void signUpWith(String username, String password) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    } 
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
