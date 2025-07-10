import org.openqa.selenium.WebElement;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameBoard {
    private Cell[][] grid = new Cell[16][30];
    
    public void updateBoard(List<WebElement> e) {
        for (WebElement i: e) {
            String[] cord = i.getAttribute("id").split("_");
            int y = Integer.parseInt(cord[0]);
            int x = Integer.parseInt(cord[1]);
            x--;
            y--;
            String c = i.getAttribute("class");
            if (c.contains("bombflagged")) {
                c = "f";
            }
            else if (c.contains("blank")){
                c = "c";
            }
            else if (c.contains("open")) {
                c = c.substring(c.length()-1); 
            }
            Cell cell  = new Cell(x, y, c, i);
            grid[y][x] = cell; 
        }
    }

    public Move decideNextMove() {
        boolean isEmpty = true;
        for (Cell[] list: grid) {
            for (Cell i: list) { 
                if (!(i.state.equals("c"))) {
                    isEmpty = false;
                }}}

        if (isEmpty) {
            return new Move(grid[0][0], "left");
        }
        for (Cell[] list: grid) {
            for (Cell i: list) {
                try {
                    int num = Integer.parseInt(i.state);
                    if (num > 0) {
                        int[] nCount = checkAround(i);
                        int closed = nCount[0];
                        int flagged = nCount[1];

                        if (num == flagged && closed > 0) {
                            for (int r = -1 ;r <=1;r++) {
                                for (int j = -1; j<=1;j++) {
                                    if (r == 0 && j == 0) {
                                        continue;
                                    }

                                    if (i.x + r >=0 && i.x+r<30 && i.y+j>=0 && i.y+j<16) {
                                        if (grid[i.y+j][i.x+r].state.equals("c")) {
                                            return new Move(grid[i.y+j][i.x+r], "left");
                                        }
                                    }
                                }
                            }
                        }
                        else if (num == flagged + closed && closed > 0) {
                            for (int r = -1 ;r <=1;r++) {
                                for (int j = -1; j<=1;j++) {
                                    if (r == 0 && j == 0) {
                                        continue;
                                    }

                                    if (i.x + r >=0 && i.x+r<30 && i.y+j>=0 && i.y+j<16) {
                                        if (grid[i.y+j][i.x+r].state.equals("c")) {
                                            grid[i.y+j][i.x+r].state = "f";
                                        }
                                    }}}}}}
                catch (NumberFormatException e) {}}}
    for (int numToFind = 1; numToFind <= 8; numToFind++) {

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.state.equals(String.valueOf(numToFind))) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dx == 0 && dy == 0) continue;
                            int neighborX = cell.x + dx;
                            int neighborY = cell.y + dy;
                            if (neighborX >= 0 && neighborX < 30 && neighborY >= 0 && neighborY < 16) {
                                Cell neighbor = grid[neighborY][neighborX];
                                if (neighbor.state.equals("c")) {
                                    return new Move(neighbor, "left"); 
                        }}}}}}}}
        
        return null;
    }

    public void processClickResult(Move move, GameDriver driver) {
        String c = driver.getCellState(move.getTarget());
        
        if (c.equals("0")) { 
            Queue<Cell> cellQ = new LinkedList<>();
            boolean[][] visited = new boolean[16][30];
            cellQ.add(move.getTarget());
            visited[move.getTarget().y][move.getTarget().x] = true; 
            while(!cellQ.isEmpty()) {
                Cell currentCell = cellQ.poll();
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0) continue;

                        int neighborX = currentCell.x + dx;
                        int neighborY = currentCell.y + dy;

                        if (neighborX >= 0 && neighborX < 30 && neighborY >= 0 && neighborY < 16 && !(visited[neighborY][neighborX])) {
                            Cell neighbor = grid[neighborY][neighborX];
                            visited[neighborY][neighborX] = true;
                            neighbor.state = driver.getCellState(neighbor);
                            if (neighbor.state.equals("0")) {
                                cellQ.add(neighbor);
                            }}}}} 
        }
        
        grid[move.getTarget().y][move.getTarget().x].state = c;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int[] checkAround(Cell c) {
        int closed = 0; 
        int flagged = 0; 
        
        for (int i = -1 ;i <=1;i++) {
            for (int j = -1; j<=1;j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (c.x + i >=0 && c.x+i<30 && c.y+j>=0 && c.y+j<16) {
                    Cell neighbor = this.grid[c.y+j][c.x+i];
                

                    if (neighbor.state.equals("c")) {
                        closed++;
                    }
                    else if (neighbor.state.equals("f")) {
                        flagged++;
                    }}}}
        return new int[]{closed, flagged};
    }
}