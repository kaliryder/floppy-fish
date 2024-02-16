import java.awt.*;
import javax.swing.*;

/**
models the game screen of Floppy Fish
@author Kali Ryder
@version 2019/03/04
*/
public class GameScreen extends JPanel
{
   private Fish myFish;
   
   private Seaweed myPair;
   
   public GameScreen()
   {
      myFish = new Fish();
      myPair = new Seaweed();
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      myFish.drawFish(g2);
      myPair.drawPair(g2);
   }
}