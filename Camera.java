package Kermo_Dev_1;

public class Camera      
   {
       public Point origin = new Point(0,0,0);          // we init the camera at the origin of the space
       int FrameHeight = Kermo.display.FrameHeight;
       int FrameWidth = Kermo.display.FrameWidth;
       public final int distanceScalar = 2;
       public final double Z = 1;
       
       Vector Xnormal = new Vector(Vector.AXIS.Xpos);
       Vector Ynormal = new Vector(Vector.AXIS.Ypos);
       Vector Znormal = new Vector(Vector.AXIS.Zpos);
       
       Camera() 
       {
           update();
       }
       void update()    
       {
           for (int y = -FrameHeight/2; y < FrameHeight/2; ++y)
               for (int x = -FrameWidth/2; x < FrameWidth/2; ++x)
               {
                   double X = Methods.toDouble(x * distanceScalar);     double Y = Methods.toDouble(y * distanceScalar);
                   
                   double Xcomp = X*Xnormal.Xcomponent + Y*Ynormal.Xcomponent + Z*Znormal.Xcomponent;
                   double Ycomp = X*Xnormal.Ycomponent + Y*Ynormal.Ycomponent + Z*Znormal.Ycomponent;
                   double Zcomp = X*Xnormal.Zcomponent + Y*Ynormal.Zcomponent + Z*Znormal.Zcomponent;
                   
                   Ray r = new Ray(origin, new Vector(Xcomp, Ycomp, Zcomp));
                   Kermo.display.Frame[y + FrameHeight/2][x + FrameWidth/2] = Methods.TraceRay(r);
               }
       }
       
       void rotateX(double rotation)
       {
           Ynormal.rotate(Xnormal, rotation);   Znormal.rotate(Xnormal, rotation);
       }
       void rotateY(double rotation)
       {
           Xnormal.rotate(Ynormal, rotation);   Znormal.rotate(Ynormal, rotation);
       }
       void rotateZ(double rotation)
       {
           Xnormal.rotate(Znormal, rotation);   Ynormal.rotate(Znormal, rotation);
       }
       
       void move(int x, int y, int z)
       {
           origin.X += x; origin.Y += y; origin.Z += z;
       }
   }