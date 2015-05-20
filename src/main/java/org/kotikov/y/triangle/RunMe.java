package org.kotikov.y.triangle;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RunMe {

	/**
	 * @param args
	 * @throws WrongCoordinatesException 
	 * @throws WrongTriangleException 
	 */
	 
	public static void main(String[] args) throws WrongCoordinatesException, WrongTriangleException  {
 
		   RtriangleProvider.setRtectangle( 6, 2, 2, 2, 6, 7 );
	  //   RtriangleProvider.setRtectangle( -80000, -10, 1, 01, -1, 1 );
		// RtriangleProvider.setRtectangle( 0, 0, 0, 0, -1, 1 );
		
	  System.out.println ("IsRtriangle() = "+ RtriangleProvider.IsRtriangle()  ); 
      System.out.println(RtriangleProvider.getRtriangle());
      
     
      
      int  X1=-1;
      int  Y1=1;
      int  X2=93999996; //9999;  //47
      int  Y2=1; 
      int  X3=-1;
      int  Y3=0;
      
     double      c = Math.sqrt(Math.pow(X2-X1, 2) +  Math.pow(Y2-Y1, 2));
     double  	 a = Math.sqrt(Math.pow(X3-X2, 2) +  Math.pow(Y3-Y2, 2));
     double		 b = Math.sqrt(Math.pow(X3-X1, 2) +  Math.pow(Y3-Y1, 2));
      
     double
 	value = Math.acos(((Math.pow(a,2) + Math.pow(b,2) - Math.pow(c,2)) ) / (2 * a * b) ) * (180 / Math.PI) ;
     
    System.out.println(String.format("%.12f", value));
     //System.out.println("vall = "+ value);
     
	}

}
