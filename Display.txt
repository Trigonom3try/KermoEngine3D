package Kermo_Dev_1;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel   // this panel shall serve as a raster display
{
    
    public final int FrameHeight = 100;
    public final int FrameWidth = 100;
    public final int PixelHeight = 400;
    public final int PixelWidth = 400;
    private final int colorDistanceY = PixelHeight/FrameHeight;
    private final int colorDistanceX = PixelWidth/FrameWidth;
    public final Color defaultColor = Color.white;
    
    public Color [][] Frame = new Color[FrameHeight][FrameWidth];
    
    public void paintComponent(Graphics g)
    {
        for (int y = 0; y < FrameHeight; ++y)       // our raster display logic
            for (int x = 0; x < FrameWidth; ++x)
            {
                g.setColor(Frame[y][x]);
                g.fillRect(x*colorDistanceX, y*colorDistanceY, colorDistanceX, colorDistanceY);
            }
    }
}