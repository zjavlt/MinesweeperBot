import org.openqa.selenium.WebElement;

public class Cell {
    int x, y;
    String state;
    WebElement element;
    
    public Cell(int a, int b, String c, WebElement d) {
        x = a;
        y = b;
        state = c;
        element = d;
    } 

}