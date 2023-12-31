import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login {

    WebDriver driver; // set driver untuk testing menggunakan webdriver dari selenium
    String baseUrl = "https://kasirdemo.belajarqa.com/"; // set baseUrl nya ke dalam variable

    @Test // anotation untuk mendefinisikan dibawahnya adalah script test
    public void loginValid() {

        WebDriverManager.chromedriver().setup(); // setup chrome driver dengan otomatis menggunakan web driver manager
        driver = new ChromeDriver(); // set variable driver agar automation dijalankan di browser chrome
        driver.manage().window().maximize(); // maximize window
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // berhubungan dengan assertion, ketika time out disini tidak didefinisikan maka assertion dibawah tidak akan berfungsi
        driver.get(baseUrl); // akses baseUrl

        // assertion -> memverifikasi apakah perintah tersebut benar benar expected
        String titleLandingPageAssert = driver.findElement(By.xpath("//h2[contains(text(), 'hai, kasirAja')]")).getText();
        Assert.assertEquals("hai, kasirAja", titleLandingPageAssert);

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
        // bisa juga dengan rumus -> //namatag[contains(text(), 'Nama Text')]
        // contoh -> //a[contains(text(), 'Ikbal Shop')]
        WebElement elementXpath1 = driver.findElement(By.xpath("//*[@id='email']"));
        WebElement elementXpath2 = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement elementXpath3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/button"));

        elementXpath1.click();
        elementXpath1.sendKeys(email);
        elementXpath1.getText();

        elementXpath2.click();
        elementXpath2.sendKeys(password);

        elementXpath3.click();

        // disini kita bisa melakukan assertion karena kita sudah mendefinisikan time out implicitlyWait diatas
        String successLoginAssert = driver.findElement(By.xpath("//h3[contains(text(), 'kasirAja')]")).getText();
        Assert.assertEquals("kasirAja", successLoginAssert);

//        driver.close(); // tutup window
    }

}
