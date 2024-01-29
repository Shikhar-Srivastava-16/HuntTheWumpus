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
    
    private static int[] values1 = {2,5,8};
    private static int[] values2 = {1,3,10};
    private static int[] values3 = {2,4,12};
    private static int[] values4 = {3,5,14};
    private static int[] values5 = {1,4,6};
    private static int[] values6 = {5,7,15};
    private static int[] values7 = {6,8,17};
    private static int[] values8 = {1,7,9};
    private static int[] values9 = {8,10,18};
    private static int[] values10 = {2,9,11};
    private static int[] values11 = {10,12,19};
    private static int[] values12 = {3,11,13};
    private static int[] values13 = {12,14,20};
    private static int[] values14 = {13,15,4};
    private static int[] values15 = {6,14,16};
    private static int[] values16 = {15,17,20};
    private static int[] values17 = {16,18,7};
    private static int[] values18 = {9,17,19};
    private static int[] values19 = {11,18,20};
    private static int[] values20 = {13,16,19};
    
    private static int[][] arrayLayout = {values1, values2, values3, values4, values5, values6, values7, values8, values9, values10, values11, values12, values13, values14, values15, values16, values17, values18, values19, values20};

    private final int caveLevel;
    private final HashSet arrowLocation;
    private final HashSet pitLocation;
    
    //getters
    public static int[][] getArrayLayout() {
        return arrayLayout;
    }
    
    public HashMap<Integer, int[]> getLayoutMap() {
        return layoutMap;
    }

    //Constructor
    public CaveSystem(int caveLevel) {
        for (int i = 1; i <= arrayLayout.length; i++) {
            layoutMap.put(i, arrayLayout[i-1]);
        }
        this.caveLevel = caveLevel;
        this.arrowLocation = generateSet(5);
        this.pitLocation = generateSet(5);
    }

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
}
