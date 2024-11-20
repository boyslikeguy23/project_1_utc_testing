import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest_Sel {
    private WebDriver webDriver;
    private String homePageURL = "http://localhost:4200/";

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to(homePageURL);
    }

    @After
    public void tearDown() throws Exception {
        try {
            Thread.sleep(2000);
            webDriver.close();
            webDriver.quit();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e);
        }
    }
//register-link
    @Test
    public void registerNoUsernameAndPassword() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));
        Thread.sleep(3000);
        WebElement registerButton = webDriver.findElement(By.id("register-button"));//span[contains(text(), 'Đăng nhập | Đăng ký')]
        registerButton.click();
        Thread.sleep(300);
        WebElement txtEmail = webDriver.findElement(By.id("email"));
        txtEmail.click();
        Thread.sleep(300);
        WebElement registerButton_2 = webDriver.findElement(By.xpath("//button[contains(@id='register-button-2')]"));
        registerButton_2.click();
        Thread.sleep(300);
        WebElement txtEmailDanger = webDriver.findElement(By.id("email-text-danger"));
        //WebElement txtPasswordDanger = webDriver.findElement(By.id("password-text-danger"));
        Assert.assertNotNull(txtEmailDanger);
        //Assert.assertNotNull(txtPasswordDanger);
    }

    @Test
    public void registerNoPassword() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));
        Thread.sleep(3000);
        WebElement registerButton = webDriver.findElement(By.id("register-button"));//span[contains(text(), 'Đăng nhập | Đăng ký')]
        registerButton.click();
        Thread.sleep(300);
        WebElement txtEmail = webDriver.findElement(By.id("email"));
        txtEmail.sendKeys("test@test.com");
        Thread.sleep(300);
        WebElement registerButton_2 = webDriver.findElement(By.xpath("//button[contains(@id='register-button-2')]"));
        registerButton_2.click();
        WebElement txtEmailDanger = webDriver.findElement(By.id("email-text-danger"));
        //WebElement txtPasswordDanger = webDriver.findElement(By.id("password-text-danger"));
        Assert.assertNotNull(txtEmailDanger);
    }

    public void registerSuccessfully() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));
        Thread.sleep(3000);
        WebElement registerButton = webDriver.findElement(By.id("register-button"));
        registerButton.click();
        Thread.sleep(300);
        WebElement txtEmail = webDriver.findElement(By.id("email"));
        txtEmail.sendKeys("mxtung@gmail.com");
        Thread.sleep(300);
        WebElement txtPassword = webDriver.findElement(By.id("password"));
        txtPassword.sendKeys("123456");
        Thread.sleep(300);
        WebElement registerButton_2 = webDriver.findElement(By.id("register-button-2"));
        registerButton_2.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue(alertText.contains("đăng ký thành công"), "Nội dung của alert không khớp");
        // Chấp nhận alert để đóng
        alert.accept();
    }


}
