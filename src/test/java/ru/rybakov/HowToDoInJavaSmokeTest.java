package ru.rybakov;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class HowToDoInJavaSmokeTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    void simpleTest() {
        driver.get("https://howtodoinjava.com/");

        driver.findElement(By.linkText("Java Tutorial")).click();

        WebElement leftBlock = driver.findElement(By.xpath("//div[@class='ocs-trigger ocs-toggle ocs-toggle-abc']"));
        assertNotNull(leftBlock, "Отсутствует левый блок меню!");

        WebElement upBlock = driver.findElement(By.xpath("//div[@class='breadcrumb']"));
        assertNotNull(upBlock, "Отсутствует верхний блок навигационной панели");

        WebElement switchButton = driver.findElementByXPath("//div[@class='wrap']//li[@id='menu-item-17363']");
        assertNotNull(switchButton, "Отсутствует тумблер переключения светлой/темной темы");

        String firstPosition = driver.findElement(By.xpath("//div[@class='wrap']//li[@id='menu-item-17363']/div")).getAttribute("class");
        switchButton.click();
        String secondPosition = driver.findElement(By.xpath("//div[@class='wrap']//li[@id='menu-item-17363']/div")).getAttribute("class");
        assertNotEquals(firstPosition, secondPosition);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
