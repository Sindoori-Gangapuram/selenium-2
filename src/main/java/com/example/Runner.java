package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Runner {

    public static String driverPath = "src/main/resources/";
    public static WebDriver driver;

    public static void main(String[] args) {
        System.out.println("launching chrome browser");
        System.out.println(System.getProperty("os.name").toLowerCase());
        String path = System.getProperty("os.name").toLowerCase().contains("mac")?"chromedriver":"chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath + path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://github.com/login");
        driver.findElement(By.id("login_field")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.name("commit")).click();
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver,10);
            webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div"))));
            System.out.println("Login failed");
        } catch (Exception e) {
            System.out.println("Login success");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
