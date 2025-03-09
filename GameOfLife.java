import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLife implements Board {
    // Integers: 0 or 1 for alive or dead
    private ArrayList<ArrayList<Integer>> grid;

    public GameOfLife(int rows, int cols) {
        // Construct a 2D ArrayList of the given dimensions
        grid = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(0);
            }
            grid.add(row);
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

        // Step the simulation forward one turn.
        public void step() {
            ArrayList<ArrayList<Integer>> nextState = new ArrayList<>();
            for (int i = 0; i < grid.size(); i++) {
                ArrayList<Integer> newRow = new ArrayList<>();
                for (int j = 0; j < grid.get(0).size(); j++) {
                    int liveNeighbors = countNeighbors(i, j);
                    if (grid.get(i).get(j) == 1) {
                        newRow.add((liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0);
                    } else {
                        newRow.add((liveNeighbors == 3) ? 1 : 0);
                    }
                }
                nextState.add(newRow);
            }
            grid = nextState;
            print();
        }
    
        public int countNeighbors(int row, int col) {
            int count = 0;
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr != 0 || dc != 0) {
                        count += get(row + dr, col + dc);
                    }
                }
            }
            return count;
        }
    
        // Set values on the board
        public void set(int x, int y, int[][] pattern) {
            for (int i = 0; i < pattern.length; i++) {
                for (int j = 0; j < pattern[0].length; j++) {
                    grid.get(i + x).set(j + y, pattern[i][j]);
                }
            }
        }
    
    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x+xLimit)%xLimit][(y+yLimit)%yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
