import java.util.*;

public class main {
    
    //Scanner for reading user input
    private static Scanner inputReader = new Scanner(System.in);
    //Randomiser
    private static Random randomiser = new Random();

    public static void main(String[] args) {

        //Generating map:
        CaveSystem caveSystem1 = new CaveSystem(1);

        //Generate player
        int caveNum = caveSystem1.generatePlayerCave(randomiser);
        Player player = new Player(caveNum, 3);

        //Traversal
        while (true) {
            player.movePlayer(caveSystem1, inputReader);
            int playerLocation = player.getCaveNum();

            checkForEvent(caveSystem1, playerLocation, player);
            checkForSenses(playerLocation, caveSystem1);
        }
    }

    public static void checkForEvent(CaveSystem caveSystem1, int playerLocation, Player player) {

        if (caveSystem1.getPitLocation().contains(playerLocation)) {
            System.out.println("Falls.");
            System.exit(0);
        }

        //check for Bat
        if (caveSystem1.getBatLocation().contains(playerLocation)) {
            System.out.println("Bat.");
            //bat can drop you onto any empty square.
            player.setCaveNum(caveSystem1.generatePlayerCave(randomiser));
            System.out.printf("new location: %d", player.getCaveNum());
            System.out.println("Now in cave " + player.getCaveNum());
        }

        //check for arrow
        if (caveSystem1.getArrowLocation().contains(playerLocation)) {
            System.out.println("pokey stick.");
            int currentArrows = player.getQtyArrows();
            player.setQtyArrows(currentArrows++);
        }
    }

    public static void checkForSenses(int caveNum, CaveSystem caveSystem) {

        HashSet<Integer> nearbyCaves = caveSystem.getNearbyCaves(caveNum);
        for (int cave : nearbyCaves) {
            if (caveSystem.getWumpLocation().contains(cave)) {
                System.out.println("smell");
                break;
            } 
        }
        
        for (int cave : nearbyCaves) {
            if (caveSystem.getBatLocation().contains(cave)){
                System.out.println("flap");
                break;
            }
        } 
        
        for (int cave : nearbyCaves) {
            if (caveSystem.getPitLocation().contains(cave)) {
                System.out.println("breeze");
                break;
            }
        }
    }
}