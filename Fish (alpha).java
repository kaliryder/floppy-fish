import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
models a fish character
@author Kali Ryder
@version 2019/03/04
*/
public class Fish
{
   /** length of screen */
   public static final int SCREEN_LENGTH = FloppyFish.SCREEN_LENGTH;
   
   /** width of screen */
   public static final int SCREEN_WIDTH = FloppyFish.SCREEN_WIDTH;
   
   /** size of fish */
   public static final int FISH_SIZE = FloppyFish.FISH_SIZE;
   
   /** height of fish movement upon tap */
   public static final int SWIM_AMT = FloppyFish.SWIM_AMT;
   
   /** x-axis position of fish stays constant in the center */
   public static final int X_COORD = FloppyFish.SCREEN_WIDTH / 2;
   
   /** y-axis position of fish */
   private int myYCoord;
   
   /**
   constructor puts fish object in the 
   middle of the screen vertically
   */
   public Fish()
   {
      System.out.println("** Fish.Fish() called");
      
      myYCoord = SCREEN_LENGTH / 2;
   }
      
   /**
   returns y-coord of fish object
   @return y-coord of fish object
   */
   public int getYCoord()
   {
      System.out.println("** Fish.getYCoord() called");
      
      return myYCoord;
   }
   
   /**
   method called when screen is tapped, 
   makes fish swim upward a uniform amount
   */
   public void swimTap()
   {
      System.out.println("** Fish.swimTap() called");
      
      myYCoord = myYCoord + SWIM_AMT;
   }
   
   /**
   when the fish begins the game, it should begin
   falling towards the bottom of the screen at 
   the rate of 'gravity'
   */
   public void startGame()
   {
      System.out.println("** Fish.startGame() called");
      //some implementation of gravity
   }
   
   /**
   when the game ends, fish should fall
   gradually to the bottom of the screen
   for now fish moves directly to the bottom
   */
   public void endGame()
   {
      System.out.println("** Fish.endGame() called");
      
      myYCoord = SCREEN_LENGTH;
   }
   
   /**
   draws the fish in the screen
   @param g2 the graphics handler
   */
   public void drawFish(Graphics2D g2)
   {
      System.out.println("** Fish.drawFish() called");
   
      g2.setColor(Color.BLUE);
      
      Ellipse2D.Double fishbody = new Ellipse2D.Double(X_COORD, myYCoord, 20, 20);
      g2.fill(fishbody);
   }

   public String toString()
   {
      String str = "";
      str += "X Coordinate: " + X_COORD;
      str += " Y Coordinate: " + myYCoord;
      return str;
   }
   
   public static void main(String[] args)
   {
      Fish test = new Fish();
      System.out.println("Expected: X Coordinate: 300 Y Coordinate:"
         + " 412");
      System.out.println("Actual: " + test);
      test.swimTap();
      System.out.println("Expected: X Coordinate: 300 Y Coordinate: 432");
      System.out.println("Actual: " + test);
      System.out.println("Expected: Y Coordinate: 432");
      System.out.println("Actual: Y Coordinate: " + test.getYCoord());
      test.endGame();
      System.out.println("Expected: X Coordinate: 300 Y Coordinate: 825");
      System.out.println("Actual: " + test);
   }
}