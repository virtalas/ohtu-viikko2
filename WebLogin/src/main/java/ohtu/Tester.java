package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        
        
        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        
        driver.get("http://localhost:4567");
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1234");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
        
        driver.get("http://localhost:4567");
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka1234");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // uuden käyttäjätunnuksen luominen
        
        driver.get("http://localhost:4567");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep1");
        element = driver.findElement(By.name("signup"));
        element.submit();
        
        // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        //
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*0);
        } catch(Exception e){}
    }
}
