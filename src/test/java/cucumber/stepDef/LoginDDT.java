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

public class LoginDDT {

    WebDriver driver;
    String baseUrl = "https://kasirdemo.belajarqa.com/";

    @Given("I am on login page")
    public void iAmOnLoginPage() {
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

    @When("I input (.*) as email$")
    public void iInputEmailAsEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("I input (.*) as password$")
    public void iInputPasswordAsPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("I verify (.*) login result with (.*) as username$")
    public void iVerifyStatusLoginResultWithUsernameAsUsername(String status, String username) throws InterruptedException {
        if (status.equals("success")) {
            driver.findElement(By.xpath("//div[contains(text(), 'dashboard')]"));
            String welcomeAssert = driver.findElement(By.xpath("//dd[contains(text(), 'hai')]")).getText();
            String usernameAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/dl/dt")).getText();
            Assert.assertEquals("hai", welcomeAssert);
            Assert.assertEquals(usernameAssert, username);
        } else {
            String errorLogin = driver.findElement(By.xpath("//div[contains(text(), 'Kredensial yang Anda berikan salah')]")).getText();
            Assert.assertEquals("Kredensial yang Anda berikan salah", errorLogin);
        }
        Thread.sleep(3000); // delay 5 detik
        driver.close();
    }

}
