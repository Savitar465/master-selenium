package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

// page_url = https://demoblaze.com/
public class DemoblazePage {

    private static final Logger log = LoggerFactory.getLogger(DemoblazePage.class);
    private final WebDriver driver;

    @FindBy(xpath = "//a[@onclick=\"byCat('phone')\"]")
    private WebElement phonesCategory;

    @FindBy(xpath = "//a[@onclick=\"byCat('notebook')\"]")
    private WebElement laptopsCategory;

    @FindBy(xpath = "//a[@onclick=\"byCat('monitor')\"]")
    private WebElement monitorsCategory;

    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div")
    private List<WebElement> productTitles;

    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div[1]/div/div/h4/a")
    public WebElement content;



    public DemoblazePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToCategory(String category) {
        switch (category.toLowerCase()) {
            case "phones":
                phonesCategory.click();
                waitForContent();
                break;
            case "laptops":
                laptopsCategory.click();
                waitForContent();
                break;
            case "monitors":
                monitorsCategory.click();
                waitForContent();
                break;
            default:
                log.error("Invalid category: {}", category);
        }
    }

    public WebElement findByProductName(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        int attempts = 0;
        while (attempts < 3) {
            try {
                return productTitles.stream()
                        .map(product -> {
                            try {
                                WebElement w = product.findElement(By.xpath(".//h4/a[contains(text(),'" + productName + "')]"));
                                if (w != null) {
                                    log.info("Product found: {}", w.getText());
                                }
                                return w;
                            } catch (NoSuchElementException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
            } catch (StaleElementReferenceException e) {
                attempts++;
                log.warn("Attempt {} - StaleElementReferenceException caught, retrying...", attempts);
            }
        }
        throw new RuntimeException("Product not found after multiple attempts: " + productName);
    }

    public void openDemoblazePage() {
        try{
            driver.get("https://demoblaze.com/");
            waitForContent();

        }catch (Exception e){
            log.error("Error al abrir la pagina de demoblaze");
        }
    }

    public void waitForContent(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(content));
    }


    public WebElement getProductPrice(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        int attempts = 0;
        while (attempts < 3) {
            try {
                return productTitles.stream()
                        .map(product -> {
                            try {
                                WebElement w = product.findElement(By.xpath(".//h4/a[contains(text(),'" + productName + "')]"));
                                if (w != null) {
                                    log.info("Product found: {}", w.getText());
                                }
                                return w;
                            } catch (NoSuchElementException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
            } catch (StaleElementReferenceException e) {
                attempts++;
                log.warn("Attempt {} - StaleElementReferenceException caught, retrying...", attempts);
            }
        }
        throw new RuntimeException("Product not found after multiple attempts: " + productName);
    }
}