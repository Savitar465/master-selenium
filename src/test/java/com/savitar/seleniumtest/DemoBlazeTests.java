package com.savitar.seleniumtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DemoblazePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoBlazeTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Configuración del WebDriver
        System.setProperty("webdriver.chrome.driver",
                "F:\\Programacion\\selenium-test\\src\\main\\resources\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProductPresence() {
        DemoblazePage demoblazePage = new DemoblazePage(driver);
        demoblazePage.openDemoblazePage();
        String[] phones = {"Samsung galaxy s6", "Nokia lumia 1520"};
        String[] laptops = {"Sony vaio i5", "MacBook air"};
        String[] monitors = {"ASUS Full HD"};

        demoblazePage.navigateToCategory("phones");
        for (String phone : phones) {
            WebElement product = demoblazePage.findByProductName(phone);
            assertEquals(phone, product.getText());

        }

        demoblazePage.navigateToCategory("laptops");
        for (String laptop : laptops) {
            WebElement product = demoblazePage.findByProductName(laptop);
            assertEquals(product.getText(), laptop);
        }

        demoblazePage.navigateToCategory("monitors");
        for (String monitor : monitors) {
            WebElement product = demoblazePage.findByProductName(monitor);
            assertEquals(product.getText(), monitor);
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
