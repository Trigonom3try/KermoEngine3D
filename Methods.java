
package Kermo_Dev_1;

import java.awt.Color;

public class Methods 
{
    
    public static Color TraceRay(Ray r)
    {
        Point pointBuffer = null;
        
        for (Triangle t : Kermo.environment)
        {
            Point p = findIntersection(r, t);
            if (p == null) continue;
            if (withinVertices(p, t))
            {
                if (pointBuffer == null)
                {
                    pointBuffer = p;
                    pointBuffer.setTriangle(t);
                }
                else if (distance(p, Kermo.camera.origin) < distance(pointBuffer, Kermo.camera.origin)) 
                {
                    pointBuffer = p;
                    pointBuffer.setTriangle(t);
                }
            }
        }
        if (pointBuffer == null) return Kermo.display.defaultColor;
        return pointBuffer.triangle.color;
    }
    
    
    
    
    public static Point findIntersection(Ray r, Triangle tri)
    {
        Vector p0 = new Vector(tri.P0);     
        Vector r0 = new Vector(r.origin);   
        
        double numerator = dotProduct(tri.normal, p0) - dotProduct(tri.normal, r0);
        double denominator = dotProduct(tri.normal, r.slope);
        
        if (denominator == 0) return null;      
            
        double t = numerator / denominator;   
        
        if (t < 0) return null;             
        
        Vector v = Methods.scalarMultiple(r.slope, t);  
        Point intersection = new Point( sum(r0, v) );     
                                            
        return intersection;  
    }
    
    
    
    
    static boolean withinVertices(Point p, Triangle t) 
    {
        double Atotal = Area(t.P0, t.P1, t.P2);
        double A1 = Area(t.P0, t.P1, p);
        double A2 = Area(t.P0, p, t.P2);
        double A3 = Area(p, t.P1, t.P2);
            
        if ((A1 + A2 + A3) > Atotal - .001 && (A1 + A2 + A3) < Atotal + .001) return true;    
                                                // we need an inequality because of floating point imprecision 
                                                // and we don't want to do more needless multiplying by 100 and converting to int
        return false;
    }
    
    
    
    static double Area(Point p0, Point p1, Point p2)    // finds the area of a triangle given 3 vertices
    {
        double a = distance(p0, p1);
        double b = distance(p0, p2);
        double c = distance(p1, p2);
        double C = LawOfCosines(a, b, c);   // returns an angle measure in radians
        
        double height = a * Math.sin(C);
        
        return ( b * height ) / 2;          // the area of a triangle
    }
    
    static double LawOfCosines(double a, double b, double c)    // finds the C term angle of a triangle using the Law of Cosines
    {
        return Math.acos( ( a*a + b*b - c*c ) / (2*a*b) ) ;            
    }
    static double angleDifference(Vector v1, Vector v2)     // finds the angle between two vectors. Used only for debugging
    {
        return toDegrees( Math.acos( dotProduct(v1,v2) / (v1.magnitude*v2.magnitude) ) );
    }
        
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
    
    static double distance(Vector v1, Vector v2)    // unused method
    {
        return distance(v2.Xcomponent-v1.Xcomponent, v2.Ycomponent-v1.Ycomponent, v2.Xcomponent-v1.Xcomponent);
    }
    
    static double distance(double deltaX, double deltaY, double deltaZ) // finds a 3 dimensional distance from 3 deltas
    {
        return Math.sqrt( (deltaX*deltaX) + (deltaY*deltaY) + (deltaZ*deltaZ) );    // Pythagoras in 3 variables
    }
    
    static double distance(Point p1, Point p2)        // or from 2 preconstructed 3d Point objects (comes in handy in the code)
    {
        double x1 = toDouble(p1.X);       // all coordinates must be converted to double values for further computation
        double x2 = toDouble(p2.X);
        double y1 = toDouble(p1.Y);
        double y2 = toDouble(p2.Y);
        double z1 = toDouble(p1.Z);
        double z2 = toDouble(p2.Z);
        double deltaX = x2 - x1;    double deltaY = y2 - y1;    double deltaZ = z2 - z1;
        
        return distance (deltaX, deltaY, deltaZ);   // recursive call of an alternate distance method with different parameters
                                                    // mainly because we don't like repeatedly typing math stuff
    }   
    
    static double toDegrees(double r)       // used only for debugging
    {
        return (r * 180) / Math.PI;
    }
    
    static double toRadians(double d)
    {
        return (d * Math.PI) / 180;
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



        //  Life's do or die, and I'ma die makin classics        - Isaiah Rashad