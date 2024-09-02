package pages;

import model.Producto;
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

public class MarketPage {

    private static final Logger log = LoggerFactory.getLogger(MarketPage.class);
    private final WebDriver driver;

    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div")
    private List<WebElement> producto;

    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div[1]/div/div/h4/a")
    public WebElement paginaInicial;

    @FindBy(xpath = "//a[@onclick=\"byCat('phone')\"]")
    private WebElement categoriaPhones;

    @FindBy(xpath = "//a[@onclick=\"byCat('notebook')\"]")
    private WebElement categorialaptops;

    @FindBy(xpath = "//a[@onclick=\"byCat('monitor')\"]")
    private WebElement categoriaMonitors;


    public MarketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void irACategoria(String categoria) {
        WebElement categoryElement = switch (categoria.toLowerCase()) {
            case "phones" -> categoriaPhones;
            case "laptops" -> categorialaptops;
            case "monitors" -> categoriaMonitors;
            default -> {
                log.error("Invalid category: {}", categoria);
                yield null;
            }
        };
        if (categoryElement != null) {
            categoryElement.click();
            waitForContent();
        }
    }


    public Producto buscarProducto(String productName) {
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                return producto.stream()
                        .map(product -> {
                            try {
                                WebElement nameElement = product.findElement(By.xpath(".//h4/a[contains(text(),'" + productName + "')]"));
                                WebElement priceElement = product.findElement(By.xpath(".//h5"));
                                if (nameElement != null && priceElement != null) {
                                    log.info("Product found: {} - Price: {}", nameElement.getText(), priceElement.getText());
                                    return new Producto(nameElement.getText(), priceElement.getText());
                                }
                                return null;
                            } catch (NoSuchElementException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
            } catch (StaleElementReferenceException e) {
                log.warn("Attempt {} - StaleElementReferenceException caught, retrying...", attempts + 1);
            }
        }
        throw new RuntimeException("Product not found after multiple attempts: " + productName);
    }

    public void openDemoblazePage() {
        try {
            driver.get("https://demoblaze.com/");
            waitForContent();
        } catch (Exception e) {
            log.error("Error opening Demoblaze page", e);
        }
    }

    public void waitForContent() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(paginaInicial));
    }
}