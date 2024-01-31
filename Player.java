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
        System.out.println("You can move to the following caves: " + caveSystem.getLayoutMap().get(caveNum));
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
}