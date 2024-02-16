import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Random;

/**
models a "pair" of seaweed strands
@author Kali Ryder
@version 2019/03/04
*/
public class Seaweed
{
   /** length of screen */
   public static final int SCREEN_LENGTH = FloppyFish.SCREEN_LENGTH;
   
   /** width of screen */
   public static final int SCREEN_WIDTH = FloppyFish.SCREEN_WIDTH;
   
   /** size of fish */
   public static final int FISH_SIZE = FloppyFish.FISH_SIZE;
   
   /** break size between strand pairs */
   public static final int BREAK_SIZE = FISH_SIZE * 2;
   
   /** lowest possible top of break */
   public static final int BREAK_LOWIDX = ((SCREEN_LENGTH / 3) * 2) - BREAK_SIZE;
   
   /** highest possible top of break */
   public static final int BREAK_HIGHIDX = SCREEN_LENGTH / 3;
   
   /** y-coord of break top */
   private int myBreakTop;
   
   /** y-coord of break bottom */
   private int myBreakBottom;
   
   /** x-coord of strand pair */
   private int myXCoord;
   
   public Seaweed()
   {
      System.out.println("** Seaweed.Seaweed() called");
      
      int randNum = this.randNumRange(BREAK_HIGHIDX, BREAK_LOWIDX);
      myBreakTop = randNum;
      myBreakBottom = myBreakTop + BREAK_SIZE;
      myXCoord = SCREEN_WIDTH; //far right of screen
   }
   
   public void startGame()
   {
      System.out.println("** Seaweed.startGame() called");
      
      //move seaweed to the left of screen at constant rate
   }
   
   /**
   draws two seaweed strands on the screen
   @param g2 the graphics handler
   */
   public void drawPair(Graphics2D g2)
   {
      System.out.println("** Seaweed.drawPair() called");
      
      int strand1X = SCREEN_WIDTH - BREAK_SIZE;
      int strand1Y = 0; //top of screen
      
      int strand1W = (int) (SCREEN_WIDTH * 0.15);
      int strand1L = myBreakTop;
      
      int strand2X = SCREEN_WIDTH - BREAK_SIZE;
      int strand2Y = myBreakBottom;
      
      int strand2W = (int) (SCREEN_WIDTH * 0.15);
      int strand2L = SCREEN_LENGTH - myBreakTop;
      
      System.out.println(strand1X);
   
      g2.setColor(Color.GREEN);
      
      Rectangle2D.Double strand1 = new Rectangle2D.Double(strand1X, strand1Y, strand1W, strand1L);
      g2.fill(strand1);
      
      Rectangle2D.Double strand2 = new Rectangle2D.Double(strand2X, strand2Y, strand2W, strand2L);
      g2.fill(strand2);
   }
   
   /** 
   private method uses the java.Random class to generate a random
   integer in a range defined by lo-hi (inclusive). (hi - lo) is the
   range, add one to make the hi value inclusive, then add lo to
   increment the result
   @param lo lower bound
   @param hi upper bound
   @return random integer between lo and hi
   */
   private static int randNumRange(int lo, int hi)
   {
      System.out.println("** Seaweed.randNumRange() called");
      
      if(lo >= hi)
      {
         throw new IllegalArgumentException("lo must be less than hi");
      }
      else
      {
         Random r = new Random();
         int randNum = r.nextInt((hi - lo) + 1) + lo;
         return randNum;
      }
   }
   
   public String toString()
   {
      String str = "";
      str += "Break Top: " + myBreakTop;
      str += "\n\tBreak Bottom: " + myBreakBottom;
      str += "\n\tX Coordinate: " + myXCoord;
      return str;
   }
   
   public static void main(String[] args)
   {
      System.out.println("Expected: Break Top: (random integer between " 
         + BREAK_HIGHIDX + " and " + BREAK_LOWIDX + ")\n\t  Break Bottom: "
            + "(Break Top + 40)" + "\n\t  X Coordinate: 600");
      Seaweed test = new Seaweed();
      System.out.println("Actual: " + test);
      System.out.println("Expected: Break Top: (random integer between " 
         + BREAK_HIGHIDX + " and " + BREAK_LOWIDX + ")\n\t  Break Bottom: "
            + "(Break Top + 40)" + "\n\t  X Coordinate: 600");
      Seaweed test2 = new Seaweed();
      System.out.println("Actual: " + test2);
      
   }
}
