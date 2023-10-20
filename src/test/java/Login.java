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

        // mencari dan mendefinisikan element
        WebElement element1 = driver.findElement(By.id(idEmail));
        WebElement element2 = driver.findElement(By.id(idPassword));
        WebElement element3 = driver.findElement(By.cssSelector(btn));

        // mendefinisikan xpath element
        // di inspect element, klik kanan element -> copy -> copy xpath
        WebElement elementXpath1 = driver.findElement(By.xpath("//*[@id='email']"));
        WebElement elementXpath2 = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement elementXpath3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/button"));

        elementXpath1.click();
        elementXpath1.sendKeys(email);
        elementXpath1.getText();

        elementXpath2.click();
        elementXpath2.sendKeys(password);

        elementXpath3.click();

//        driver.close(); // tutup window
    }

}
