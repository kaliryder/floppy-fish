import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
models the game screen of Floppy Fish
@author Kali Ryder
@version 2019/03/04
*/
public class GameScreen extends JPanel
{
   /** length of screen */
   public static final int SCREEN_LENGTH = FloppyFish.SCREEN_LENGTH;
   
   /** width of screen */
   public static final int SCREEN_WIDTH = FloppyFish.SCREEN_WIDTH;
   
   /** x coordinate of fish */
   public static final double FISH_XCOORD = FloppyFish.FISH_XCOORD;
    
   /** width of seaweed strands */
   public static final double STRAND_WIDTH = FloppyFish.STRAND_WIDTH;
   
   /** milliseconds between fish timer updates */
   public static final double FALL_SPEED = FloppyFish.FALL_SPEED;
   
   /** milliseconds before fish timer begins updating */
   public static final double FALL_DELAY = FloppyFish.FALL_DELAY;
   
   /** FloppyFish object */
   private FloppyFish myApp;
   
   /** fish object */
   private Fish myFish;
   
   /** width of fish image */
   private double myFishWidth;
   
   /* height of fish image */
   private double myFishHeight;
   
   /** first seaweed pair */
   private Seaweed myPair1;
   
   /** second seaweed pair */
   private Seaweed myPair2;
      
   /** start button begins gameplay */
   private JButton myStartButton;
   
   /** timer for FishTimer class */
   private Timer fishTimer;
   
   /** timer for PointTimer class */
   private Timer pointTimer;
   
   /** thread updater for UpdateGS class */
   private UpdateGS updater;
   
   /** score of game */
   private int myScore;
   
   /** y-coord fish should begin at,
   even with break of first strand */
   private int myStartingY;
   
   /**
   constructor
   @param app FloppyFish app
   */
   public GameScreen(FloppyFish app)
   {
      myApp = app;
   
      double first = (STRAND_WIDTH / SCREEN_WIDTH);
      double second = (first * 100); //turns strand size from percentage of screen to integer val
      double third = (100 / second); //how many times strands fit into screen horizontally
      myPair1 = new Seaweed(myApp, this, ((int)((SCREEN_WIDTH / third) * 2)));
      myPair2 = new Seaweed(myApp, this, ((int)((SCREEN_WIDTH / third) * 6)));
      
      myStartingY = (myPair2.getBreakTop() + myPair2.getBreakBottom()) / 2;
      
      myFish = new Fish(myApp, myStartingY);
      
      myStartButton = new JButton("start");
      myStartButton.setBounds(200, 400, 
         (int) (SCREEN_WIDTH / 4), (int) (SCREEN_LENGTH / 10));
      myStartButton.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(60f));
      myStartButton.addActionListener(new StartButtonListener());
      myStartButton.setActionCommand("sb");
      add(myStartButton);
      
