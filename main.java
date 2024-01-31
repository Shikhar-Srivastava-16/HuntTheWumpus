import java.util.*;

public class main {

    public static void main(String[] args) {
        
        //Scanner for reading user input
        Scanner inputReader = new Scanner(System.in);
        //Randomiser
        Random randomiser = new Random();

        //Generating map:
        CaveSystem caveSystem1 = new CaveSystem(1);

        //Generate player
        //int numArrows = ;
        int caveNum = 0;
        while (caveNum == 0) {
            int num = 1 + randomiser.nextInt(20);
            if (!(caveSystem1.getPitLocation().contains(num) 
            || caveSystem1.getArrowLocation().contains(num)
            || caveSystem1.getWumpLocation().contains(num))) {
                caveNum = num;
            }
        }

        Player player = new Player(caveNum, 3);

    }
}