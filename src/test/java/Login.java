import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    @Test // anotation untuk mendefinisikan dibawahnya adalah script test
    public void openBrowser() {
        WebDriver driver; // set driver untuk testing menggunakan webdriver dari selenium
        String baseUrl = "https://kasirdemo.belajarqa.com/"; // set baseUrl nya ke dalam variable

        WebDriverManager.chromedriver().setup(); // setup chrome driver dengan otomatis menggunakan web driver manager

        driver = new ChromeDriver(); // set variable driver menjadi object dari chrome driver
        driver.manage().window().maximize(); // maximize window
        driver.get(baseUrl); // akses baseUrl
        String title = driver.getTitle(); // title website akan di set ke variable title
        System.out.println(title);

        driver.close(); //   tutup window
    }
}
