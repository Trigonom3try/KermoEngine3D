
                                            // Dedicated to Dizkarma aka "Kermo"
                                            // to RareFraction
                                            // and to Buckethead, who was my pair programmer


package Kermo_Dev_1;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Kermo 
{
    public static Display display = new Display();
    public static ArrayList<Triangle> environment = new ArrayList<Triangle>();
    public static Camera camera = new Camera();
    static int z = 200;
    public static void main(String [] args)
    {   
        environment.add(new Triangle(new Point(100,100,z), new Point(240,100,z), new Point(240,520,z), Color.gray) );
        environment.add(new Triangle(new Point(100,100,z), new Point(100,520,z), new Point(240,520,z), Color.gray) );
        environment.add(new Triangle(new Point(240,100,z), new Point(320,100,z), new Point(320,400,z), Color.gray) );
        environment.add(new Triangle(new Point(240,100,z), new Point(240,400,z), new Point(320,400,z), Color.gray) );
        environment.add(new Triangle(new Point(320,260,z), new Point(320,400,z), new Point(480,260,z), Color.gray) );
        environment.add(new Triangle(new Point(480,260,z), new Point(480,400,z), new Point(320,400,z), Color.gray) );
        environment.add(new Triangle(new Point(480,100,z), new Point(480,400,z), new Point(560,400,z), Color.gray) );
        environment.add(new Triangle(new Point(480,100,z), new Point(560,100,z), new Point(560,400,z), Color.gray) );
        environment.add(new Triangle(new Point(560,100,z), new Point(560,520,z), new Point(700,520,z), Color.gray) );
        environment.add(new Triangle(new Point(560,100,z), new Point(700,100,z), new Point(700,520,z), Color.gray) );
        environment.add(new Triangle(new Point(60,520,z), new Point(280,520,z), new Point(280,560,z), Color.gray) );
        environment.add(new Triangle(new Point(60,520,z), new Point(60,560,z), new Point(280,560,z), Color.gray) );
        camera.update();
        
        new EngineWindow();
        new CameraRotator();
        new CameraMover();
    }
    
    static class EngineWindow extends JFrame
    {
        public EngineWindow()
        {
            setSize(display.PixelWidth, display.PixelHeight);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(display);
            setVisible(true);
        }
    }
}
