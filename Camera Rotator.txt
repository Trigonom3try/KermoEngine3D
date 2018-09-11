package Kermo_Dev_1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class CameraRotator extends JFrame
{
    Button rotateLeft = new Button("<", "camRotateLeft");
    Button rotateRight = new Button(">", "camRotateRight");
    Button rotateUp = new Button("^", "camRotateUp");
    Button rotateDown = new Button("v", "camRotateDown");
    
    public CameraRotator()
    {
        setSize(250,100);
        setResizable(false);
        setTitle("Camera Rotator");
        add(rotateLeft, BorderLayout.WEST);
        add(rotateRight, BorderLayout.EAST);
        add(rotateUp, BorderLayout.CENTER);
        add(rotateDown, BorderLayout.SOUTH);
        setVisible(true);
    }
}