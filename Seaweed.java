import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

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
   
   /** x-coord of fish stays constant */
   public static final double FISH_XCOORD = FloppyFish.FISH_XCOORD;
   
   /** break size between strand pairs */
   public static final int BREAK_SIZE = FloppyFish.BREAK_SIZE;
   
   /** lowest possible top of break */
   public static final int BREAK_LOWIDX = FloppyFish.BREAK_LOWIDX;
   
   /** highest possible top of break */
   public static final int BREAK_HIGHIDX = FloppyFish.BREAK_HIGHIDX;
   
   /** move amount for seaweed strands every update */
   public static final double STRAND_MOVE_AMT = FloppyFish.STRAND_MOVE_AMT;
   
   /** width of seaweed strands */
   public static final double STRAND_WIDTH = FloppyFish.STRAND_WIDTH;
   
   /** FloppyFish app object */
   private FloppyFish myApp;
   
   /** GameScreen object */
   private GameScreen myGS;
   
   /** y-coord of break top */
   private int myBreakTop;
   
   /** y-coord of break bottom */
   private int myBreakBottom;
   
   /** x-coord of strand pair */
   private double myXCoord;
   
   /** strand 1 upper left corner x-coord */
   private double strand1X;
   
   /** strand 1 upper left corner y-coord */
   private double strand1Y;
   
   /** strand 1 width */
   private double strand1W;
   
   /** strand 1 length */
   private double strand1L;
   
   /** strand 2 upper left corner x-coord */
   private double strand2X;
   
   /** strand 2 upper left corner y-coord */
   private double strand2Y;
   
   /** strand 2 width */
   private double strand2W;
   
   /** strand 2 length */
   private double strand2L;
   
   /** x-coord of the right side of strands */
   private double rightXCoord;
   
   /** strand buffered image */
   private BufferedImage myStrandPic;
   
   /**
   constructor
   @param app FloppyFish app object
   @param gs GameScreen object
   @param xCoord starting x coordinate of seaweed
   */
   public Seaweed(FloppyFish app, GameScreen gs, int xCoord)
   {
      myApp = app;
      myGS = gs;
      
      int randNum = this.randNumRange(BREAK_HIGHIDX, BREAK_LOWIDX);
      myBreakTop = randNum;
      myBreakBottom = myBreakTop + BREAK_SIZE;
      myXCoord = xCoord;
      
      myStrandPic = myApp.getStrandPic();
   }
   
   /**
   returns myBreakTop
   @return myBreakTop
   */
   public int getBreakTop()
   {
      return myBreakTop;
   }
   
   /**
   returns myBreakBottom
   @return myBreakBottom
   */
   public int getBreakBottom()
   {
      return myBreakBottom;
   }
   
   /**
   returns myXCoord
   @return myXCoord
   */
   public double getXCoord()
   {
      return myXCoord;
   }
   
   /**
   returns first strand x-coord
   @return strand1X
   */
   public double getS1X()
   {
      return strand1X;
   }
   
   /**
   returns first strand y-coord
   @return strand1Y
   */
   public double getS1Y()
   {
      return strand1Y;
   }
   
   /**
   returns first strand width
   @return strand1W
   */
   public double getS1W()
   {
      return strand1W;
   }
   
   /**
   returns first strand length
   @return strand1L
   */
   public double getS1L()
   {
      return strand1L;
   }
   
   /**
   returns second strand x-coord
   @return strand2X
   */
   public double getS2X()
   {
      return strand2X;
   }
   
   /**
   returns second strand y-coord
   @return strand2Y
   */
   public double getS2Y()
   {
      return strand2Y;
   }
   
   /**
   returns second strand width
   @return strand2W
   */
   public double getS2W()
   {
      return strand2W;
   }
   
   /**
   returns second strand length
   @return strand2L
   */
   public double getS2L()
   {
      return strand2L;
   }
      
   /**
   reset method returns seaweed to the far right 
   of screen after it has reached the far left 
   and re-randomized break location as to appear 
   as a separate seaweed pair
   */
   public void reset()
   {
      System.out.println("** Seaweed.reset() called");
      
      int randNum = this.randNumRange(BREAK_HIGHIDX, BREAK_LOWIDX);
      myBreakTop = randNum;
      myBreakBottom = myBreakTop + BREAK_SIZE;
      myXCoord = (double) SCREEN_WIDTH;
      myGS.addPoint();
   }
   
   /**
   move method moves seaweed pair to the left
   of the screen by MOVE_AMT if the pair has not
   yet reached the far left of the screen, else
   calls the pair to reset()
   */
   public void move()
   {
      System.out.println("** Seaweed.move() called");
      
      rightXCoord = myXCoord + STRAND_WIDTH;
      
      if(rightXCoord >= 0)
      {
         myXCoord = myXCoord - STRAND_MOVE_AMT;
      }
      else
      {
         this.reset();
      }
   }
   
   /**
   draws two seaweed strands on the screen
   @param g2 the graphics handler
   */
   public void drawStrands(Graphics2D g2)
   {
      System.out.println("** Seaweed.drawStrands() called");
      
      strand1X = myXCoord;
      strand1Y = 0;
      
      strand1W = STRAND_WIDTH;
      strand1L = myBreakTop;
      
      strand2X = myXCoord;
      strand2Y = myBreakBottom;
      
      strand2W = STRAND_WIDTH;
      strand2L = SCREEN_LENGTH - myBreakTop;
      
      Rectangle2D.Double strand1 = new Rectangle2D.Double(strand1X, strand1Y, strand1W, strand1L);
      g2.fill(strand1);
      
      Rectangle2D.Double strand2 = new Rectangle2D.Double(strand2X, strand2Y, strand2W, strand2L);
      g2.fill(strand2);  
      
      g2.drawImage(myStrandPic, (int) myXCoord, 
         (int) (myBreakBottom - BREAK_SIZE - 
            (SCREEN_LENGTH - BREAK_HIGHIDX)), null);
            
      g2.drawImage(myStrandPic, (int) strand2X, (int) strand2Y, null);    
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
}
