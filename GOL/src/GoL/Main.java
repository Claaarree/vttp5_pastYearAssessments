package GoL;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -cp classes GoL/Main <filename>");
            return;
        }
        File file = new File("gol" + File.separator + args[0]);
        Grid grid = new Grid(file);
        boolean[][] startingGrid;
        try {
            startingGrid = grid.setup();
            grid.printGrid(startingGrid);
            //up till here is fine
            boolean[][] newStartGrid = grid.runSimulation(startingGrid);
            grid.printGrid(newStartGrid);
            for (int i = 1; i <= 4; i++) {
                boolean[][] endGrid = grid.runSimulation(newStartGrid);
                newStartGrid = endGrid;
                grid.printGrid(endGrid);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    
    }
    

   
}
