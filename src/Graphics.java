import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.HashSet;

public class Graphics extends Main{

    //private static JPanel panel = new JPanel();
    private static JFrame titleWindow = new JFrame("Hunt The Wumpus");
    private static JButton[] movers = new JButton[3];
    private static JButton[] shooters = new JButton[3];
    private static JLabel eventLabel = new JLabel();
    private static JLabel senseLabel = new JLabel();
    private static JLabel mapLabel = new JLabel();
    
    /** 
     * @return JFrame
     */
    public static JFrame getTitleWindow() {
        return titleWindow;
    }
    
    public static void addButtons(int yLevel, JButton[] arrayButtons) {

        arrayButtons[0] = new JButton();
        arrayButtons[0].setBounds(175, yLevel, 150, 50);
        titleWindow.add(arrayButtons[0]);

        arrayButtons[1] = new JButton();
        arrayButtons[1].setBounds(425, yLevel, 150, 50);
        titleWindow.add(arrayButtons[1]);
  
        arrayButtons[2] = new JButton();
        arrayButtons[2].setBounds(675, yLevel, 150, 50);
        titleWindow.add(arrayButtons[2]);
    }

    public static void titleScreen() {
        
        titleWindow.setLayout(null);
        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleWindow.setSize(1000, 1000);
        
        //adding title
        JLabel title = new JLabel("Hunt The Wumpus", SwingConstants.CENTER);
        title.setBounds(125, 500, 750, 100);
        title.setFont(new Font("Serif", Font.PLAIN, 70));
        titleWindow.add(title);

        //adding Image
        JLabel picture = new JLabel(new ImageIcon("./../resources/wumpus.png"));
        picture.setBounds(0,200,1000,400);
        titleWindow.add(picture);
        
        addButtons(650, movers);

        //caveLevel = 0
        movers[0].setText("Easy");

        //caveLevel = 1
        movers[1].setText("Hard");
  
        //caveLevel = 2
        movers[2].setText("Nightmare");
        
        //titleWindow.setContentPane(panel);
        titleWindow.setVisible(true);
    }

    public static void buttonModeListener() {

        movers[0].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                caveSystem = new CaveSystem(0);
            }
            
        });
        
        movers[1].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                caveSystem = new CaveSystem(1);
            }
            
        });
        
        movers[2].addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                caveSystem = new CaveSystem(2);
            }
            
        });
    }

    public static void changeButtonForGame() {

        for (int i = 0; i < movers.length; i++) {

            String num = String.valueOf(caveSystem.getLayoutMap().get(player.getCaveNum())[i]);
            movers[i].setText(num);
            shooters[i].setText(num);
        }
    }

    public static void setMap(int caveNum, JLabel mapLabel) {
        String fileName = ("./../resources/map" + caveNum + ".png").toString();
        mapLabel.setIcon(new ImageIcon(fileName));
    }

    public static void otherModes() {
        
        titleWindow.getContentPane().removeAll();
        addButtons(660, movers);
        addButtons(760, shooters);
        changeButtonForGame();

        eventLabel.setBounds(75, 800, 850, 100);
        eventLabel.setText("All events will be displayed here");
        eventLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        titleWindow.add(eventLabel);
        
        senseLabel.setBounds(75, 850, 850, 100);
        senseLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        senseLabel.setText("<html>" + checkForSenses() + "</html>");
        titleWindow.add(senseLabel);

        JLabel label1 = new JLabel("Move:");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setBounds(200,620,100,50);
        titleWindow.add(label1);

        JLabel label2 = new JLabel("Shoot:");
        label2.setFont(new Font("Serif", Font.PLAIN, 20));
        label2.setBounds(200,720,100,50);
        titleWindow.add(label2);

        mapLabel.setBounds(200, 10, 600, 600);
        titleWindow.add(mapLabel);
        setMap(player.getCaveNum(), mapLabel);

        mover();
        shooter();
        titleWindow.repaint();
    }

    public static void doEvent (Event event){

        switch (event) {
            case ARROW:
                eventLabel.setText("<html>You find a bundle of arrows! You decide to take one. Now you have " + player.getQtyArrows() + " Arrows in your quiver.</html>");
                break;

            case BAT:
                //bat can drop you onto any empty square.
                int newCaveNum = caveSystem.generatePlayerCave(randomiser);
                player.setCaveNum(newCaveNum);
                setMap(newCaveNum, mapLabel);
                eventLabel.setText("<html>As you enter the cave, the sound of flapping wings grows louder. You find yourself being lifted through the air by a gigantic bat. Now, you are in cave " + player.getCaveNum() + "</html>");
                senseLabel.setText("<html>" + checkForSenses() + "</html>");
                break;

            case FALL:
                popup("<html>You lose your footing and fall into an endless pit! The End.</html>");
                System.out.println("fall");
                break;

            case WUMPUS:
                popup("<html>You walk into the cave and come to the horrible realisation that the wumpus stirs astride you. Hunger in it's snake-like eyes, it pounces. The End</html>"); 
                System.out.println("monch");
                break;
            default:
                break;
        }
    }

    public static void mover() {

        movers[0].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moveAction(movers[0]);
            }            
        });

        movers[1].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moveAction(movers[1]);
            }
            
        });

        movers[2].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moveAction(movers[2]);
            }
            
        });
    }

    public static void moveAction(JButton button) {

        int newCaveNum = Integer.parseInt(button.getText());
        setMap(newCaveNum, mapLabel);
        player.setCaveNum(newCaveNum);
        doEvent(checkForEvent());
        senseLabel.setText("<html>" + checkForSenses() + "</html>");
        changeButtonForGame();
    }

    public static void shooter() {
        shooters[0].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.printf(shooters[0].getText());
                shootAction(shooters[0]);
            }            
        });

        shooters[1].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                shootAction(shooters[1]);
            }
            
        });

        shooters[2].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                shootAction(shooters[2]);
            }
            
        });
    }

    public static void shootAction(JButton button) {
        int caveShotAt = Integer.parseInt(button.getText());
        System.out.printf(player.doShootCave(caveShotAt, caveSystem));
        eventLabel.setText("<html>" + player.doShootCave(caveShotAt, caveSystem) + "</html>");
    }

    public static void popup(String printStr) {
        JFrame popup = new JFrame("you died!");
        popup.setSize(300, 300);
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html>" + printStr + "</html>");
        label.setBounds(100, 50, 300, 300);
        label.setFont(new Font("Serif",Font.PLAIN, 12));
        popup.add(label);
        popup.setVisible(true);
    }
}