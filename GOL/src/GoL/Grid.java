package GoL;

import java.io.*;
import java.util.*;

public class Grid {

    private File file;
    private int rows;
    private int columns;

    public Grid(File file) {
        this.file = file;
    }

    //no need to call this in main
    public ArrayList<String> readFile() throws IOException {
        FileReader fr = new FileReader(this.file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> linesRead = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            linesRead.add(line);
        }
        br.close();
        fr.close();
        return linesRead;
    }

    //no need to call this in main
    public boolean[][] gridInit(String gridsize) {
        String[] gridSplitArray = gridsize.split(" ");
        this.columns = Integer.parseInt(gridSplitArray[1]);
        this.rows = Integer.parseInt(gridSplitArray[2]);
        boolean[][] grid2D = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < grid2D[i].length; j++){
                grid2D[i][j] = false;
            }
        }
        return grid2D;
    }

    //call this first in main
    public boolean[][] setup() throws IOException {
        ArrayList<String> linesRead = readFile();
        String gridSize = linesRead.get(0);
        boolean[][] falseGrid2D = gridInit(gridSize);
        String startPos = linesRead.get(1);
        String[] startPosArray = startPos.split(" ");
        int startX = Integer.parseInt(startPosArray[2]);
        int startY = Integer.parseInt(startPosArray[1]);

        for (int i = 3; i < linesRead.size(); i++) {
            char[] dataRead = linesRead.get(i).toCharArray();
            int yNext = startY;
            for (char c : dataRead) {
                if (c == '*'){
                    falseGrid2D[startX][yNext] = true;
                }
                yNext++;
            }            
            startX++;
        }
        boolean [][] startingGrid = falseGrid2D;
        return startingGrid;
    }

    //No need to call this in main
    public int evaluateNeighbours(boolean[][] parentGenGrid, int x, int y) {
        int count = 0;
        //System.out.println("in eval neighbours");
        for (int i = x-1; i <= x+1; i++){
            if (i < 0 || i >= this.columns) {
                continue;
            }
            for (int j = y-1; j <= y+1; j++) {
                if (j < 0 || j >= this.rows) {
                    continue;
                } else if (i == x && j == y) {
                    continue;
                } else if (parentGenGrid[j][i] == true) {
                    count++;
                }
            
            }
        }
        return count;
    }

    //call this second in main
    public boolean[][] runSimulation(boolean[][] parentGenGrid) {
        boolean[][] childGenGrid = new boolean[this.rows][this.columns];
        for (int x = 0; x <= this.columns -1; x++) {
            for (int y = 0; y <= this.rows -1; y++) {
                int count = evaluateNeighbours(parentGenGrid, x, y);
                //System.out.println("count " + count + "position " + x +y);
                if (count == 2) {
                    continue;
                } else if (count == 3) {
                    childGenGrid[y][x] = true;
                } else {
                    childGenGrid[y][x] = false;
                }
            }
        }
        // printGrid(childGenGrid);
        return childGenGrid;
    }

    public void printGrid(boolean[][] gridToPrint) {
        for (boolean[] row : gridToPrint) {    
            for (boolean b : row) {    
                if (b == false) {
                    System.out.print("[ ]");
                } else if (b == true) {
                    System.out.print("[*]");
                }
            }
            System.out.println();            
        }
        System.out.println();
    }
    
}