      myScore = 0;
      myFishWidth = myApp.getFishWidth();
      myFishHeight = myApp.getFishHeight();
   }
      
   /**
   begins animation and gameplay
   */
   public void begin()
   {
      updater = new UpdateGS();
      updater.start();
      addMouseListener(new MouseClicker());
      addKeyListener(new SpaceListener());
      requestFocusInWindow();
      fishTimer = new Timer();
      fishTimer.scheduleAtFixedRate(new FishFall(), 
         (int) FALL_DELAY, (int) FALL_SPEED);
   }
   
   /**
   adds one point to score and calls app.addPoint()
   */
   public void addPoint()
   {
      myScore = myScore + 1;
      myApp.addPoint();
   }
   
   /**
   checks if fish has collided with strands or bottom of screen
   @param fish fish object
   @param seaweed seaweed pair to test
   @return true if intersects, false if not
   */
   public boolean checkCollision(Fish fish, Seaweed seaweed)
   {
      double thisFishY = fish.getYCoord();
      
      double thisStrand1X = seaweed.getS1X();
      double thisStrand1Y = seaweed.getS1Y();
      double thisStrand1W = seaweed.getS1W();
      double thisStrand1L = seaweed.getS1L();
      double thisStrand2X = seaweed.getS2X();
      double thisStrand2Y = seaweed.getS2Y();
      double thisStrand2W = seaweed.getS2W();
      double thisStrand2L = seaweed.getS2L();
      
      Rectangle2D.Double fishBounds = new Rectangle2D.Double(FISH_XCOORD, 
         thisFishY, myFishWidth, myFishHeight);
      
      Rectangle2D.Double s1bounds = new Rectangle2D.Double(thisStrand1X, 
         thisStrand1Y, thisStrand1W, thisStrand1L);
               
      Rectangle2D.Double s2bounds = new Rectangle2D.Double(thisStrand2X, 
         thisStrand2Y, thisStrand2W, thisStrand2L);
            
      if(fishBounds.intersects(s1bounds))
      {
         return true;
      }
      else if(fishBounds.intersects(s2bounds))
      {
         return true;
      }
      else if(thisFishY >= SCREEN_LENGTH)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
   paint component draws game components on panel
   including score, fish and seaweed strands
   @param g Graphics object
   */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      g2.drawImage(myApp.getBGPic(), 0, 0, null);
      
      String myScoreString = Integer.toString(myScore);
      setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(60f));
      g2.drawString(myScoreString, SCREEN_WIDTH / 2 - 10, 100);
      
      myFish.drawFish(g2);
      myPair1.drawStrands(g2);
      myPair2.drawStrands(g2);
   }
   
   /**
   private method returns the flappyFont imported
   from the internet in file FlappyBirdy.ttf
   @param f file name of font to use
   @return the flappyFont
   */
   private static Font getFlappyFont(String f)
   {
      Font font = null;
      
      try
      {
         Font flappyFont = Font.createFont(Font.TRUETYPE_FONT, 
            new File(f)).deriveFont(12f);
         GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
         ge.registerFont(flappyFont);
         font = flappyFont;
      }
      catch(IOException e)
      {
         font = new Font("serif", Font.PLAIN, 24);
      }
      catch(FontFormatException e)
      {
         font = new Font("serif", Font.PLAIN, 24);
      }
      
      return font;
   }
   
   /**
   private Thread class continuously 
   repaints the game screen until the Fish
   touches the ground or a seaweed strand
   */
   private class UpdateGS extends Thread
   {
      /**
      checks if fish has hit strands or bottom of screen
      and moves the seaweed strands
      */
      public void run()
      {
         boolean stop = true;
         
         while(stop)
         {
            if(checkCollision(myFish, myPair1) == true 
               || checkCollision(myFish, myPair2) == true)
            {
               myApp.switchScreens("end");
               stop = false;
            }
            else if(myFish.getYCoord() == SCREEN_LENGTH)
            {
               myApp.switchScreens("end");
               stop = false;
            }
            
            myPair1.move();
            myPair2.move();
                  
            try
            {
               sleep(1);
            }
            catch(InterruptedException ie)
            {
            }
         }
      }
   }
   
   /**
   executes fish downward falling motion
   */
   private class FishFall extends TimerTask
   {  
      /**
      run method makes fish fall continously
      */
      public void run()
      { 
         myFish.fall(); 
      }
   }
      
   /**
   ActionListener for the start button
   begins Thread motion and then makes button disappear
   */
   private class StartButtonListener implements ActionListener
   {
      /**
      begins Thread motion and then makes button disappear
      @param e ActionEvent
      */
      public void actionPerformed(ActionEvent e)
      {
         String ac = e.getActionCommand();
      
         if(ac.equals("sb"))
         {
            System.out.println("** START Button pressed");
            myStartButton.setVisible(false);
            begin();
         }
      }
   }
   
   /**
   MouseListener for the 'START' button
   begins Thread motion
   */
   private class MouseClicker implements MouseListener
   {
      /**
      allows user to click to fly fish
      @param e MouseEvent
      */
      public void mousePressed(MouseEvent e)
      {
         System.out.println("** TAP");
         myFish.swimTap();
         GameScreen.this.requestFocusInWindow();
      }
      
      /** 
      unused
      @param e MouseEvent
      */
      public void mouseReleased(MouseEvent e)
      {
      }
      
      /** 
      unused
      @param e MouseEvent
      */
      public void mouseClicked(MouseEvent e)
      {
      }
      
      /** 
      unused
      @param e MouseEvent
      */
      public void mouseEntered(MouseEvent e)
      {
      }
      
      /** 
      unused
      @param e MouseEvent
      */
      public void mouseExited(MouseEvent e)
      {
      }
   }
   
   /**
   KeyListener for the space bar in gameplay
   */
   private class SpaceListener implements KeyListener
   {
      /**
      when user presses space bar, fish moves upwards
      @param e KeyEvent
      */
      public void keyPressed(KeyEvent e)
      {
         int code = e.getKeyCode();
         
         if(code == KeyEvent.VK_SPACE)
         {
            myFish.swimTap();
            GameScreen.this.requestFocusInWindow();
         }
      }
      
      /** 
      unused
      @param e KeyEvent
      */
      public void keyReleased(KeyEvent e)
      {
      
      }
      
      /** 
      unused
      @param e KeyEvent
      */
      public void keyTyped(KeyEvent e)
      {
      
      } 
   }
      
}