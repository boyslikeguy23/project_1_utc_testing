import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminTest_Sel {
    private WebDriver webDriver;
    private String homePageURL = "https://project-1-utc-angular.onrender.com/";

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
    @Test
    public void insertCategory() throws Exception {
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("https://project-1-utc-angular.onrender.com/login"));
        WebElement usernameField = webDriver.findElement(By.id("email"));
        usernameField.sendKeys("kuangbuckk159@gmail.com");
        Thread.sleep(200);
        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("123456");
        Thread.sleep(200);
        WebElement continueButton = webDriver.findElement(By.id("login-button-2"));
        continueButton.click();
        Thread.sleep(200);
        WebElement manageButton = webDriver.findElement(By.xpath("//a[contains(text(),'Quản lý dữ liệu')]"));
        manageButton.click();
        Alert alertlogin = wait.until(ExpectedConditions.alertIsPresent());
        alertlogin.accept();
        Thread.sleep(200);
        WebElement organizationButton = webDriver.findElement(By.xpath("//a[contains(text(),'Ban tổ chức')]"));
        organizationButton.click();
        Thread.sleep(200);
        WebElement addOrganizationButton = webDriver.findElement(By.xpath("//a[contains(text(),'Thêm ban tổ chức')]"));
        addOrganizationButton.click();
        Thread.sleep(200);
        WebElement inputOrganization = webDriver.findElement(By.xpath("//input[@id='name']"));
        inputOrganization.sendKeys("1989s Entertainment");
        Thread.sleep(200);
        WebElement addButton = webDriver.findElement(By.xpath("//button[contains(text(),'Thêm')]"));
        addButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue(alertText.contains("Thêm organization thành công"), "Nội dung của alert không khớp");

        // Chấp nhận alert để đóng
        alert.accept();


    }
}
