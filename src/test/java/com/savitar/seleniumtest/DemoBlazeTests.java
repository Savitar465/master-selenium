package com.savitar.seleniumtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class DemoBlazeTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Configuración del WebDriver
        System.setProperty("webdriver.chrome.driver", "F:\\Programacion\\selenium-test\\src\\main\\resources\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verificarPreciosDeProductos() {
        // Abrir la página Demo Blaze
        driver.get("https://demoblaze.com/");

        // Verificar precios de Phones
        driver.findElement(By.linkText("Phones")).click();

        WebElement samsungGalaxyS6 = driver.findElement(By.linkText("Samsung galaxy s6"));
        samsungGalaxyS6.click();
        WebElement samsungPrice = driver.findElement(By.tagName("h3"));
        assertEquals("$360 *includes tax", samsungPrice.getText());
        driver.navigate().back();

        WebElement nokiaLumia1520 = driver.findElement(By.linkText("Nokia lumia 1520"));
        nokiaLumia1520.click();
        WebElement nokiaPrice = driver.findElement(By.tagName("h3"));
        assertEquals("$820 *includes tax", nokiaPrice.getText());
        driver.navigate().back();

        // Verificar precios de Laptops
        driver.findElement(By.linkText("Laptops")).click();

        WebElement sonyVaioI5 = driver.findElement(By.linkText("Sony vaio i5"));
        sonyVaioI5.click();
        WebElement sonyPrice = driver.findElement(By.tagName("h3"));
        assertEquals("$790 *includes tax", sonyPrice.getText());
        driver.navigate().back();

        WebElement macBookAir = driver.findElement(By.linkText("MacBook air"));
        macBookAir.click();
        WebElement macPrice = driver.findElement(By.tagName("h3"));
        assertEquals("$700 *includes tax", macPrice.getText());
        driver.navigate().back();

        // Verificar precio de Monitors
        driver.findElement(By.linkText("Monitors")).click();

        WebElement asusFullHD = driver.findElement(By.linkText("ASUS Full HD"));
        asusFullHD.click();
        WebElement asusPrice = driver.findElement(By.tagName("h3"));
        assertEquals("$230 *includes tax", asusPrice.getText());
    }

    @After
    public void tearDown() {
        // Cerrar el navegador
        driver.quit();
    }

}
