import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTest_Sel {
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

    @Test
    public void searchNothing() throws Exception {
        //By searchBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("search-button"));
        WebElement btnSearch = webDriver.findElement(By.id("search-button"));
        btnSearch.click();
        Thread.sleep(2000);
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.urlToBe(homePageURL));

        String urlCurrent = webDriver.getCurrentUrl();
        Assert.assertEquals(homePageURL, urlCurrent);
    }

    @Test
    public void searchNonExistingEvent() throws Exception {
        WebElement txtSearch = webDriver.findElement(By.id("input-search-textbox"));
        txtSearch.sendKeys("XYZ123");
        Thread.sleep(500);
        WebElement btnSearch = webDriver.findElement(By.id("search-button"));
        btnSearch.click();
        Thread.sleep(2000);

        WebElement noEventMessage = webDriver.findElement(By.xpath("//*[contains(text(),'Không có sự kiện nào được tìm thấy')]"));
        Assert.assertNotNull(noEventMessage); // Kiểm tra thông báo được hiển thị

    }
}
