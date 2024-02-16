import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.io.*;
import java.awt.GridLayout;

/**
models the ending screen of Floppy Fish
@author Kali Ryder
@version 2019/03/04
*/
public class EndingScreen extends JPanel implements ActionListener
{
   /** length of screen */
   public static final int SCREEN_LENGTH = FloppyFish.SCREEN_LENGTH;
   
   /** width of screen */
   public static final int SCREEN_WIDTH = FloppyFish.SCREEN_WIDTH;
   
   /** FloppyFish object */
   private FloppyFish myApp;
   
   /** whole screen */
   private JPanel myScreen;
   
   /** game over label */
   private JLabel gameOver;
   
   /** score label */
   private JLabel score;
   
   /** high score label */
   private JLabel highScore;
   
   /** play again button */
   private JButton playAgain;
   
   /** main menu button */
   private JButton mainMenu;
   
   /** user score */
   private int myScore;
   
   /** user high score */
   private int myHighScore;
   
   /** 
   constructor
   @param app FloppyFish object 
   */
   public EndingScreen(FloppyFish app)
   {
      myApp = app;

      myScreen = new JPanel();
      add(myScreen);
      
      myScore = myApp.getScore();
      myHighScore = myApp.getHighScore();
   }
   
   /**
   paint component method paints background of screen
   @param g Graphics object
   */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      g2.drawImage(myApp.getBGPic(), 0, 0, null);
   }
   
   /**
   uses GridBagLayout to set up the JPanel 
   that occupies the EndScreen
   */
   public void setUpScreen()
   {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      
      JLabel gap1 = new JLabel("\n");
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 9;
      gbc.weightx = 3;
      gbc.weighty = 0.5;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap1, gbc);
      
      gameOver = new JLabel("GAME OVER");
      gameOver.setFont(getFlappyFont("FlappyBirdy.ttf").deriveFont(200f));
      gbc.gridx = 2;
      gbc.gridy = 1;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 2;
      gbc.fill = GridBagConstraints.CENTER;
      add(gameOver, gbc);
      
      JLabel gap2 = new JLabel("\n");
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 9;
      gbc.weightx = 3;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap2, gbc);
      
      score = new JLabel("score: " + myScore);
      score.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(80f));
      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 1;
      gbc.fill = GridBagConstraints.CENTER;
      add(score, gbc);
      
      highScore = new JLabel("highScore: " + myHighScore);
      highScore.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(80f));
      gbc.gridx = 0;
      gbc.gridy = 4;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 1;
      gbc.fill = GridBagConstraints.CENTER;
      add(highScore, gbc);
      
      JLabel gap3 = new JLabel("\n");
      gbc.gridx = 0;
      gbc.gridy = 6;
      gbc.gridwidth = 9;
      gbc.gridheight = 1;
      gbc.weightx = 3;
      gbc.weighty = 1;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap3, gbc);
      
      playAgain = new JButton("playAgain");
      playAgain.setFont(getFlappyFont("FlappyBirdy.ttf").deriveFont(60f));
      gbc.gridx = 0;
      gbc.gridy = 7;
      gbc.gridwidth = 3;
      gbc.gridheight = 1;
      gbc.weightx = 0.5;
      gbc.weighty = 2;
      gbc.fill = GridBagConstraints.BOTH;
      playAgain.addActionListener(this);
      playAgain.setActionCommand("pa");
      add(playAgain, gbc);
     
      mainMenu = new JButton(" mainMenu ");
      mainMenu.setFont(getFlappyFont("FlappyBirdy.ttf").deriveFont(60f));
      gbc.gridx = 6;
      gbc.gridy = 7;
      gbc.gridwidth = 3;
      gbc.gridheight = 1;
      gbc.weightx = 0.5;
      gbc.weighty = 2;
      gbc.fill = GridBagConstraints.BOTH;
      mainMenu.addActionListener(this);
      mainMenu.setActionCommand("mm");
      add(mainMenu, gbc);
      
      JLabel gap4 = new JLabel("\n");
      gbc.gridx = 0;
      gbc.gridy = 8;
      gbc.gridwidth = 9;
      gbc.gridheight = 1;
      gbc.weightx = 3;
      gbc.weighty = 0.5;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap4, gbc);
   }
   
   /**
   actionPerformed method is from interface ActionListener
   @param e ActionEvent
   */
   public void actionPerformed(ActionEvent e)
   {
      String ac = e.getActionCommand();
      
      if(ac.equals("mm"))
      {
         myApp.switchScreens("main");
      }
      else if(ac.equals("pa"))
      {         
         myApp.switchScreens("game");
      }
   }
   
   /**
   private method returns the flappyFont imported
   from the internet in file FlappyBirdy.ttf
   @param f file name of font to be used
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
}