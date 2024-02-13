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
     * setter validates the value being set so that only values between 1 and 20 can be set 
     */
    public void setCaveNum(int caveNum) {
        if (caveNum <= 20 || caveNum >= 1) {
            this.caveNum = caveNum;
        } else {
            System.out.println("invalid caveNum");
            System.exit(1);
        }
    }

    /**
     * @return int
    */
    public int getQtyArrows() {
        return qtyArrows;
    }
    
    /**
     * @param qtyArrows
     */
    public void setQtyArrows(int qtyArrows) {
        this.qtyArrows = qtyArrows;
    }

    /**
     * @param caveNum
     * @param qtyArrows
     * Class Contructor
     */
    public Player(int caveNum, int qtyArrows) {

        this.caveNum = caveNum;
        this.qtyArrows = qtyArrows;
    }

    /**
     * @param caveSystem
     * @param scan1
     * 
     * takes scanner as parameter and uses to read user input for movement
     */
    public void movePlayer(CaveSystem caveSystem, Scanner scan1) {
        
        //get caves wher it is possible to move
        System.out.printf("You can move to the following caves: ");
        for (int i : caveSystem.getLayoutMap().get(caveNum)) {
            System.out.printf("%d ", i);
        }
        System.out.printf("\n");

        //get user input
        System.out.printf("Which Cave would you like to move to?: ");
        int varIntendedCave = scan1.nextInt();
        
        //ensure the value entered by user is possible to move into
        if (arrContains(caveSystem.getLayoutMap().get(caveNum), varIntendedCave)) {
            caveNum = varIntendedCave;
        } else {
            System.out.println("Not Possible!");
        }
    }
    
    /**
     * @param int[]
     * @param numCheck
     * 
     * checks if numCheck is present in integerArray. This is used inseveral places within the Player class
     * private because it is not meant for use outside this class.
     */
    private static boolean arrContains(int[] arr1, int numCheck) {
        //returns true if the second argument is an element of the integer array passed as first array
        for (int s : arr1) {
            if (s == numCheck) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param caveSystem
     * @param inputReader
     * 
     * performs all functions required when shooting Wumpus in an instance of the game running nightmare mode
     */
    public void shootWump(CaveSystem caveSystem, Scanner inputReader) {
        
        qtyArrows--;
        
        //get all caves into which player can shoot
        System.out.printf("You can shoot into the caves: ");
        for (int adjCave : caveSystem.getLayoutMap().get(caveNum)) {
            System.out.printf("%d ", adjCave);
        }
        System.out.println(doShootCave(inputReader.nextInt(), caveSystem));

        System.out.printf("Your quiver has grown lighter! %d Arrows remain.\n", qtyArrows);
    }
    
    /**
     * @param caveShotAt
     * @param caveSystem
     * 
     * @return String
     * returned string contains all information to be printed after shooting into a cave. Acts differently based on difficulty. 
     * checks for number of wumpi remaining
     * implements the win condition (no wumpus remain)
     */
    public String doShootCave(int caveShotAt, CaveSystem caveSystem) {
        
        String returnStr = "";
        
        //checking if wumpus is in cave shat at
        if (caveSystem.wumpDeath(caveShotAt)) {
            returnStr += ("You have slain the mighty Wumpus! ");
            
            //check if win condition reached
            if (caveSystem.getWumpLocation().size() == 0) {
                returnStr += ("No more wumpi remain in the Cave System. You have conquered the caves! You win. ");
                
                if(caveSystem.getCaveLevel() == 2) {
                    Graphics.popup("<html>" + returnStr + "</html>");
                
                } else {
                    System.out.println(returnStr);
                    System.exit(0);
                }
            } else {
                //informing on number of Wumpi remaining
                returnStr += (caveSystem.getWumpLocation().size() + " wumpi remain in the Cave System.\n");
            }
            
        } else {
            returnStr += ("You missed! You have wasted an arrow. ");
            for (int cave : caveSystem.getNearbyCaves(caveShotAt)) {
                //moving wumpus if arrow is shot int an adacent cave
                if (caveSystem.getWumpLocation().contains(cave)) {
                    returnStr += ("Worse news - your arrow spooked the wumpus and it fled to another cave. ");
                    caveSystem.moveWump(cave);
                } 
            }
        }
        return returnStr;
    }
}