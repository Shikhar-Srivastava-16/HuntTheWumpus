import java.util.*;

public class main {

    public static void main(String[] args) {
        
        //object for randomisation, which is used in a lot of functions
        Random random = new Random();

        //generate HashMap for cave layout
        for (int i = 1; i <= arrayLayout.length; i++) {
                layoutMap.put(i, arrayLayout[i-1]);
        }

        //generate player object
        //randomly generated cave
        int caveGenerated = 1 + random.nextInt(20);
        //randomly generated number of arrows
        int arrowsGenerated = 1 + random.nextInt(5);
        //player generated
        Player newPlayer = new Player(caveGenerated, arrowsGenerated);

        //generate location of Wumpus and Wumpus object (TBD)
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

    /*
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
     */
}