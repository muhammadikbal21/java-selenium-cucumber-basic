package kasirAja;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {

    WebDriver driver;
    String baseUrl = "https://kasirdemo.belajarqa.com/";

    @Test
    public void login_ddt() {

        // setup awal menggunakan chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        // mendefinisikan lokasi file test-data.csv
        // user.dir disini yaitu D:\Rakamin Modul\W6\My Project\Selenium\FirstSelenium
        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv";

        // membaca file test-data
        try (CSVReader reader = new CSVReader(new FileReader(csvDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String email = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];
                String username = nextLine[3];

                driver = new ChromeDriver(opt);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.get(baseUrl);

                // input form
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                // assertion
                if (status.equals("success")) {
                    driver.findElement(By.xpath("//div[contains(text(), 'dashboard')]"));
                    String welcomeAssert = driver.findElement(By.xpath("//dd[contains(text(), 'hai')]")).getText();
                    String usernameAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/dl/dt")).getText();
                    Assert.assertEquals("hai", welcomeAssert);
                    Assert.assertEquals(username, usernameAssert);
                } else {
                    String errorLogin = driver.findElement(By.xpath("//div[contains(text(), 'Kredensial yang Anda berikan salah')]")).getText();
                    Assert.assertEquals("Kredensial yang Anda berikan salah", errorLogin);
                }
                Thread.sleep(5000); // delay 5 detik
                driver.close();
            }
        } catch (IOException e) { // nge-catch error secara general
            throw new RuntimeException(e);
        } catch (CsvValidationException e) { //nge-catch error untuk csv
            e.printStackTrace();
        } catch (InterruptedException e) { // nge-catch error untuk delay
            throw new RuntimeException(e);
        }
    }
}
