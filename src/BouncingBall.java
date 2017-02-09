/******************************************************************************
*  Compilation:  javac BouncingBall.java
*  Execution:    java BouncingBall
*  Dependencies: StdDraw.java from http://introcs.cs.princeton.edu/java/15inout/StdDraw.java
*  Author: 		 Rodrigo Costa 
*  
*  Code based on http://introcs.cs.princeton.edu/java/15inout/BouncingBall.java but upgraded to include
*  proper physics using the Euler step method.
*  
*  % java BouncingBall
*
******************************************************************************/
 
public class BouncingBall {
    public static void main(String[] args) {
 
        // set the scale of the coordinate system
       double L = 0.55;
       double dt = 0.00001;
       double step = 1/dt;
       double framerate = 1/dt*2;
       int n=0;
       
      
        StdDraw.setXscale(-L, L);
        StdDraw.setYscale(-L, L);
        //StdDraw.enableDoubleBuffering();
 
        // initial values
        double rx1 = 0.1, ry1 = 0.1;     // position
        double vx1 = -0.007, vy1 = -0.0012;     // velocity
        double radius1 = 0.05;              // radius
        double m1 = 1;                                        // mass
       
        double rx2 = -0.100, ry2 = -0.100;     // position
        double vx2 = 0.0005, vy2 = 0.0001;     // velocity
        double radius2 = 0.05;              // radius
        double m2 = 10;                                        // mass
       
        // initial values colision
        double phy = 0;
        double theta1 = 0;
        double theta2 = 0;
        double vi1 = 0;
        double vi2 = 0;
        double vo1 = 0;
        double vo2 = 0;
        double vif1 = 0;
        double vif2 = 0;
        double checkstep = L;
 
        // main animation loop
        while (true)  {
 
            // bounce off wall according to law of elastic collision
            if (Math.abs(rx1 + dt*vx1) > L - radius1) vx1 = -vx1;
            if (Math.abs(ry1 + dt*vy1) > L - radius1) vy1 = -vy1;
           
            if (Math.abs(rx2 + dt*vx2) > L - radius2) vx2 = -vx2;
            if (Math.abs(ry2 + dt*vy2) > L - radius2) vy2 = -vy2;
            

            
           // bounce off ball
           if (Math.sqrt(Math.pow(rx1-rx2, 2)+Math.pow(ry1-ry2, 2))<radius1+radius2){
        	   
        	         	          	   
               //check if ball are getting close
               if (Math.sqrt(Math.pow(rx1-rx2, 2)+Math.pow(ry1-ry2, 2))< checkstep ){
            	   
            	   //resolve collision (when remove one from inside the other)...
            	  /* while(Math.sqrt(Math.pow(rx1-rx2, 2)+Math.pow(ry1-ry2, 2))<=radius1+radius2){
            	  
                   rx1 = rx1 - 0.1*dt*vx1;
                   ry1 = ry1 - 0.1*dt*vy1;
                   rx2 = rx2 - 0.1*dt*vx2;
                   ry2 = ry2 - 0.1*dt*vy2;
                   
                   //if (Math.abs(rx1 - 0.01*dt*vx1) > L - radius1) vx1 = -vx1;
                   //if (Math.abs(ry1 - 0.01*dt*vy1) > L - radius1) vy1 = -vy1;
                   //if (Math.abs(rx2 - 0.01*dt*vx2) > L - radius2) vx2 = -vx2;
                   //if (Math.abs(ry2 - 0.01*dt*vy2) > L - radius2) vy2 = -vy2;
                                     
               		}*/


            	   
            	   n = n+1;
            	   System.out.println("COL: "+n+" rx1: "+rx1+" rx2: "+rx2+" ry1: "+ry1+" ry2: "+ry2+" vx1: "+vx1+" vx2: "+vx2+" vy1: "+vy1+" vx2: "+vy2);
            	   
            	   
             phy = Math.atan2(Math.abs(ry2-ry1),Math.abs(rx1-rx2));
             theta1 = Math.atan2(vy1,vx1);
             theta2 = Math.atan2(vy2,vx2);
             
             double v1 = Math.sqrt(Math.pow(vx1, 2)+Math.pow(vy1, 2));
             double v2 = Math.sqrt(Math.pow(vx2, 2)+Math.pow(vy2, 2));
             
             vi1=v1*Math.cos(theta1-phy);
             vo1=v1*Math.sin(theta1-phy);
             
             vi2=v2*Math.cos(theta2-phy);
             vo2=v2*Math.sin(theta2-phy);
            
             vif1=(vi1*(m1-m2)+(m2+m2)*vi2)/(m1+m2);
             vif2=(vi2*(m2-m1)+(m1+m1)*vi1)/(m1+m2);
             
             vx1=vif1*Math.cos(phy)+vo1*Math.cos(phy+Math.PI/2);
             vy1=vif1*Math.sin(phy)+vo1*Math.sin(phy+Math.PI/2);
             
             vx2=vif2*Math.cos(phy)+vo2*Math.cos(phy+Math.PI/2);
             vy2=vif2*Math.sin(phy)+vo2*Math.sin(phy+Math.PI/2);
             
            }}           
           
            checkstep = Math.sqrt(Math.pow(rx1-rx2, 2)+Math.pow(ry1-ry2, 2));
           
            // update position
            rx1 = rx1 + dt*vx1;
            ry1 = ry1 + dt*vy1;
            rx2 = rx2 + dt*vx2;
            ry2 = ry2 + dt*vy2;
            
            step = step+1;
            
            if ((step%(int)framerate) == 0){
            // clear the background
            StdDraw.clear(StdDraw.WHITE);
 
            // draw ball on the screen
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(rx1, ry1, radius1);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(rx2, ry2, radius2);
 
            // copy offscreen buffer to onscreen
            StdDraw.show(1);
 
            // pause for 20 ms
            StdDraw.pause(0);
              
            }
        }
    }
}