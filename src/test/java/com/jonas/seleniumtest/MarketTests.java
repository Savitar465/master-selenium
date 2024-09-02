package com.jonas.seleniumtest;

import model.Producto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MarketPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarketTests {

    private WebDriver driver;

    private final Map<String, Map<String, String>> productsWithPrices = new HashMap<>() {{
        put("phones", new HashMap<>() {{
            put("Samsung galaxy s6", "$360");
            put("Nokia lumia 1520", "$820");
        }});
        put("laptops", new HashMap<>() {{
            put("Sony vaio i5", "$790");
            put("MacBook air", "$700");
        }});
        put("monitors", new HashMap<>() {{
            put("ASUS Full HD", "$230");
        }});
    }};

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "F:\\Programacion\\selenium-test\\src\\main\\resources\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProductPrice() {
        MarketPage marketPage = new MarketPage(driver);
        marketPage.openDemoblazePage();

        for (Map.Entry<String, Map<String, String>> categoryEntry : productsWithPrices.entrySet()) {
            String category = categoryEntry.getKey();
            marketPage.irACategoria(category);

            for (Map.Entry<String, String> productEntry : categoryEntry.getValue().entrySet()) {
                String productName = productEntry.getKey();
                String expectedPrice = productEntry.getValue();
                Producto product = marketPage.buscarProducto(productName);
                assertEquals(expectedPrice, product.getPrice(), "Price mismatch for product: " + productName);
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
