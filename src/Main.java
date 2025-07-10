
import org.openqa.selenium.UnhandledAlertException;

public class Main {
    public static void main(String[] args) {
        GameDriver driver = new GameDriver();
        GameBoard board = new GameBoard();
        
        driver.initialize();
        board.updateBoard(driver.readBoardElements());

        while (true) {
            
            Move next = board.decideNextMove();
            
            if (next != null) {
                try {
                    driver.performClick(next.getTarget().element, next.getAction());
                    board.processClickResult(next, driver);

                } catch (UnhandledAlertException alertEx) {
                    System.out.println("이김");
                    for (Cell[] row : board.getGrid()) {
                        for (Cell i: row) {
                            System.out.print(i.state + " ");
                        }
                        System.out.println();
                    }
                    break;
                }
                String gameState = driver.getGameState();
                if (gameState.equals("lost")) {
                    driver.restartGame();
                    board.updateBoard(driver.readBoardElements());
                    continue;
                }}}}}