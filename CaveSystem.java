import java.util.*;

public class CaveSystem {
    /*
     * The Cave system is represented as a HashMap
     * Each cave is represented within the program as a number between 1 and 20 (inclusive)
     * each key is a number for a cave
     * the value corresponding to key is an array of integers
     * this array of integers is the set of caves to which there is an available pathway.
     */

    //attributes
    private final HashMap<Integer, int[]> layoutMap = new HashMap<>();
    
    private static int[][] arrayLayout = {
        {2,5,8},
        {1,3,10},
        {2,4,12},
        {3,5,14},
        {1,4,6},
        {5,7,15},
        {6,8,17},
        {1,7,9},
        {8,10,18},
        {2,9,11},
        {10,12,19},
        {3,11,13},
        {12,14,20},
        {13,15,4},
        {6,14,16},
        {15,17,20},
        {16,18,7},
        {9,17,19},
        {11,18,20},
        {13,16,19}
    };

    /**Attributes:
     * caveLevel (currently unused) is an attribute used for difficulty: 0 for Easy, 1 for Hard, 2 for Nightmarish
     * arrowLocation is a Set, whose length is level dependent, which contains all cave numbers where an arrow can be picked up. 
     *     -if the location of the player matches is in this set, the number of arrows is incremented by one
     * pitLocation is a Set, whose length is level dependent, which contains locations of all pits.
     *     -if the location of the player is here, they fall and lose
     * wumpLocation is a Set, whose length is level dependent, which contains the locations of all wumpuses (wumpi...?)
     *     if the players's location is here, they get eaten and lose. 
     * batLocation is a Set, whose length is level dependent, which stores all locations of bats in the game.
     *     If a player lands on these tiles, they are carried away to a random empty cave.  
     */

    private final int caveLevel;
    private HashSet arrowLocation;
    private HashSet pitLocation;
    private HashSet wumpLocation;
    private HashSet batLocation;
    private Random random = new Random();
    
    //getters
    public int getCaveLevel() {
        return caveLevel;
    }

    public HashSet getArrowLocation() {
        return arrowLocation;
    }

    public HashSet getBatLocation() {
        return batLocation;
    }

    public HashSet getPitLocation() {
        return pitLocation;
    }

    public HashSet getWumpLocation() {
        return wumpLocation;
    }

    public static int[][] getArrayLayout() {
        return arrayLayout;
    }
    
    public HashMap<Integer, int[]> getLayoutMap() {
        return layoutMap;
    }

    //Constructor
    public CaveSystem(int caveLevel) {

        if (caveLevel < 0 || caveLevel > 2 ) {
            System.err.println("Invalid level");
            System.exit(1);
        }

        for (int i = 1; i <= arrayLayout.length; i++) {
            layoutMap.put(i, arrayLayout[i-1]);
        }
        this.caveLevel = caveLevel;

        int numOfArrows;
        int numOfPits;
        int numOfWumpi;
        int numOfBats = 5;

        if (caveLevel == 0) {
            
            numOfArrows = 5;
            numOfPits = 3;
            numOfWumpi = 1;

        } else {

            numOfArrows = 3;
            numOfPits = 5;
            numOfWumpi = 2;
        
        }

        this.arrowLocation = generateSet(numOfArrows);
        this.pitLocation = generateSet(numOfPits);
        this.wumpLocation = generateSet(numOfWumpi, this.pitLocation);
        this.batLocation = generateSet(numOfBats, this.pitLocation);

    }

    //methods
    public static HashSet<Integer> generateSet(int sizeGiven) {  
        //generates a set of n unique numbers between 1 and 20
        //used for pits and for arrow drops
        
        Random numberGenerator = new Random();
        HashSet<Integer> set1 = new HashSet<>();
        while (set1.size() < sizeGiven) {
            set1.add(1 + numberGenerator.nextInt(20));
        }
        return set1;
    }

    //overloading generateSet
    public static HashSet<Integer> generateSet(int sizeGiven, HashSet comparisonSet) {
        Random numberGenerator = new Random();
        HashSet<Integer> set1 = new HashSet<>();
        while (set1.size() < sizeGiven) {
            int numToAdd = 1 + numberGenerator.nextInt(20);
            if (!comparisonSet.contains(numToAdd)) {
                set1.add(numToAdd);
            }
        }
        return set1;
    }

    public int generatePlayerCave(Random random) {

        int returner = 0;
        while (returner == 0) {
            int num = 1 + random.nextInt(20);
            if (!(getPitLocation().contains(num) 
            || getArrowLocation().contains(num)
            || getWumpLocation().contains(num))) {
                returner = num;
            }
        }

        return returner;
    }

    public HashSet<Integer> getNearbyCaves(int caveNum) {
        
        HashSet<Integer> returnSet = new HashSet<>();
        
        for (int cave : layoutMap.get(caveNum)) {
            returnSet.add(cave);
            for (int cave2 : layoutMap.get(cave)) {
                returnSet.add(cave2);
            }
        }                
    
        return returnSet;
    }

    public Boolean wumpDeath(int caveShotAt) {
        if (wumpLocation.remove(caveShotAt)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void moveWump(int wumpusCave) {

        wumpLocation.remove(wumpusCave);
        wumpLocation.add(generatePlayerCave(random));

    }
}   
