import java.awt.*;
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

/**
models the main screen of Floppy Fish
@author Kali Ryder
@version 2019/03/04
*/
public class MainScreen extends JPanel implements ActionListener
{
   /** FloppyFish object */
   private FloppyFish myApp;
   
   /** fish label */
   private JLabel myFish;
   
   /** play button */
   private JButton myPlayButton;
   
   /** pick fish button */
   private JButton myPickButton;
   
   /** instructions button */
   private JButton myInsButton;
   
   /** title label */
   private JLabel myTitle;
   
   /** high score box */
   private JLabel myHSBox;
   
   /** whole MainScreen panel */
   private JPanel wholeScreen;
   
   /** user high score */
   private int myHighScore;
   
   /**
   constructor
   @param app FloppyFish object
   */
   public MainScreen(FloppyFish app)
   {
      myApp = app;
      
      wholeScreen = new JPanel();
      add(wholeScreen);
      
      myHighScore = myApp.getHighScore();
      
      setUpWholeScreen();
   }
   
   /**
   uses GridBagLayout to set up the JPanel 
   that occupies the MainScreen
   */
   public void setUpWholeScreen()
   {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      
      JLabel gap1 = new JLabel("");
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 0.05;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap1, gbc);
      
      ImageIcon title = new ImageIcon("title.PNG");
      myTitle = new JLabel(title);
      myTitle.setForeground(Color.WHITE);
      gbc.gridx = 2;
      gbc.gridy = 1;
      gbc.gridwidth = 5;
      gbc.weightx = 1;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.CENTER;
      add(myTitle, gbc);
      
      JLabel gap2 = new JLabel("");
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 0.05;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap2, gbc);
      
      myHSBox = new JLabel("highScore: " + myHighScore);
      myHSBox.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(70f));
      gbc.gridx = 2;
      gbc.gridy = 3;
      gbc.gridwidth = 5;
      gbc.weightx = 1;
      gbc.weighty = 0.3;
      gbc.fill = GridBagConstraints.CENTER;
      add(myHSBox, gbc);
      
      JLabel gap3 = new JLabel("");
      gbc.gridx = 0;
      gbc.gridy = 4;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap3, gbc);
      
      ImageIcon fish = new ImageIcon("linda.PNG");
      myFish = new JLabel(fish);
      gbc.gridx = 3;
      gbc.gridy = 5;
      gbc.gridwidth = 3;
      gbc.weightx = 1;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.CENTER;
      add(myFish, gbc);
      
      JLabel gap4 = new JLabel("");
      gbc.gridx = 0;
      gbc.gridy = 6;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap4, gbc);
      
      myPlayButton = new JButton("play");
      myPlayButton.setForeground(Color.BLACK);
      myPlayButton.setFocusPainted(false);
      myPlayButton.setFont(getFlappyFont("FlappyBirdy.ttf").deriveFont(70f));
      gbc.gridx = 2;
      gbc.gridy = 7;
      gbc.gridwidth = 5;
      gbc.weightx = 1;
      gbc.weighty = 0.15;
      gbc.fill = GridBagConstraints.CENTER;
      myPlayButton.addActionListener(this);
      myPlayButton.setActionCommand("openGS");
      add(myPlayButton, gbc);
      
      JLabel gap5 = new JLabel("");
      gbc.gridx = 0;
      gbc.gridy = 8;
      gbc.gridwidth = 9;
      gbc.weightx = 1;
      gbc.weighty = 0.05;
      gbc.fill = GridBagConstraints.BOTH;
      add(gap5, gbc);
      
      myInsButton = new JButton("instructions");
      myInsButton.setForeground(Color.BLACK);
      myInsButton.setFocusPainted(false);
      myInsButton.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(50f));
      gbc.gridx = 1;
      gbc.gridy = 9;
      gbc.gridwidth = 3;
      gbc.weightx = 0.5;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.BOTH;
      myInsButton.addActionListener(this);
      myInsButton.setActionCommand("openIS");
      add(myInsButton, gbc);
      
      myPickButton = new JButton("chooseFish");
      myPickButton.setForeground(Color.BLACK);
      myPickButton.setFocusPainted(false);
      myPickButton.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(50f));
      gbc.gridx = 5;
      gbc.gridy = 9;
      gbc.gridwidth = 3;
      gbc.weightx = 0.5;
      gbc.weighty = 0.1;
      gbc.fill = GridBagConstraints.BOTH;
      myPickButton.addActionListener(this);
      myPickButton.setActionCommand("openPS");
      add(myPickButton, gbc);
   }

   /**
   actionPerformed method is from interface ActionListener
   @param e ActionEvent
   */
   public void actionPerformed(ActionEvent e)
   {
      String ac = e.getActionCommand();
      
      if(ac.equals("openIS"))
      {
         myApp.switchScreens("ins");
      }
      else if(ac.equals("openPS"))
      {
         myApp.switchScreens("pick");
      }
      else if(ac.equals("openGS"))
      {
         myApp.switchScreens("game");
      }
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
   private method returns the flappyFonts imported
   from the internet in font files
   @param f name of font file to use
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
