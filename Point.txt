
package Kermo_Dev_1;

public class Point
    {
        int X; int Y; int Z;
        Triangle triangle;
        Point(int x, int y, int z)
        {
            X = x; Y = y; Z = z;
        }
        Point(Vector v)
        {
            X = Methods.toInt( v.Xcomponent );
            Y = Methods.toInt( v.Ycomponent );
            Z = Methods.toInt( v.Zcomponent );
        }      
        void setTriangle(Triangle t)
        {
            triangle = t;
        }
        void add(int x, int y, int z)
        {
            X += x; Y += y; Z += z;
        }
        void add(Point p)
        {
            X += p.X; Y += p.Y; Z += p.Z;
        }   
        public String toString()
        {
            String s = " X : " + X;
            s += "\n Y : " + Y;
            s += "\n Z : " + Z;
            return s;
        }
    }