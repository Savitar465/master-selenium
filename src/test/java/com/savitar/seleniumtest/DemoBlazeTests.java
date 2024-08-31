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
    private final String[] phones = {"Samsung galaxy s6", "Nokia lumia 1520"};
    private final String[] laptops = {"Sony vaio i5", "MacBook air"};
    private final String[] monitors = {"ASUS Full HD"};

    @Before
    public void setUp() {
        // Utilizar la ruta correcta del webdriver de chrome
        System.setProperty("webdriver.chrome.driver",
                "F:\\Programacion\\selenium-test\\src\\main\\resources\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProductPresence() {
        DemoblazePage demoblazePage = new DemoblazePage(driver);
        demoblazePage.openDemoblazePage();

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

    @Test
    public void testProductPrice() {
        DemoblazePage demoblazePage = new DemoblazePage(driver);
        demoblazePage.openDemoblazePage();

        demoblazePage.navigateToCategory("phones");
        for (String phone : phones) {
            String price = demoblazePage.getProductPrice(phone);
            System.out.println(phone + " price: " + price);
        }

        demoblazePage.navigateToCategory("laptops");
        for (String laptop : laptops) {
            String price = demoblazePage.getProductPrice(laptop);
            System.out.println(laptop + " price: " + price);
        }

        demoblazePage.navigateToCategory("monitors");
        for (String monitor : monitors) {
            String price = demoblazePage.getProductPrice(monitor);
            System.out.println(monitor + " price: " + price);
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
