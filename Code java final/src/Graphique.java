import java.awt.*;
import java.io.*;	
import javax.imageio.ImageIO;
import javax.swing.*;

public class Graphique extends JPanel {
	  public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("Fond.png"));
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }                
	  }               
	}

	


