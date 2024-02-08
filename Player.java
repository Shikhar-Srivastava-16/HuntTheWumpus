import java.util.*;

public class Player {
    
    /**attributes
     *caveNum is the current location, gets updated each round
     *qtyArrows is the number of Arrows the player has
     */
    private int caveNum;
    private int qtyArrows;

    //getters and setters
    public int getCaveNum() {
        return caveNum;
    }

    public void setCaveNum(int caveNum) {
        if (caveNum <= 20 || caveNum >= 1) {
            this.caveNum = caveNum;
        }
    }

    public int getQtyArrows() {
        return qtyArrows;
    }
    
    public void setQtyArrows(int qtyArrows) {
        this.qtyArrows = qtyArrows;
    }

    //constructor
    public Player(int caveNum, int qtyArrows) {

        this.caveNum = caveNum;
        this.qtyArrows = qtyArrows;

    }

    //player movement
    public void movePlayer(CaveSystem caveSystem, Scanner scan1) {
        
        System.out.printf("You can move to the following caves: ");
        for (int i : caveSystem.getLayoutMap().get(caveNum)) {
            System.out.printf("%d ", i);
        }
        System.out.printf("\n");

        System.out.printf("Which Cave would you like to move to?: ");
        int varIntendedCave = scan1.nextInt();
        
        if (arrContains(caveSystem.getLayoutMap().get(caveNum), varIntendedCave)) {
            caveNum = varIntendedCave;
        } else {
            System.out.println("Not Possible!");
        }
    }

    public static boolean arrContains(int[] arr1, int numCheck) {
        //returns true if the second argument is an element of the integer array passed as first array
        for (int s : arr1) {
            if (s == numCheck) {
               return true;
            }
        }
        return false;
    }

    public void shootWump(CaveSystem caveSystem, Scanner inputReader) {

        qtyArrows--;

        System.out.printf("You can shoot into the caves: ");
        for (int adjCave : caveSystem.getLayoutMap().get(caveNum)) {
            System.out.printf("%d ", adjCave);
        }

        int caveShotAt = inputReader.nextInt();
        if (caveSystem.wumpDeath(caveShotAt)) {
            System.out.println("wump ded");
            if (caveSystem.getWumpLocation().size() == 0) {
                System.out.println("gaem end.");
            } else {
                System.out.printf("$d wump rem", caveSystem.getWumpLocation().size());
            }
            
        } else {
            System.out.println("wump no poke");
            for (int cave : caveSystem.getNearbyCaves(caveShotAt)) {
                if (caveSystem.getWumpLocation().contains(cave)) {
                    System.out.println("wump move");
                    caveSystem.moveWump(cave);
                    break;
                } 
            }
        }

        System.out.printf("%d Arrows rem", qtyArrows);

    }
}