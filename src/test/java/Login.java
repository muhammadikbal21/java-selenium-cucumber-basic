import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    WebDriver driver; // set driver untuk testing menggunakan webdriver dari selenium
    String baseUrl = "https://kasirdemo.belajarqa.com/"; // set baseUrl nya ke dalam variable

    @Test // anotation untuk mendefinisikan dibawahnya adalah script test
    public void loginValid() {
        WebDriverManager.chromedriver().setup(); // setup chrome driver dengan otomatis menggunakan web driver manager

        driver = new ChromeDriver(); // set variable driver agar automation dijalankan di browser chrome
        driver.manage().window().maximize(); // maximize window
        driver.get(baseUrl); // akses baseUrl

        String email = "ikbalshop@shop.com";
        String password = "123456";
        String idEmail = "email";
        String idPassword = "password";
        String btn = ".css-1n8i4of";

        WebElement element1 = driver.findElement(By.id(idEmail));
        WebElement element2 = driver.findElement(By.id(idPassword));
        WebElement element3 = driver.findElement(By.cssSelector(btn));

        element1.click();
        element1.sendKeys(email);
        element1.getText();

        element2.click();
        element2.sendKeys(password);

        element3.click();

//        driver.close(); // tutup window
    }

}
