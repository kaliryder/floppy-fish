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
models the instructions screen
@author Kali Ryder
@version 2019/03/04
*/
public class InstructionsScreen extends JPanel
{
   /** FloppyFish object */
   private FloppyFish myApp;
   
   /** menu JButton */
   private JButton menuButton;
      
   /**
   constructor
   @param app FloppyFish app
   */
   public InstructionsScreen(FloppyFish app)
   {
      myApp = app; 
      menuButton = new JButton("mainMenu");
      menuButton.setFont(getFlappyFont("FlappyBirdy.ttf").deriveFont(70f)); 
      menuButton.setActionCommand("m"); 
      menuButton.addActionListener(new MenuButtonListener());
      add(menuButton);
   }
   
   /**
   paint component method paints background of screen
   @param g Graphics object
   */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(myApp.getBGPic(), 0, 0, null);
      g2.drawImage(myApp.getInsPic(), 0, 0, null);
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
   ActionListener for the main menu button
   */
   private class MenuButtonListener implements ActionListener
   {
      /**
      takes user to main screen
      @param e ActionEvent
      */
      public void actionPerformed(ActionEvent e)
      {
         String ac = e.getActionCommand();
      
         if(ac.equals("m"))
         {
            myApp.switchScreens("main");
         }
      }
   }
}