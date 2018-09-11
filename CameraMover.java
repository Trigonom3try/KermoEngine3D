package Kermo_Dev_1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class CameraMover extends JFrame
{
    Button moveLeft = new Button("<", "camMoveLeft");
    Button moveRight = new Button(">", "camMoveRight");
    Button moveForward = new Button("^", "camMoveForward");
    Button moveBack = new Button("v", "camMoveBack");
    
    public CameraMover()
    {
        setSize(250,100);
        setResizable(false);
        setTitle("Camera Mover");
        add(moveLeft, BorderLayout.WEST);
        add(moveRight, BorderLayout.EAST);
        add(moveForward, BorderLayout.CENTER);
        add(moveBack, BorderLayout.SOUTH);
        setVisible(true);
    }
}