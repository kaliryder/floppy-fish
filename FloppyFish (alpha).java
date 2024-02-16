import java.awt.*;
import javax.swing.*;

/**
the Floppy Fish app
@author Kali Ryder
@version 2019/03/04
*/
public class FloppyFish
{
   /** length of screen */
   public static final int SCREEN_LENGTH = 825;
   
   /** width of screen */
   public static final int SCREEN_WIDTH = 600;
   
   /** size of fish */
   public static final int FISH_SIZE = 20;
   
   /** break size between seaweed strands */
   public static final int BREAK_SIZE = FISH_SIZE * 4;
   
   /** height of fish movement upon tap */
   public static final int SWIM_AMT = FISH_SIZE;
   
   /** lowest possible top of break */
   public static final int BREAK_LOWIDX = ((SCREEN_LENGTH / 3) * 2) - BREAK_SIZE;
   
   /** highest possible top of break */
   public static final int BREAK_HIGHIDX = SCREEN_LENGTH / 3;
         
   /** the window of the app */
   private JFrame myWindow;
   
   /** the main screen of the app */
   private MainScreen myMainScreen;
   
   /** the game screen of the app */
   private GameScreen myGameScreen;
   
   /**
   constructor
   */
   public FloppyFish()
   {
      myMainScreen = new MainScreen();
      
      myGameScreen = new GameScreen();
      
      myWindow = new JFrame();
      myWindow.setSize(SCREEN_WIDTH, SCREEN_LENGTH);
      myWindow.setTitle("Floppy Fish"); 
      myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      myWindow.add(myMainScreen);
   
      myWindow.setVisible(true);
   }
   
   public static void main(String[] args)
   {
      FloppyFish test = new FloppyFish();
   }
}