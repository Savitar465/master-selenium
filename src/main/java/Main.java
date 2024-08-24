import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {
        // Set up the WebDriver
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoblaze.com/");

        // Verify product prices
        verifyProductPrice(driver, "Phones", "Samsung galaxy s6");
        verifyProductPrice(driver, "Phones", "Nokia lumia 1520");
        verifyProductPrice(driver, "Laptops", "Sony vaio i5");
        verifyProductPrice(driver, "Laptops", "MacBook air");
        verifyProductPrice(driver, "Monitors", "ASUS Full HD");

        // Close the browser
        driver.quit();
    }

    private static void verifyProductPrice(WebDriver driver, String category, String productName) {
        // Navigate to the category
        driver.findElement(By.linkText(category)).click();

        // Locate the product card
        WebElement productCard = driver.findElement(By.xpath("//a[text()='" + productName + "']/ancestor::div[@class='card h-100']"));

        // Extract and print the product price
        String price = productCard.findElement(By.className("price")).getText();
        System.out.println(productName + " price: " + price);
    }
}
