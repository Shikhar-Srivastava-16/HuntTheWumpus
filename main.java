import java.util.*;

public class main {

    private static HashMap<Integer, int[]> layoutMap = new HashMap<>();
    
    public static int[] values1 = {2,5,8};
    public static int[] values2 = {1,3,10};
    public static int[] values3 = {2,4,12};
    public static int[] values4 = {3,5,14};
    public static int[] values5 = {1,4,6};
    public static int[] values6 = {5,7,15};
    public static int[] values7 = {6,8,17};
    public static int[] values8 = {1,7,9};
    public static int[] values9 = {8,10,18};
    public static int[] values10 = {2,9,11};
    public static int[] values11 = {10,12,19};
    public static int[] values12 = {3,11,13};
    public static int[] values13 = {12,14,20};
    public static int[] values14 = {13,15,4};
    public static int[] values15 = {6,14,16};
    public static int[] values16 = {15,17,20};
    public static int[] values17 = {16,18,7};
    public static int[] values18 = {9,17,19};
    public static int[] values19 = {11,18,20};
    public static int[] values20 = {13,16,19};
    
    public static int[][] arrayLayout = {values1, values2, values3, values4, values5, values6, values7, values8, values9, values10, values11, values12, values13, values14, values15, values16, values17, values18, values19, values20};
    
    public static void main(String[] args) {
        
        for (int i = 1; i <= arrayLayout.length; i++) {
                layoutMap.put(i, arrayLayout[i-1]);
        }

        //generate player object
        Random random = new Random();
        int caveGenerated = random.nextInt(21);
        int arrowsGenerated = random.nextInt(6);
        Player newPlayer = new Player(caveGenerated, arrowsGenerated);

    }

    public void playerMovement(int currentPlayerPosition, Player player) {

        System.out.println("You can move to: " + layoutMap.get(currentPlayerPosition));
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Where to?");
        int newCave = inputScanner.nextInt();

    }
}