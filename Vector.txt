package Kermo_Dev_1;

public class Vector
    {
        public static enum AXIS { Xpos, Xneg, Ypos, Yneg, Zpos, Zneg }
        double Xcomponent;
        double Ycomponent;
        double Zcomponent;
        double magnitude;
        
        Vector(double Xcomp, double Ycomp, double Zcomp)       // this code is practically unnecessary
        {
            Xcomponent = Xcomp; Ycomponent = Ycomp; Zcomponent = Zcomp;
            magnitude = Methods.distance(Xcomponent, Ycomponent, Zcomponent);
        }
        Vector(Point p)
        {
            Xcomponent = Methods.toDouble( p.X );
            Ycomponent = Methods.toDouble( p.Y );
            Zcomponent = Methods.toDouble( p.Z );
            magnitude = Methods.distance(Xcomponent, Ycomponent, Zcomponent);
        }
        Vector(Point p0, Point p1)
        {
            Xcomponent = Methods.toDouble( p1.X - p0.X );
            Ycomponent = Methods.toDouble( p1.Y - p0.Y );
            Zcomponent = Methods.toDouble( p1.Z - p0.Z );
            magnitude = Methods.distance(Xcomponent, Ycomponent, Zcomponent);
        }
        Vector(Vector v)
        {
            Xcomponent = v.Xcomponent; Ycomponent = v.Ycomponent; Zcomponent = v.Zcomponent;
            magnitude = v.magnitude;
        }
        Vector(AXIS axis)
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
        void rotate(Vector v, double rotation)
        {
            Vector k = new Vector(v);   // otherwise the passed vector would get changed outside the method
            Vector cross = Methods.crossProduct(this, k);
            double dot = Methods.dotProduct(this, k);
            
            cross.scale(Math.sin(rotation));
            k.scale( dot * (1 - Math.cos(rotation)) );
            
            scale(Math.cos(rotation));
            add(cross);
            add(k);
        }
        
        void rotateXY(double rotation)
        {
            double Xtemp = Xcomponent;
            Xcomponent = Xcomponent*Math.cos(rotation) - Ycomponent*Math.sin(rotation);
            Ycomponent = Xtemp*Math.sin(rotation) + Ycomponent*Math.cos(rotation);
            
            double newMag = Methods.distance(Xcomponent, Ycomponent, Zcomponent);
            Xcomponent = Xcomponent * (magnitude/newMag);
            Ycomponent = Ycomponent * (magnitude/newMag);
        }
        
        
        void changeMagnitude(double newMag)
        {
            Xcomponent = Xcomponent * (newMag/magnitude);
            Ycomponent = Ycomponent * (newMag/magnitude);
            Zcomponent = Zcomponent * (newMag/magnitude);
            magnitude = newMag;     // should be equal to distance(X,Y,Z) but without the floating point nonsense
        }
        
        void add(Vector v)
        {
            Xcomponent += v.Xcomponent;
            Ycomponent += v.Ycomponent;
            Zcomponent += v.Zcomponent;
            magnitude = Methods.distance(Xcomponent, Ycomponent, Zcomponent);
        }
        void scale(double scalar)
        {
            Xcomponent = Xcomponent * scalar;
            Ycomponent = Ycomponent * scalar;
            Zcomponent = Zcomponent * scalar;
            magnitude = magnitude * scalar;     // matheatically correct
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