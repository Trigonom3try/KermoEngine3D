package Kermo_Dev_1;

import java.awt.Color;

public class Triangle
    {
        Point P0; Point P1; Point P2;
        Vector normal;
        Color color;
        
        Triangle(Point p0, Point p1, Point p2, Color c)
        {
            P0 = p0; P1 = p1; P2 = p2;  color = c;
            
            Vector side1 = new Vector(P0, P1);
            Vector side2 = new Vector(P0, P2);
            normal = Methods.crossProduct(side1, side2);
            normal.changeMagnitude(1);
        }
        
        
    }