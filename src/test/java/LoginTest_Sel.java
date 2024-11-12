import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest_Sel {
    private WebDriver webDriver;
    private String homePageURL = "http://localhost:4200/";

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to(homePageURL);
    }

    @Test
    public void loginNoUsernameAndPassword() throws Exception {
       WebElement loginButton = webDriver.findElement(By.id("login-button"));
       loginButton.click();
       WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
       wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));

    }

    @Test
    public void loginNoUsername() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));
        WebElement usernameField = webDriver.findElement(By.id("email"));
        usernameField.sendKeys("kuangbuckk159@gmail.com");
        Thread.sleep(20);
        WebElement continueButton = webDriver.findElement(By.id("login-button-2"));
        continueButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Kiểm tra alert không null, nghĩa là alert đã xuất hiện
        assertNotNull(alert, "Alert không xuất hiện khi đăng nhập mà không nhập thông tin");

        // Đóng alert sau khi kiểm tra
        alert.accept();
    }

    @Test
    public void loginNoPassword() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));
//        WebElement usernameField = webDriver.findElement(By.id("email"));
//        usernameField.sendKeys("kuangbuckkk1509@gmail.com");
//        Thread.sleep(20);
        WebElement passwordField = webDriver.findElement(By.id("email"));
        passwordField.sendKeys("123456");
        WebElement continueButton = webDriver.findElement(By.id("login-button-2"));
        continueButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Kiểm tra alert không null, nghĩa là alert đã xuất hiện
        assertNotNull(alert, "Alert không xuất hiện khi đăng nhập mà không nhập thông tin");

        // Đóng alert sau khi kiểm tra
        alert.accept();
    }

    @Test
    public void loginSuccessfully() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));
        WebElement usernameField = webDriver.findElement(By.id("email"));
        usernameField.sendKeys("kuangbuckk159@gmail.com");
        Thread.sleep(200);
        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("123456");
        Thread.sleep(200);
//        WebElement accessRightButton = webDriver.findElement(By.id("access-right"));
//        accessRightButton.click();
        Select selectRole = new Select(webDriver.findElement(By.name("access-right"))); // Cập nhật locator cho phù hợp

        // Chọn option với giá trị "ADMIN"
        selectRole.selectByVisibleText("ADMIN");
        Thread.sleep(2000);
        WebElement continueButton = webDriver.findElement(By.id("login-button-2"));
        continueButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Kiểm tra nội dung của alert
        String alertText = alert.getText();
        assertTrue(alertText.contains("đăng nhập thành công"), "Nội dung của alert không khớp");

        // Chấp nhận alert để đóng
        alert.accept();
    }

}
