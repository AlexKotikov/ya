package org.kotikov.y.triangle;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class RtriangleProvider  {
	
private static  Rtriangle innerTriangle = null;
private static RtriangleProvider.RightTriangleChecker checker = null;

private RtriangleProvider() {}
 

public static void setRtectangle(int  X1,int  Y1, int  X2, int  Y2, int  X3, int  Y3)    {
	
		//Специальный объект который проверит все условия для построения треугольника
		checker = new  RightTriangleChecker(X1,Y1,X2,Y2,X3,Y3);

		checker.lineA = Math.sqrt(Math.pow(X2-X1, 2) +  Math.pow(Y2-Y1, 2));
		checker.lineB = Math.sqrt(Math.pow(X3-X2, 2) +  Math.pow(Y3-Y2, 2));
		checker.lineC = Math.sqrt(Math.pow(X3-X1, 2) +  Math.pow(Y3-Y1, 2));
		
		//Объект который предполагается вернуть в случае если он прямоугольный треугольник
		innerTriangle = new Triangle(X1,Y1,X2,Y2,X3,Y3) ;
		

	 
}

public static boolean IsRtriangle() throws WrongCoordinatesException {	
	 
	  //Обязать клиент вызывать метод set в начале всегда.
	if (checker == null)
			throw new WrongCoordinatesException("You must set the triangle first");
		
	  // Если треугольник задан, то в случае любых проблем с треугольником возвращать false
 	try {
	     checker.doChecks();
	 	   }
 	        catch (WrongCoordinatesException e)  {
			 return false;
 	        	}
 	        catch (WrongTriangleException e) {
 	          return false;
			} 

 return true;	  
}


public static Rtriangle getRtriangle() throws    WrongCoordinatesException, WrongTriangleException {	 
	if (innerTriangle == null)
	     throw new WrongCoordinatesException("You must set the triangle first");
	   
	  checker.doChecks(); 
	     	  
	  return  innerTriangle;
	 
}

private static final class RightTriangleChecker { 
	
	
	
	private int X1;
	private int Y1;
	private int X2;
	private int Y2;
	private int X3;
	private int Y3;
	
	private double lineA;
	private double lineB;
	private double lineC;
	
	public  RightTriangleChecker (int X1,int Y1, int X2, int Y2, int X3, int Y3 ) {
		 this.X1=X1;
		 this.Y1=Y1;
		 this.X2=X2;
		 this.Y2=Y2;
		 this.X3=X3;
		 this.Y3=Y3;
		        
	}
	
	
private void doChecks() throws WrongTriangleException, WrongCoordinatesException  {
		
	    checker.checkValuesInСoordinateSystem();
	
		checker.areLinesMoreThenZero();
		checker.isPossibleToComposeTriangle();	
		checker.isNotIsoscelesTriangle (); 
		checker.powOfHypoEqualsToSumOfPowOfLeg();
		checker.has90DegreeAngle ();
		checker.areAllAngles180Degrees();
	}
	
	
	//Все углы равны 180 градусов
private void areAllAngles180Degrees () throws WrongTriangleException {
 	     double 
		         d = angle(this.lineA,this.lineB,this.lineC);
		     d = d + angle(this.lineC,this.lineA,this.lineB);
			 d = d + angle(this.lineC,this.lineB,this.lineA);
			 
	  //TODO Добавить код на проверку того, что угол может быть NaN или ноль		 
		 
		 BigDecimal angle180 = new BigDecimal("180" );		 
		 			angle180 = angle180.setScale(0, BigDecimal.ROUND_HALF_UP);
		
		 BigDecimal result = new BigDecimal(Double.toString(d)); 
		 			result = result.setScale(0, BigDecimal.ROUND_HALF_UP);
			
			 if  ( result.compareTo(angle180) != 0)
				 		throw new  WrongTriangleException("areAllAngles180Degrees()");  
	   }
		
	
	
	//Квадрат гипотенузы равен сумме квадратов катетов
private static void checkPowOfHypAndLegs (double a, double b, double c) throws WrongTriangleException {
		
	MathContext context = new MathContext(4, RoundingMode.DOWN);
	
	
		double value = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));	
		
		if (Double.isInfinite(value))
			 throw new  WrongTriangleException(" Hyp is Infinite");
		
	      BigDecimal  summ = new BigDecimal(Double.toString(value), context );		 
	      BigDecimal  hypo = new BigDecimal(Double.toString(c),context); 
 			
			if  ( summ.compareTo(hypo) != 0){
				System.out.println("hypo in="+ c);
				System.out.println("hypo="+ hypo);
				System.out.println("a + b value="+ value);
				System.out.println("balue as summ="+ summ);
				System.out.println("  A="+ checker.lineA);
				System.out.println("  B="+ checker.lineB);
				System.out.println("  C="+ checker.lineC);
				System.out.println();
				throw new  WrongTriangleException("checkPowOfHyp()");		    	
			}
 	
	}
		
	
	//Вычислить угол между двумя сторонами
private static double angle (double a, double b, double c) throws WrongTriangleException {   
		
	double
	value = Math.acos(((Math.pow(a,2) + Math.pow(b,2) - Math.pow(c,2)) ) / (2 * a * b) ) * (180 / Math.PI) ;

	
	if (Double.isInfinite(value)) 
		   throw new  WrongTriangleException("angle() isInfinite = true");	

	
	    return   value;     
		}


	//Есть хотябы один угол 90 градусов
