

package Kermo_Dev_0;

import java.util.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;   
import javax.swing.event.*;

public class Rodriguez 
{
    static enum AXIS { Xpos, Ypos, Zpos, Xneg, Yneg, Zneg } ;
    static Vector VictorTheVector = new Vector(AXIS.Xpos);      
    static Vector OlindeRodriguez = new Vector(AXIS.Ypos);
    
    static ViewFrame XYframe;
    static ViewFrame XZframe;
    static ViewFrame ZYframe;
    
    static Rodtator rodtator;
    
    public static void main(String [] args)
    {
        XYframe = new ViewFrame("XY Plane", 0);
        XZframe = new ViewFrame("XZ Plane", 1);
        ZYframe = new ViewFrame("ZY Plane", 2);
        rodtator = new Rodtator();
    }
    
    
    static class ViewFrame extends JFrame
    {
        int XYZ;    // XYZ is an enumerable value that can be 0, 1, or 2 representing XY, XZ, or ZY plane respectively
        ViewPanel vp = new ViewPanel();
        ButtonPanel bp = new ButtonPanel(); // I actually attach the rotator buttons directly onto each ViewFrame in this program
        
        ViewFrame(String title, int xyz)
        {
            XYZ = xyz;
            setSize(500,550);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle(title);
            add(vp);
            add(bp, BorderLayout.SOUTH);
            setVisible(true);
        }
        
        
        class ViewPanel extends JPanel
        { 
            ViewPanel()
            {
                setPreferredSize(new Dimension(500,500));
            }
            public void paintComponent(Graphics g)
            {
                g.setColor(Color.white);    
                g.fillRect(0, 0, 500, 500);         // this is the background
                g.setColor(Color.black);
                g.drawLine(0, 250, 500, 250);       // this is the X axis
                g.drawLine(250, 0, 250, 500);       // this the Y axis
            
                int VictorX = toInt ( VictorTheVector.Xcomponent );     // these are the integer values of the vector components
                int VictorY = toInt ( VictorTheVector.Ycomponent );
                int VictorZ = toInt ( VictorTheVector.Zcomponent );
                
                int RodyX = toInt ( OlindeRodriguez.Xcomponent );     // these are the integer values of the vector components
                int RodyY = toInt ( OlindeRodriguez.Ycomponent );
                int RodyZ = toInt ( OlindeRodriguez.Zcomponent );
                
                g.setColor(Color.red);
                
                if (XYZ == 0) 
                {
                    g.drawLine(250, 250, 250 + VictorX, 250 + VictorY);
                    g.setColor(Color.blue);
                    g.drawLine(250, 250, 250 + RodyX, 250 + RodyY);
                }      
                else if (XYZ == 1) 
                {
                    g.drawLine(250, 250, 250 + VictorX, 250 + VictorZ);
                    g.setColor(Color.blue);
                    g.drawLine(250, 250, 250 + RodyX, 250 + RodyY);
                }  
                else
                {
                    g.drawLine(250,250, 250 + VictorZ, 250 + VictorY);
                    g.setColor(Color.blue);
                    g.drawLine(250, 250, 250 + RodyX, 250 + RodyY);
                }                 
            }   
        }
    
        class ButtonPanel extends JPanel    // the ButtonPanel and listener classes are inner classes of the ViewFrame in this program
        {
            JButton Left = new JButton("<");
            JButton Right = new JButton(">");
        
            ButtonPanel()
            {
                Left.addActionListener(new ButtonListener());
                Right.addActionListener(new ButtonListener());
                add(Left);
                add(Right);
            }
        }
        class ButtonListener implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                char c = ((JButton)e.getSource()).getText().charAt(0);
                double rotation;
                if (c == '<') rotation = -Math.PI / 20;
                else rotation = Math.PI / 20;
            
                if (XYZ == 0) VictorTheVector.RotateXY(rotation);
                else if (XYZ == 1) VictorTheVector.RotateXZ(rotation);
                else  VictorTheVector.RotateZY(rotation);
                
