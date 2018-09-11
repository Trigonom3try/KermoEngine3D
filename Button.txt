
package Kermo_Dev_1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Button extends JButton
{
    String tag;
    
    public Button(String label, String t)
    {
        tag = t;
        setText(label);
        addActionListener(new ButtonListener());
    }
}