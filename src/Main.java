import java.util.*;

public class Main {
    
    //Scanner for reading user input
    protected static Scanner inputReader = new Scanner(System.in);
    //Randomiser
    protected static Random randomiser = new Random();
    
    //Game elements
    protected static CaveSystem caveSystem;
    protected static Player player;
    
    /** 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Graphics.titleScreen();
        Graphics.buttonModeListener();

        //Generate player
        while (true) {
            int caveNum;
            try {
                caveNum = caveSystem.generatePlayerCave(randomiser);
                player = new Player(caveNum, 3);
                break;
            } catch (Exception e) {
                Thread.sleep(500);
                continue;
            }
        }

        if (caveSystem.getCaveLevel() == 2) {
            Graphics.getTitleWindow().dispose();
            //check for nearby cave/bat/wump.
            System.out.println(checkForSenses());
            System.out.println(caveSystem.getWumpLocation());
            nightmareMode();
        } else {
            Graphics.otherModes();
        }
    }
    
    public static Event checkForEvent() {
        
        int playerLocation = player.getCaveNum();
        if (caveSystem.getPitLocation().contains(playerLocation)) {
            return (Event.FALL);

        } else if (caveSystem.getBatLocation().contains(playerLocation)) {
            //check for Bat
            return (Event.BAT);

        } else if (caveSystem.getArrowLocation().contains(playerLocation)) {
            //check for arrow
            return Event.ARROW;
        } else if (caveSystem.getWumpLocation().contains(playerLocation)) {
            //check for wump
            return Event.WUMPUS;
        }
        else {
            return Event.NONE;
        }
    }

    public static void doEvent (Event event) {

        switch (event) {
            case ARROW:
                System.out.println("You find a bundle of arrows! You decide to take one.");
                int currentArrows = player.getQtyArrows();
                player.setQtyArrows(currentArrows++);
                System.out.printf("Now you have %d Arrows in your quiver.", player.getQtyArrows());
                break;

            case BAT:
                //bat can drop you onto any empty square.
                System.out.println("As you enter the cave, the sound of flapping wings grows louder. You find yourself being lifted through the air by a gigantic bat.");
                player.setCaveNum(caveSystem.generatePlayerCave(randomiser));
                System.out.printf("Now, you are in cave %d\n", player.getCaveNum());
                checkForSenses();
                break;

            case FALL:
                System.out.println("You lose your footing and fall into an endless pit! The End.");
                System.exit(0);
                break;

            case WUMPUS:
                System.out.println("You walk into the cave and come to the horrible realisation that the wumpus stirs astride you. Hunger in it's snake-like eyes, it pounces."); 
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public static String checkForSenses() {
        
        String printStr = "";
        HashSet<Integer> nearbyCaves = caveSystem.getNearbyCaves(player.getCaveNum());
        for (int cave : nearbyCaves) {
            if (caveSystem.getWumpLocation().contains(cave)) {
                printStr += ("The foul stench of the wumpus accosts your nose. You wish you had a set of earplugs to shove in your nostrils .\n");
                break;
            } 
        }
        
        for (int cave : nearbyCaves) {
            if (caveSystem.getBatLocation().contains(cave)){
                printStr += ("You hear an indistinct sound. Wait, is it-? \nYes, you hear bats .\n");
                break;
            }
        } 
        
        for (int cave : nearbyCaves) {
            if (caveSystem.getPitLocation().contains(cave)) {
                printStr += ("You feel a breeze. It makes you crave hot cocoa and marshmallows .\n");
                break;
            }
        }

        return printStr;
    }
    
    public static void nightmareMode() {
        
        //give mode to player
        System.out.println("You are in Nightmare mode. There are multiple wumpi, more pits, less arrows, and you are inflicted with blindness.");

        while (true) {
            //getting action from user
            System.out.println("What do: move/shoot");
            String action = inputReader.next();
            switch (action) {
                case "move":
                    //Traversal
                    player.movePlayer(caveSystem, inputReader);
                    doEvent(checkForEvent());
                    System.out.println(checkForSenses());   
                    break;

                case "shoot":
                    //shooting
                    player.shootWump(caveSystem, inputReader);
                    System.out.println("shoot");
                    break;

                default:
                    System.out.println("Invalid input, try again.");
                    break;
            } 
        }
    }
}