            //    System.out.println(VictorTheVector.toString());
                
                XYframe.vp.repaint(); 
                XZframe.vp.repaint();
                ZYframe.vp.repaint();
            }
        }
    }

    static class Rodtator extends JFrame
    {
        JButton left = new JButton("<");
        JButton right = new JButton(">");
        
        Rodtator()
        {
            setTitle("Rodtat0r 9000");
            setSize(100,75);
            setResizable(false);
            left.addActionListener(new ButtonListener());
            right.addActionListener(new ButtonListener());
            add(left, BorderLayout.WEST);
            add(right, BorderLayout.EAST);
            setVisible(true);
        }
        
        class ButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                char c = ((JButton)e.getSource()).getText().charAt(0);
                if (c == '<') OlindeRodriguez.Rotate(VictorTheVector, -Math.PI/20);
                else OlindeRodriguez.Rotate(VictorTheVector, Math.PI/20);
                
                XYframe.vp.repaint(); 
                XZframe.vp.repaint();
                ZYframe.vp.repaint();
            }
        }
    }
    
    
    
    static class Vector
    {
        double Xcomponent;
        double Ycomponent;
        double Zcomponent;
        double magnitude;
        
        Vector(double Xcomp, double Ycomp, double Zcomp)
        {
            Xcomponent = Xcomp; Ycomponent = Ycomp; Zcomponent = Zcomp;
        }
        Vector(Vector v)
        {
            Xcomponent = v.Xcomponent; Ycomponent = v.Ycomponent; Zcomponent = v.Zcomponent;
            magnitude = v.magnitude;
        }
        
        Vector(AXIS axis)   // we still don't need any other constructors for the Vector class yet
        {
            magnitude = 1;
            switch (axis) 
            {
                case Xpos : { Xcomponent = 1; Ycomponent = 0; Zcomponent = 0; break; } 
                case Xneg : { Xcomponent = -1; Ycomponent = 0; Zcomponent = 0; break; }
                case Ypos : { Xcomponent = 0; Ycomponent = 1; Zcomponent = 0; break; }
                case Yneg : { Xcomponent = 0; Ycomponent = -1; Zcomponent = 0; break; }
                case Zpos : { Xcomponent = 0; Ycomponent = 0; Zcomponent = 1; break; }
                case Zneg : { Xcomponent = 0; Ycomponent = 0; Zcomponent = -1; break; }
                default : break;
            }
        }
        
        
        void RotateXY(double rotation)
        {
            double Xtemp = Xcomponent;
            Xcomponent = Xcomponent*Math.cos(rotation) - Ycomponent*Math.sin(rotation);
            Ycomponent = Xtemp*Math.sin(rotation) + Ycomponent*Math.cos(rotation);
            
            double newMag = distance(Xcomponent, Ycomponent, Zcomponent);
            Xcomponent = Xcomponent * (magnitude/newMag);
            Ycomponent = Ycomponent * (magnitude/newMag);
            
            System.out.println("Rotate XY called");
            System.out.println("Victor : " + VictorTheVector.toString());
        }
        void RotateXZ(double rotation)
        {
            double Xtemp = Xcomponent;
            Xcomponent = Xcomponent*Math.cos(rotation) - Zcomponent*Math.sin(rotation);
            Zcomponent = Xtemp*Math.sin(rotation) + Zcomponent*Math.cos(rotation);
            
            double newMag = distance(Xcomponent, Ycomponent, Zcomponent);
            Xcomponent = Xcomponent * (magnitude/newMag);
            Zcomponent = Zcomponent * (magnitude/newMag);
            System.out.println("Rotate XZ called");
            System.out.println("Victor : " + VictorTheVector.toString());
        }
        void RotateZY(double rotation)
        {
            double Ztemp = Zcomponent;
            Zcomponent = Zcomponent*Math.cos(rotation) - Ycomponent*Math.sin(rotation);
            Ycomponent = Ztemp*Math.sin(rotation) + Ycomponent*Math.cos(rotation);
            
            double newMag = distance(Xcomponent, Ycomponent, Zcomponent);
            Zcomponent = Zcomponent * (magnitude/newMag);
            Ycomponent = Ycomponent * (magnitude/newMag);
            
            System.out.println("Rotate ZY called");
            System.out.println("Victor : " + VictorTheVector.toString());
        }
        void Rotate(Vector v, double rotation)
        {
       //     k.changeMagnitude(1);   
            Vector k = new Vector(v);   // otherwise the passed vector would get changed outside the method
            Vector cross = crossProduct(this, k);
            double dot = dotProduct(this, k);
            
            cross.scale(Math.sin(rotation));
            k.scale( dot * (1 - Math.cos(rotation)) );
            
            scale(Math.cos(rotation));
            add(cross);
            add(k);
            System.out.println("Rotate called");
            System.out.println("Rodriguez : " + OlindeRodriguez.toString());
            System.out.println("Victor : " + VictorTheVector.toString());
        }
        void add(Vector v)
        {
            Xcomponent += v.Xcomponent;
            Ycomponent += v.Ycomponent;
            Zcomponent += v.Zcomponent;
            magnitude = distance(Xcomponent, Ycomponent, Zcomponent);
        }
        void scale(double scalar)
        {
            Xcomponent = Xcomponent * scalar;
            Ycomponent = Ycomponent * scalar;
            Zcomponent = Zcomponent * scalar;
            magnitude = magnitude * scalar;     // matheatically correct
        }
     /*   void changeMagnitude(double newMag)
        {
            Xcomponent = Xcomponent * (newMag/magnitude);
            Ycomponent = Ycomponent * (newMag/magnitude);
            Zcomponent = Zcomponent * (newMag/magnitude);
            magnitude = newMag;
        }   */
        static Vector sum (Vector v1, Vector v2)
        {
            double Xcomp = v1.Xcomponent + v2.Xcomponent;
            double Ycomp = v1.Ycomponent + v2.Ycomponent;
            double Zcomp = v1.Zcomponent + v2.Zcomponent;
            return new Vector(Xcomp, Ycomp, Zcomp);
        }
        static Vector scalarMultiple (Vector v1, double scalar)
        {
            double Xcomp = v1.Xcomponent*scalar;
            double Ycomp = v1.Ycomponent*scalar;
            double Zcomp = v1.Zcomponent*scalar;
            return new Vector(Xcomp, Ycomp, Zcomp);
        }
        static double dotProduct (Vector v1, Vector v2)
        {
            return v1.Xcomponent*v2.Xcomponent + v1.Ycomponent*v2.Ycomponent + v1.Zcomponent*v2.Zcomponent;
        }
        static Vector crossProduct (Vector v1, Vector v2)
        {
            double Xcomp = v1.Ycomponent*v2.Zcomponent - v1.Zcomponent*v2.Ycomponent;
            double Ycomp = v1.Zcomponent*v2.Xcomponent - v1.Xcomponent*v2.Zcomponent;
            double Zcomp = v1.Xcomponent*v2.Ycomponent - v1.Ycomponent*v2.Xcomponent;
            return new Vector (Xcomp, Ycomp, Zcomp);
        }
        
        public String toString()
        {
            String s = "Printing Vector ...";
            s += "\n Xcomponent : " + Xcomponent;
            s += "\n Ycomponent : " + Ycomponent;
            s += "\n Zcomponent : " + Zcomponent;
            s += "\n Magnitude : " + magnitude;
            s += "\n";
            return s;
        }
    }
    
    
    
    static double distance(double deltaX, double deltaY, double deltaZ) 
    {
        return Math.sqrt( (deltaX*deltaX) + (deltaY*deltaY) + (deltaZ*deltaZ) );    
    }
    
    static double toDouble(int a)
    {
        return ( (double) a ) / 100;
    }
    static int toInt (double d)
    {
        return (int)( d * 100 );
    }
}