private   void	has90DegreeAngle () throws WrongTriangleException {
		final String errorMSg = "has90DegreeAngle";
		
		BigDecimal angle90 = new BigDecimal("90" );		 
		angle90 = angle90.setScale(0, BigDecimal.ROUND_HALF_UP);
		BigDecimal result = new BigDecimal("0"); 
		
		if ((this.lineA > this.lineB) && (this.lineA > this.lineC))
		 { 
			result =  result.add(BigDecimal.valueOf(angle(this.lineB,this.lineC,this.lineA)) );
			result = result.setScale(0, BigDecimal.ROUND_HALF_UP);
			
			 if  ( result.compareTo(angle90) != 0)
				   {
				    /*System.out.println("angle =" + result);
				    
					System.out.println("  A="+ checker.lineA);
					System.out.println("  B="+ checker.lineB);
					System.out.println("  C="+ checker.lineC);
					System.out.println();*/
				    
				    throw new  WrongTriangleException(errorMSg);
				   }
			 }
	     else 
	    	if  ((this.lineB > this.lineA) && (this.lineB > this.lineC))
	    		{
	    		result =  result.add(BigDecimal.valueOf(angle( this.lineA,this.lineC,this.lineB)));
	    		result =  result.setScale(0, BigDecimal.ROUND_HALF_UP);
	    		
	    		if  ( result.compareTo(angle90) != 0)
	    		       {
	    			     System.out.println("angle=" +  result);
	    			    throw new  WrongTriangleException(errorMSg);}
	    		}
	     else 
	    	if  ((this.lineC > this.lineA) && (this.lineC > this.lineB))
	    		{
	    		result =  result.add(BigDecimal.valueOf(angle(this.lineA,this.lineB,this.lineC)));
	    		result = result.setScale(0, BigDecimal.ROUND_HALF_UP);
	    		
	    		if   ( result.compareTo(angle90) != 0)
	    		   {throw new  WrongTriangleException(errorMSg);}
	    		}
		 
	}
	
	
private   void	powOfHypoEqualsToSumOfPowOfLeg () throws WrongTriangleException {
	    if ((this.lineA > this.lineB) && (this.lineA > this.lineC)){
	        checkPowOfHypAndLegs(this.lineB,this.lineC,this.lineA);} 
	    
	    	else 
    	if  ((this.lineB > this.lineA) && (this.lineB > this.lineC)){
    	     checkPowOfHypAndLegs( this.lineA,this.lineC,this.lineB);}
    	 
    	else 
    	if  ((this.lineC > this.lineA) && (this.lineC > this.lineB)){
    	   	  checkPowOfHypAndLegs(this.lineA,this.lineB,this.lineC); }
	    
	}
	
	// Проверка что не равнобедренный (дополнительно)
private   void	isNotIsoscelesTriangle ( ) throws WrongTriangleException {
			
			 if ((this.lineA == this.lineB) && (this.lineB ==this.lineC) && (this.lineC== this.lineA))
				    throw new  WrongTriangleException("isNotIsoscelesTriangle()"); 
			 
		}
	

//Возможно ли построить с такими сторонами
private   void isPossibleToComposeTriangle() throws WrongTriangleException {
			
			 if (!((this.lineA + this.lineB > this.lineC) && 
					 (this.lineA + this.lineC > this.lineB) && 
					     (this.lineB + this.lineC > this.lineA))) 
				    
				            throw new  WrongTriangleException("isPossibleToComposeTriangle()");
			
		 
		}
	
	
	private   void	areLinesMoreThenZero () throws WrongTriangleException {
		
		 if (!((this.lineA > 0 ) && (this.lineB > 0) && ( this.lineC > 0)))  
			 {
			 System.out.println("this.lineA" + this.lineA );
			 System.out.println("this.lineB" + this.lineB);
			 System.out.println("this.lineC" + this.lineC );
			 throw new  WrongTriangleException("areLinesMoreThenZero()");
			 }
	}
	
	
	private  void checkValuesInСoordinateSystem() throws WrongCoordinatesException{
       final String cartErrorMsg = " exceeds the bounds of the existed coordinate system";
		
		if ( (this.X1>CartesianСoordinateSystem.MAXX ) ||
			   (this.X1<CartesianСoordinateSystem.MINX))
				 throw new WrongCoordinatesException("X1" + cartErrorMsg );
			   
			 if  ((this.X2>CartesianСoordinateSystem.MAXX ) ||
			      (this.X2<CartesianСoordinateSystem.MINX) )
				 throw new WrongCoordinatesException("X2" + cartErrorMsg);
			 
			 
			 if   ((this.X3>CartesianСoordinateSystem.MAXX ) ||
			       (this.X3<CartesianСoordinateSystem.MINX))
			       throw new WrongCoordinatesException("X3" + cartErrorMsg);
					
				if ((this.Y1>CartesianСoordinateSystem.MAXY ) ||
			       (this.Y1<CartesianСoordinateSystem.MINY))
				    throw new WrongCoordinatesException("Y1" + cartErrorMsg);
			     
			 if   ((this.Y2>CartesianСoordinateSystem.MAXY ) ||
			       (this.Y2<CartesianСoordinateSystem.MINY) )
				    throw new WrongCoordinatesException("Y2 " + cartErrorMsg);
			 
			 if   ((this.Y3>CartesianСoordinateSystem.MAXY ) ||
			       (this.Y3<CartesianСoordinateSystem.MINY))
			       throw new WrongCoordinatesException("Y3" + cartErrorMsg);
			}
	
	
	} //inner class 
} // main class