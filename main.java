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
        //check for nearby cave/bat/wump.
        checkForSenses(player.getCaveNum(), caveSystem1);

        while (true) {
            //getting action from player
            System.out.println("What do: move/shoot");
            String action = inputReader.nextLine();
            switch (action) {
                case "move":
                    //Traversal
                    player.movePlayer(caveSystem1, inputReader);
                    int playerLocation = player.getCaveNum();
    
                    checkForEvent(caveSystem1, playerLocation, player);
                    checkForSenses(playerLocation, caveSystem1);   
                    break;
                case "shoot":
                    shootWump(caveSystem1, player);
                    System.out.println("shoot");
                    break;
                default:
                    break;
            }
            
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
            System.out.println("Now in cave " + player.getCaveNum());
            checkForSenses(player.getCaveNum(), caveSystem1);
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

    public static void shootWump(CaveSystem caveSystem, Player player) {

        System.out.printf("You can shoot into the caves: ");
        for (int adjCave : caveSystem.getLayoutMap().get(player.getCaveNum())) {
            System.out.printf("%d ", adjCave);
        }

        int caveShotAt = inputReader.nextInt();
        if (caveSystem.wumpDeath(caveShotAt)) {
            System.out.println("wump ded");
            if (caveSystem.getWumpLocation().size() == 0) {
                System.out.println("gaem end.");
            } else {
                System.out.printf("$d wump rem", caveSystem.getWumpLocation().size());
            }
            
        } else {
            System.out.println("wump no poke");
            for (int cave : caveSystem.getNearbyCaves(caveShotAt)) {
                if (caveSystem.getWumpLocation().contains(cave)) {
                    System.out.println("wump move");
                    caveSystem.moveWump(cave);
                    break;
                } 
            }
        }

        System.out.printf("%d Arrows rem", player.getQtyArrows());

    }
}
