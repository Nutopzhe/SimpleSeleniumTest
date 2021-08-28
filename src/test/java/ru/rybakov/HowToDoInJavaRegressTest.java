package ru.rybakov;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.*;

public class HowToDoInJavaRegressTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @ParameterizedTest(name = "#{index} - Run with section = {0}")
    @ValueSource(strings = {"Java Tutorial", "Java 15 Tutorial", "Java 9 Tutorial", "Generics Tutorial"})
    void simpleTest(String section) {
        driver.get("https://howtodoinjava.com/");
        //находим ссылку на странице, где текст = section и нажимаем на неё
        driver.findElement(By.linkText(section)).click();

        //проверяем что на странице присутствует левый блок тематического меню
        WebElement leftBlock = driver.findElement(By.xpath("//div[@class='ocs-trigger ocs-toggle ocs-toggle-abc']"));
        assertNotNull(leftBlock, "Отсутствует левый блок меню!");

        //проверяем что на странице верхний блок навигационного меню
        WebElement upBlock = driver.findElement(By.xpath("//div[@class='breadcrumb']"));
        assertNotNull(upBlock, "Отсутствует верхний блок навигационной панели");

        //проверяем что присутствует тумблер переключения темной/светлой темы
        WebElement switchButton = driver.findElementByXPath("//div[@class='wrap']//li[@id='menu-item-17363']");
        assertNotNull(switchButton, "Отсутствует тумблер переключения светлой/темной темы");

        //записываем значение атрибута class у тумблера темы в переменную firstPosition
        //и нажимаем на тумблер переключения темы
        String firstPosition = driver.findElement(By.xpath("//div[@class='wrap']//li[@id='menu-item-17363']/div")).getAttribute("class");
        switchButton.click();
        //записываем значение атрибута class у тумблера после клика
        //и сравниваем значения переменных на неравенство
        String secondPosition = driver.findElement(By.xpath("//div[@class='wrap']//li[@id='menu-item-17363']/div")).getAttribute("class");
        assertNotEquals(firstPosition, secondPosition);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
