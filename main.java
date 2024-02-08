import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main {
    
    //Scanner for reading user input
    private static Scanner inputReader = new Scanner(System.in);
    //Randomiser
    private static Random randomiser = new Random();
    
    //Game elements
    private static CaveSystem caveSystem1;
    private static Player player;

    public static void main(String[] args) throws InterruptedException {
                
        //opening title window
        JPanel panel = new JPanel();
        JFrame titleWindow = new JFrame();
        titleWindow.setLayout(null);
        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleWindow.setSize(500, 500);
        
        //adding title
        JLabel title = new JLabel("Hunt The Wumpus", SwingConstants.CENTER);
        title.setLocation(500, 500);
        panel.add(title);
        
        //caveLevel = 0
        JButton buttonE = new JButton("Easy");                
        buttonE.setLocation(50,20);
        panel.add(buttonE);
        
        //caveLevel = 1
        JButton buttonH = new JButton("Hard");                
        panel.add(buttonH);
        buttonE.setLocation(50,40);
        
        //caveLevel = 2
        JButton buttonN = new JButton("Nightmare");           
        buttonE.setLocation(50,60);
        panel.add(buttonN);
        
        titleWindow.setContentPane(panel);
        titleWindow.setVisible(true);
        
        buttonListener(buttonE, buttonH, buttonN);

        //Generate player
        while (true) {
            System.out.println("WHILE");
            int caveNum;
            try {
                caveNum = caveSystem1.generatePlayerCave(randomiser);
                System.out.println("trying");
                player = new Player(caveNum, 3);
                break;
            } catch (Exception e) {
                System.out.println("SLEEPING");
                Thread.sleep(500);
                continue;
            }
        }

        //check for nearby cave/bat/wump.
        if (caveSystem1.getCaveLevel() == 2) {
            System.out.println(checkForSenses(player.getCaveNum(), caveSystem1));
            titleWindow.dispose();
        } else {
            
        }
    }
    
    public static Event checkForEvent(CaveSystem caveSystem1, Player player) {
        
        int playerLocation = player.getCaveNum();
        if (caveSystem1.getPitLocation().contains(playerLocation)) {
            return (Event.FALL);

        } else if (caveSystem1.getBatLocation().contains(playerLocation)) {
            //check for Bat
            return (Event.BAT);

        } else if (caveSystem1.getArrowLocation().contains(playerLocation)) {
            //check for arrow
            return Event.ARROW;
        } else if (caveSystem1.getWumpLocation().contains(playerLocation)) {
            //check for wump
            return Event.WUMPUS;
        }
        else {
            return Event.NONE;
        }
    }

    public static void doEvent (Player player) {
        Event event = checkForEvent(caveSystem1, player);
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
                player.setCaveNum(caveSystem1.generatePlayerCave(randomiser));
                System.out.println("Now, you are in cave" + player.getCaveNum());
                System.out.println(checkForSenses(player.getCaveNum(), caveSystem1));
                break;

            case FALL:
                System.out.println("You lose your footing and fall into an endless pit! The End.");
                System.exit(0);
                break;

            case WUMPUS:
                System.out.println("You walk into the cave and come to the horrible realisation that the wumpus stirs astride you. Hunger in it's snake-like eyes, it pounces."); 
                break;
            default:
                break;
        }
    }

    public static String checkForSenses(int caveNum, CaveSystem caveSystem) {
        
        String printStr = "";
        HashSet<Integer> nearbyCaves = caveSystem.getNearbyCaves(caveNum);
        for (int cave : nearbyCaves) {
            if (caveSystem.getWumpLocation().contains(cave)) {
                printStr += ("The foul stench of the wumpus accosts your nose. You wish you had a set of earplugs to shove in your nostrils.\n");
                break;
            } 
        }
        
        for (int cave : nearbyCaves) {
            if (caveSystem.getBatLocation().contains(cave)){
                printStr += ("You hear an indistinct sound. Wait, is it-? \nYes, you hear bats.\n");
                break;
            }
        } 
        
        for (int cave : nearbyCaves) {
            if (caveSystem.getPitLocation().contains(cave)) {
                printStr += ("You feel a breeze. It makes you crave hot cocoa and marshmallows.\n");
                break;
            }
        }

        return printStr;
    }
    
    public static void buttonListener(JButton button1, JButton button2, JButton button3) {        
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                caveSystem1 = new CaveSystem(0);
            }
            
        });
        
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                caveSystem1 = new CaveSystem(1);
            }
            
        });
        
        button3.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                caveSystem1 = new CaveSystem(2);
                System.out.println("SET TO NIGHTMARE");
            }
            
        });
    }
    
    public static void nightmareMode() {
        
        while (true) {
            //getting action from user
            System.out.println("What do: move/shoot");
            String action = inputReader.next();
            switch (action) {
                case "move":
                    //Traversal
                    player.movePlayer(caveSystem1, inputReader);
                    doEvent(player);
                    System.out.println(checkForSenses(player.getCaveNum(), caveSystem1));   
                    break;

                case "shoot":
                    //shooting
                    player.shootWump(caveSystem1, inputReader);
                    System.out.println("shoot");
                    break;

                default:
                    System.out.println("Invalid input, try again.");
                    break;
            } 
        }
    }

}