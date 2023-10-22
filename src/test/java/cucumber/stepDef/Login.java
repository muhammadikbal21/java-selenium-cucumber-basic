package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Login {

    WebDriver driver;
    String baseUrl = "https://kasirdemo.belajarqa.com/";

    @Given("User on login page")
    public void user_on_login_page() {

        // setup awal menggunakan chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(), 'hai, kasirAja')]")).getText();
        Assert.assertEquals("hai, kasirAja", loginPageAssert);
    }

    @When("Input username")
    public void input_username() {
        driver.findElement(By.id("email")).sendKeys("ikbalshop@shop.com");
    }

    @And("Input password")
    public void input_password() {
        driver.findElement(By.id("password")).sendKeys("123456");
    }

    @And("Click login button")
    public void click_login_button() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User will be on dashboard page")
    public void user_will_be_on_dashboard_page() throws InterruptedException {
        driver.findElement(By.xpath("//div[contains(text(), 'dashboard')]"));
        String welcomeAssert = driver.findElement(By.xpath("//dd[contains(text(), 'hai')]")).getText();
        String usernameAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/dl/dt")).getText();
        Assert.assertEquals("hai", welcomeAssert);
        Assert.assertEquals("Ikbal Shop", usernameAssert);

        Thread.sleep(3000); // delay 5 detik
        driver.close();
    }

    @And("Input invalid password")
    public void input_invalid_password() {
        driver.findElement(By.id("password")).sendKeys("1111111");

    }

    @Then("User got error message")
    public void user_got_error_message() throws InterruptedException {
        String errorLogin = driver.findElement(By.xpath("//div[contains(text(), 'Kredensial yang Anda berikan salah')]")).getText();
        Assert.assertEquals("Kredensial yang Anda berikan salah", errorLogin);

        Thread.sleep(3000); // delay 5 detik
        driver.close();
    }
}
