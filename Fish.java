import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

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
   
   /** height of fish movement upon tap */
   public static final double SWIM_AMT = FloppyFish.SWIM_AMT;
   
   /** x-axis position of fish stays constant in the center */
   public static final double FISH_XCOORD = FloppyFish.FISH_XCOORD;
   
   /** add every update while fish falls */
   public static final double FISH_ACCELERATION = FloppyFish.FISH_ACCELERATION;
   
   /** original velocity when screen is tapped */
   public static final double FISH_OG_VELOCITY = FloppyFish.FISH_OG_VELOCITY;
   
   /** FloppyFish app object */
   private FloppyFish myApp;
   
   /** fish buffered image */
   private BufferedImage myFishPic;
   
   /** y-axis position of fish */
   private double myYCoord;
   
   /** incrementation for falling fish */
   private double myVelocity;
   
   /**
   constructor puts fish object in the 
   middle of the screen vertically and
   sets velocity back to default
   @param app FloppyFish object
   @param y initial y-coord of fish
   */
   public Fish(FloppyFish app, int y)
   {
      System.out.println("** Fish.Fish() called");
      
      myApp = app;
      
      myYCoord = SCREEN_LENGTH / 2;
      myVelocity = FISH_OG_VELOCITY;
      
      myFishPic = myApp.getFishPic();
      
      myYCoord = y;
   }
      
   /**
   returns y-coord of fish object
   @return y-coord of fish object
   */
   public double getYCoord()
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
      
      myYCoord = myYCoord - SWIM_AMT;
      myVelocity = FISH_OG_VELOCITY;
   }
   
   /**
   when the screen is not tapped, fish should begin
   falling towards the bottom of the screen at 
   the rate of 'gravity'
   */
   public void fall()
   {
      System.out.println("** Fish.fall() called");
      
      myYCoord = myYCoord + myVelocity;
      myVelocity = myVelocity + FISH_ACCELERATION;
   }
   
   /**
   draws the fish in the screen
   @param g2 the graphics handler
   */
   public void drawFish(Graphics2D g2)
   {
      System.out.println("** Fish.drawFish() called");
      
      g2.drawImage(myFishPic, (int) FISH_XCOORD, (int) myYCoord, null);
   }
   
   /**
   toString method
   */
   public String toString()
   {
      String str = "";
      str += "X Coordinate: " + FISH_XCOORD;
      str += " Y Coordinate: " + myYCoord;
      return str;
   }
}