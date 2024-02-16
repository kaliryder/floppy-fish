import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
models the main screen of Floppy Fish
@author Kali Ryder
@version 2019/03/04
*/
public class MainScreen extends JPanel implements ActionListener
{
   private Fish myFish;
   
   private JButton myPlayButton;
   
   private String myCommand;
   
   public MainScreen()
   {
      myFish = new Fish();
      
      myPlayButton = new JButton("PLAY");
      myPlayButton.setLocation(260, 400);
      myPlayButton.addActionListener(this);
      myPlayButton.setActionCommand("openGS");
      add(myPlayButton);
   }
   
   /**
   actionPerformed method is from interface ActionListener
   */
   public void actionPerformed(ActionEvent e)
   {
      String ac = e.getActionCommand();
      
      if(ac.equals("openGS"))
      {
         System.out.println("** Play button pressed");
         //open game screen
      }
   }
   
   public String getCommand()
   {
      return myCommand;
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      g2.drawString("Floppy Fish", 265, 200);
      
      myFish.drawFish(g2);
   }
}
