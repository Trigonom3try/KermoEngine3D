
package Kermo_Dev_1;

import java.awt.event.*;
import javax.swing.event.*;

public class ButtonListener implements ActionListener
{
    Vector camXnormal = Kermo.camera.Xnormal;       // keeping direct references to these makes the code cleaner
    Vector camYnormal = Kermo.camera.Ynormal;
    Vector camZnormal = Kermo.camera.Znormal;
    final double rotation = Math.PI / 20;
    
    public void actionPerformed(ActionEvent e)
    {
        String tag = ((Button)e.getSource()).tag;
        
        if (tag.equals("camRotateUp"))
        {
            Kermo.camera.rotateX(-rotation);
        }
        if (tag.equals("camRotateDown"))
        {
            Kermo.camera.rotateX(rotation);
        }
        if (tag.equals("camRotateLeft"))
        {
            Kermo.camera.rotateY(-rotation);
        }
        if (tag.equals("camRotateRight"))
        {
            Kermo.camera.rotateY(rotation);
        }
        
        if (tag.equals("camMoveForward"))
        {
            int x = Methods.toInt( camZnormal.Xcomponent );
            int y = Methods.toInt( camZnormal.Ycomponent );
            int z = Methods.toInt( camZnormal.Zcomponent );
            
            Kermo.camera.move(x, y, z);
        }
        if (tag.equals("camMoveBack"))
        {
            int x = -Methods.toInt( camZnormal.Xcomponent );
            int y = -Methods.toInt( camZnormal.Ycomponent );
            int z = -Methods.toInt( camZnormal.Zcomponent );
            
            Kermo.camera.move(x, y, z);
        }
        if (tag.equals("camMoveLeft"))
        {
            int x = -Methods.toInt( camXnormal.Xcomponent );
            int y = -Methods.toInt( camXnormal.Ycomponent );
            int z = -Methods.toInt( camXnormal.Zcomponent );
            
            Kermo.camera.move(x, y, z);
        }
        if (tag.equals("camMoveRight"))
        {
            int x = Methods.toInt( camXnormal.Xcomponent );
            int y = Methods.toInt( camXnormal.Ycomponent );
            int z = Methods.toInt( camXnormal.Zcomponent );
            
            Kermo.camera.move(x, y, z);
        }
        
        Kermo.camera.update();
        Kermo.display.repaint();
    }
}
