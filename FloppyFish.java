import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.io.*;
import javax.imageio.*;

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
   
   /** break size between seaweed strands */
   public static final int BREAK_SIZE = 180;
   
   /** width of seaweed strands */
   public static final double STRAND_WIDTH = (SCREEN_WIDTH * 0.15);
   
   /** how far strands move every update */
   public static final double STRAND_MOVE_AMT = 0.65;
   
   /** height of fish movement upon tap */
   public static final double SWIM_AMT = 55.0;
   
   /** lowest possible top of break */
   public static final int BREAK_LOWIDX = ((SCREEN_LENGTH / 4) * 3) - BREAK_SIZE;
   
   /** highest possible top of break */
   public static final int BREAK_HIGHIDX = SCREEN_LENGTH / 4;
   
   /** milliseconds between fish timer updates */
   public static final double FALL_SPEED = 45;
   
   /** milliseconds before fish timer begins updating */
   public static final double FALL_DELAY = 70;
   
   /** x-axis position of fish stays constant in the center */
   public static final double FISH_XCOORD = (double) SCREEN_WIDTH / 2;
   
   /** add every update while fish falls */
   public static final double FISH_ACCELERATION = 1.0;
   
   /** original velocity when screen is tapped */
   public static final double FISH_OG_VELOCITY = 0.0;
   
   /** CardLayout main screen constant */
   public static final String MAIN = "main";
   
   /** CardLayout game screen constant */
   public static final String GAME = "game";
   
   /** CardLayout instructions screen constant */
   public static final String INS = "ins";
   
   /** CardLayout pick fish screen constant */
   public static final String PICK = "pick";
   
   /** CardLayout end screen constant */
   public static final String END = "end";
         
   /** the window of the app */
   private JFrame myWindow;
   
   /** the main screen of the app */
   private MainScreen myMainScreen;
   
   /** the screen for user to pick fish */
   private PickFishScreen myPFScreen;
   
   /** the instructions screen of the app */
   private InstructionsScreen myInsScreen;
   
   /** the game screen of the app */
   private GameScreen myGameScreen;
   
   /** the ending screen of the app */
   private EndingScreen myEndingScreen;
   
   /** CardLayout JPanel */
   private JPanel myContainer;
   
   /** background image */
   private BufferedImage myBGPic;
   
   /** strand image */
   private BufferedImage myStrandPic;
   
   /** desired fish image, defaults to first fish */
   private BufferedImage myFishPic;
   
   /** first fish image */
   private BufferedImage myFishPic1;
   
   /** second fish image */
   private BufferedImage myFishPic2;
   
   /** third fish image */
   private BufferedImage myFishPic3;
   
   /** instructions image */
   private BufferedImage myInsPic;
   
   /** height of fish pic */
   private double myFishHeight;
   
   /** width of fish pic */
   private double myFishWidth;
   
   /** user score */
   private int myScore;
   
   /** user high score */
   private int myHighScore;
   
   /** 
   constructor 
   */
   public FloppyFish()
   {
      myScore = 0;
      myHighScore = 0;
      
      setUpPics();
      myFishPic = myFishPic1;
   }
   
   /**
   reads object pictures
   */
   public void setUpPics()
   {
      try
      {
         InputStream is1 = getClass().getResourceAsStream("finalbg.jpg");
         myBGPic = ImageIO.read(is1);
         
         InputStream is2 = getClass().getResourceAsStream("finalstrand.JPG");
         myStrandPic = ImageIO.read(is2); 
         
         InputStream is3 = getClass().getResourceAsStream("linda1.PNG");
         myFishPic1 = ImageIO.read(is3);
         
         InputStream is4 = getClass().getResourceAsStream("linda2.PNG");
         myFishPic2 = ImageIO.read(is4);
         
         InputStream is5 = getClass().getResourceAsStream("linda3.PNG");
         myFishPic3 = ImageIO.read(is5);
         
         InputStream is6 = getClass().getResourceAsStream("instructions.PNG");
         myInsPic = ImageIO.read(is6);
         
         myFishHeight = (double) myFishPic1.getHeight();
         myFishWidth = (double) myFishPic1.getWidth();
      }
      catch(IOException ioe)
      {
      }
   }
   
   /**
   returns background buffered image
   @return background buffered image
   */
   public BufferedImage getBGPic()
   {
      return myBGPic;
   }
   
   /**
   returns strand buffered image
   @return strand buffered image
   */
   public BufferedImage getStrandPic()
   {
      return myStrandPic;
   }
   
   /**
   updates fish picture according to user preference
   @param fish the string of the fish to be used
   */
   public void setFishPic(String fish)
   {
      if(fish.equals("fish1"))
      {
        myFishPic = myFishPic1;
      }
      else if(fish.equals("fish2"))
      {
        myFishPic = myFishPic2;
      }
      else if(fish.equals("fish3"))
      {
        myFishPic = myFishPic3;
      }
   }
   
   /**
   returns desired or default fish buffered image
   @return desired or default fish buffered image
   */
   public BufferedImage getFishPic()
   {
      return myFishPic;
   }
   
   /**
   returns first fish buffered image
   @return first fish buffered image
   */
   public BufferedImage get1FishPic()
   {
      return myFishPic1;
   }
   
   /**
   returns second fish buffered image
   @return second fish buffered image
   */
   public BufferedImage get2FishPic()
   {
      return myFishPic2;
   }
   
   /**
   returns third fish buffered image
   @return third fish buffered image
   */
   public BufferedImage get3FishPic()
   {
      return myFishPic3;
   }
   
   /**
   returns instructions buffered image
   @return instructions buffered image
   */
   public BufferedImage getInsPic()
   {
      return myInsPic;
   }
   
   /**
   returns fish height
   @return fish height
   */
   public double getFishHeight()
   {
      return myFishHeight;
   }
   
   /**
   returns fish width
   @return fish width
   */
   public double getFishWidth()
   {
      return myFishWidth;
   }
   
   /**
   returns current user score
   @return score
   */
   public int getScore()
   {
      return myScore;
   }
   
   /**
   returns user high score
   @return high score
   */
   public int getHighScore()
   {
      return myHighScore;
   }
   
   /**
   increases score by one point
   */
   public void addPoint()
   {
      myScore = myScore + 1;
   }
   
   /**
   checks if current score is high score,
   updates high score if so
   */
   public void checkForHS()
   {
      if(myScore > myHighScore)
      {
         myHighScore = myScore;
      }
   }
   
   /**
   switches screen of app and performs necessary actions
   @param screen String constant of screen to switch to
   */
   public void switchScreens(String screen)
   {
      if(screen.equals("main"))
      {
         myMainScreen = new MainScreen(this);
         myContainer.add(myMainScreen, MAIN);
      }
      else if(screen.equals("pick"))
      {
         myPFScreen = new PickFishScreen(this);
         myContainer.add(myPFScreen, PICK);
      }
      else if(screen.equals("ins"))
      {
         myInsScreen = new InstructionsScreen(this);
         myContainer.add(myInsScreen, INS);
      }
      else if(screen.equals("game"))
      {
         myScore = 0;
         myGameScreen = new GameScreen(this);
         myContainer.add(myGameScreen, GAME);
      }
      else if(screen.equals("end"))
      {
         checkForHS();
         myEndingScreen = new EndingScreen(this);
         myContainer.add(myEndingScreen, END);
         myEndingScreen.setUpScreen();
      }
                 
      CardLayout layout = (CardLayout) myContainer.getLayout();    
      layout.show(myContainer, screen);
   }
   
   /**
   creates JFrame, screen objects, and sets up card layout
   */
   private void setUpFrame()
   {
      myWindow = new JFrame();
      myWindow.setSize(SCREEN_WIDTH, SCREEN_LENGTH);
      
      myMainScreen = new MainScreen(this);
      myPFScreen = new PickFishScreen(this);
      myInsScreen = new InstructionsScreen(this);
      myGameScreen = new GameScreen(this);
      myEndingScreen = new EndingScreen(this);
      
      myContainer = new JPanel(new CardLayout());
      myContainer.add(myMainScreen, MAIN);
      myContainer.add(myPFScreen, PICK);
      myContainer.add(myInsScreen, INS);
      myContainer.add(myGameScreen, GAME);
      myContainer.add(myEndingScreen, END);
      
      myWindow.add(myContainer);
      myWindow.setTitle("FloppyFish"); 
      myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myWindow.setVisible(true);
      myWindow.setResizable(false);
   }
   
   public static void main(String[] args)
   {
      FloppyFish app = new FloppyFish();
      app.setUpFrame();
   }
}