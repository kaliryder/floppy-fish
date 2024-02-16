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
import java.awt.image.*;
import javax.imageio.*;

/**
models the screen for user to pick fish
@author Kali Ryder
@version 2019/03/04
*/
public class PickFishScreen extends JPanel
{
   /** FloppyFish object */
   private FloppyFish myApp;
   
   /** first fish buffered image */
   private BufferedImage myFish1;
   
   /** second fish buffered image */
   private BufferedImage myFish2;
   
   /** third fish buffered image */
   private BufferedImage myFish3;
   
   /** first fish JButton */
   private JButton fish1button;
   
   /** second fish JButton */
   private JButton fish2button;
   
   /** third fish JButton */
   private JButton fish3button;
   
   /**
   constructor
   @param app FloppyFish app
   */
   public PickFishScreen(FloppyFish app)
   {
      myApp = app;
      
      myFish1 = myApp.get1FishPic();
      myFish2 = myApp.get2FishPic();
      myFish3 = myApp.get3FishPic();
      
      setUpScreen();
   }
   
   /**
   uses GridBagLayout to set up the JPanel 
   that occupies the Fish Pick Screen
   */
   public void setUpScreen()
   {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      
      JLabel pickFish = new JLabel("chooseFish");
      pickFish.setFont(getFlappyFont("Flappy-Bird.ttf").deriveFont(70f));
      gbc.gridx = 1;
      gbc.gridy = 1;
      gbc.gridwidth = 3;
      gbc.weightx = 1;
      gbc.weighty = 0.4;
      gbc.fill = GridBagConstraints.CENTER;
      add(pickFish, gbc);
      
      ImageIcon fish1 = new ImageIcon("linda1.PNG");
      fish1button = new JButton(fish1);
      fish1button.setFocusPainted(false);
      fish1button.setPreferredSize(new Dimension(200, 80));
      gbc.gridx = 2;
      gbc.gridy = 3;
      gbc.gridwidth = 1;
      gbc.weightx = 1;
      gbc.weighty = 0.2;
      gbc.fill = GridBagConstraints.CENTER;
      fish1button.addActionListener(new FishListener());
      fish1button.setActionCommand("fish1");
      add(fish1button, gbc);
      
      ImageIcon fish2 = new ImageIcon("linda2.PNG");
      fish2button = new JButton(fish2);
      fish2button.setFocusPainted(false);
      fish2button.setPreferredSize(new Dimension(200, 80));
      gbc.gridx = 2;
      gbc.gridy = 5;
      gbc.gridwidth = 1;
      gbc.weightx = 1;
      gbc.weighty = 0.2;
      gbc.fill = GridBagConstraints.CENTER;
      fish2button.addActionListener(new FishListener());
      fish2button.setActionCommand("fish2");
      add(fish2button, gbc);
      
      ImageIcon fish3 = new ImageIcon("linda3.PNG");
      fish3button = new JButton(fish3);
      fish3button.setFocusPainted(false);
      fish3button.setPreferredSize(new Dimension(200, 80));
      gbc.gridx = 2;
      gbc.gridy = 7;
      gbc.gridwidth = 1;
      gbc.weightx = 1;
      gbc.weighty = 0.2;
      gbc.fill = GridBagConstraints.CENTER;
      fish3button.addActionListener(new FishListener());
      fish3button.setActionCommand("fish3");
      add(fish3button, gbc);
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
   
   /**
   ActionListener for the fish buttons
   */
   private class FishListener implements ActionListener
   {
      /**
      controls fish selection
      @param e ActionEvent
      */
      public void actionPerformed(ActionEvent e)
      {
         String ac = e.getActionCommand();
         
         if(ac.equals("fish1"))
         {
           myApp.setFishPic("fish1");
           myApp.switchScreens("game");
         }
         else if(ac.equals("fish2"))
         {
           myApp.setFishPic("fish2");
           myApp.switchScreens("game");
         }
         else if(ac.equals("fish3"))
         {
           myApp.setFishPic("fish3");
           myApp.switchScreens("game");
         }
      }
   }
}