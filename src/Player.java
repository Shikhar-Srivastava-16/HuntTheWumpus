 import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
    
    /**attributes
     *caveNum is the current location, gets updated each round
     *qtyArrows is the number of Arrows the player has
     */
    private int caveNum;
    private int qtyArrows;

    /** 
     * @return int
     */
    //getters and setters
    public int getCaveNum() {
        return caveNum;
    }

    /** 
     * @param caveNum
     */
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
        System.out.printf("Your quiver has grown lighter! %d Arrows remain.\n", qtyArrows);
        
        System.out.printf("You can shoot into the caves: ");
        for (int adjCave : caveSystem.getLayoutMap().get(caveNum)) {
            System.out.printf("%d ", adjCave);
        }

        System.out.println(doShootCave(inputReader.nextInt(), caveSystem));
    }
    
    public String doShootCave(int caveShotAt, CaveSystem caveSystem) {
        
        String returnStr = "";
        if (caveSystem.wumpDeath(caveShotAt)) {
            returnStr += ("You have slain the mighty Wumpus!");
            if (caveSystem.getWumpLocation().size() == 0) {
                returnStr += ("No more wumpi remain in the Cave System. You have conquered the caves! You win.");
                Graphics.popup("<html>" + returnStr + "</html>");
            } else {
                returnStr += (caveSystem.getWumpLocation().size() + " wumpi remain in the Cave System.\n");
            }
            
        } else {
            returnStr += ("You missed! You have wasted an arrow.");
            for (int cave : caveSystem.getNearbyCaves(caveShotAt)) {
                if (caveSystem.getWumpLocation().contains(cave)) {
                    returnStr += ("Worse news - your arrow spooked the wumpus and it fled to another cave.");
                    caveSystem.moveWump(cave);
                } 
            }
        }
        return returnStr;
    }
}