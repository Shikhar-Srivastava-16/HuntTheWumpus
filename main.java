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
}