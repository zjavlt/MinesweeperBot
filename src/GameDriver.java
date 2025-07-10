import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.time.Duration;


public class GameDriver{
    private WebDriver driver;
    private ChromeOptions options;

    public void initialize() {
        options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        driver.get("https://minesweeperonline.com/"); 


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='square blank']")));
        
    }

    public void restartGame() {
        try {
            WebElement newGameButton = driver.findElement(By.id("face"));
            newGameButton.click();

        } catch (Exception e) {
        }    
    }

    public List<WebElement> readBoardElements() {
        
        return driver.findElements(By.xpath("//div[contains(@class, 'square') and not(contains(@style, 'display: none'))]"));
        
    }

    public void performClick(WebElement element, String action) {
        if (action.equals("left")) {
            element.click();
        }
        else if (action.equals("right")) {
            Actions a = new Actions(driver);
            a.moveToElement(element);
            a.contextClick();
            a.perform();
        }
    }

    public String getGameState() {
        WebElement face = driver.findElement(By.id("face"));

        if(face.getAttribute("class").contains("dead")) {
            return "lost";
        }
        return "ongoing";
    }

    public String getCellState(Cell cell) {
        WebElement e = driver.findElement(By.cssSelector("div[id='" + (cell.y+1) + "_" + (cell.x+1) + "']"));
        String c = e.getAttribute("class");
        if (c.contains("bombflagged")) {
                c = "f";
            }
            else if (c.contains("blank")){
                c = "c";
            }
            else if (c.contains("open")) {
                c = c.substring(c.length()-1); 
            }

        return c;
    }

    public void close() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}