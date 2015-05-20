package org.kotikov.y.triangle.tests;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TranglesDataGenerator {

	private static List<Object[]>  resultSetOfData = new  ArrayList<Object[]>();
	
	
	public static  Object[] dataPrepare (Object[]  obj){
		  
		  List<Object>  tempNabor = new  ArrayList<Object>();
		  
		   //nabor = X
		   tempNabor.addAll ( Arrays.asList( obj)) ;
		   
		  
		   //nabor =X +  (X * 6) 
		    for(int i=0; i<obj.length; i++) { 
		     tempNabor.addAll( Arrays.asList(TranglesDataGenerator.coordsShuffler((Object[]) obj[i])));
		   }

		   //nabor = nabor * 16
		  for(int i=0; i< tempNabor.size(); i++) { 
		    	TranglesDataGenerator.sendToCoordinateSystem((Object[]) tempNabor.get(i));
		    	}
		 
		return resultSetOfData.toArray();
	  }
	
	
	 /**
	   * Посылать треугольник в разные четверти системы координат
	   */
  private static void  sendToCoordinateSystem( Object[] oneTriangle) {

	  //Поместить треугольник в разные четверти
	  rotater( oneTriangle, 1, 1);
	  rotater( oneTriangle,-1, 1);
	  rotater( oneTriangle, 1,-1);
	  rotater( oneTriangle,-1,-1);
  }
  
  public static void main (String[] arg){
		TranglesDataGenerator.sendToCoordinateSystem(new Object[] {6, 2,  6, 7,  2, 2, " treugolnicheg1"}) ;
		
		   for (Object nabor : resultSetOfData) {
		   
		   for(int i=0; i<7; i++) {
				   Object[] a =  (Object[]) nabor;
				   
				   if (i<6)
					 System.out.print( (Integer) a[i]+" ");
				   else  System.out.print( (String) a[i]+" ");
				   
			   }
			   System.out.println();
		   }
   }
  
  /**
   * Вычислять вокруг какой точки вертеть треугольник,
   * поворачивать там и добавлять полученные данные в тестовый набор.
   */
  private static void rotater(Object[] oneTriangle , int Xf, int Yf) {
      
	  //Получить данные из треугольника
		  int X1=(Integer)oneTriangle[0];
		  int Y1=(Integer)oneTriangle[1]; 
		  int X2=(Integer)oneTriangle[2]; 
		  int Y2=(Integer)oneTriangle[3]; 
		  int X3=(Integer)oneTriangle[4]; 
		  int Y3=(Integer)oneTriangle[5];
     String tText = (String)  oneTriangle[6]; //Сохранить текст из треугольника
	
	
	  // Вычислить точку по центру треугольтка чтобы потом вращать вокруг нее
	  int moveToX =(getCentroid(X1,X2,X3))*Xf-Math.min(X3, Math.min(X1, X2));   
	  int moveToY =(getCentroid(Y1,Y2,Y3))*Yf-Math.min(Y3, Math.min(Y1, Y2));
	   
	  
	  // для положительных координат 
	  int factorX =-Math.min(X3, Math.min(X1, X2));
	  int factorY =-Math.min(Y3, Math.min(Y1, Y2));
	  
	  //Инвертировать факторы для отрицательных координат
	  if (moveToX<0) factorX =-Math.max(X3, Math.max(X1, X2));
	  if (moveToY<0) factorY =-Math.max(Y3, Math.max(Y1, Y2));
	   
	   
	  //Тут вычисляется все 3 точки треугольника относительно центра вращения
	  X1 = (X1 + getDistance(X1 + moveToX,X1)  +factorX) * Xf;
	  Y1 = (Y1 + getDistance(Y1 + moveToY,Y1)  +factorY) * Yf;
	  
	  X2 = (X2 + getDistance(X2 +moveToX,X2)  +factorX) * Xf;
	  Y2 = (Y2 + getDistance(Y2 +moveToY,Y2)  +factorY) * Yf;
	  
	  X3 = (X3 + getDistance(X3 +moveToX,X3)  +factorX) * Xf ;
	  Y3 = (Y3 + getDistance(Y3 +moveToY,Y3)  +factorY) * Yf;
	  

	  //Поворот на ноль это - добавить как есть
	   resultSetOfData.add (new Object[]{X1, Y1, X2, Y2, X3, Y3, tText + ", rotated 0"}); 
	  
	  //Вращать треугольник вокруг своего центра и добавлять в тестовый набор 
	   resultSetOfData.add (rotateTriangle(new Object[]{X1, Y1, X2, Y2, X3, Y3, tText}, 90));  
	   resultSetOfData.add (rotateTriangle(new Object[]{X1, Y1, X2, Y2, X3, Y3, tText},180)); 
	   resultSetOfData.add (rotateTriangle(new Object[]{X1, Y1, X2, Y2, X3, Y3, tText},270)); 
  }
  
  
  /**
   * Повернуть один треугольник на  указанный угол, добавить запись что повернут,
   * вернуть обратно
   */
  private static Object[] rotateTriangle(Object[] inTriangle, int angle ) {
	   
	  //Узнать координаты
	  int X1=(Integer)inTriangle[0];
	  int Y1=(Integer)inTriangle[1]; 
	  int X2=(Integer)inTriangle[2]; 
	  int Y2=(Integer)inTriangle[3]; 
	  int X3=(Integer)inTriangle[4]; 
	  int Y3=(Integer)inTriangle[5];
     String tText = (String)  inTriangle[6]; //Сохранить текст треугольника
	
	//Повернуть и сохранить в новый объект
	Object[] oneTriangle = new Object[7];
	
	  oneTriangle[0] =
	  rotateXToAngleOnPoint(getCentroid(X1,X2,X3),getCentroid(Y1,Y2,Y3),angle,X1,Y1);
	  oneTriangle[1] =
	  rotateYToAngleOnPoint(getCentroid(X1,X2,X3),getCentroid(Y1,Y2,Y3),angle,X1,Y1);
	  
	  oneTriangle[2] =
	  rotateXToAngleOnPoint(getCentroid(X1,X2,X3),getCentroid(Y1,Y2,Y3),angle,X2,Y2);
	  oneTriangle[3] =
	  rotateYToAngleOnPoint(getCentroid(X1,X2,X3),getCentroid(Y1,Y2,Y3),angle,X2,Y2);
	  
	  oneTriangle[4] =
	  rotateXToAngleOnPoint(getCentroid(X1,X2,X3),getCentroid(Y1,Y2,Y3),angle,X3,Y3);
	  oneTriangle[5] =
	  rotateYToAngleOnPoint(getCentroid(X1,X2,X3),getCentroid(Y1,Y2,Y3),angle,X3,Y3);
  
	  oneTriangle[6] =   new String (tText+ ", rotated " +Integer.toString(angle));
	  
	  //Положить обратно
return oneTriangle;
}
  
  
 //--------------------------------------------------
 // Служебные методы
 private static int getCentroid(int x1,int x2,int x3) {
	  double xx = (double)(x1 + x2 + x3) /3;
      BigDecimal  d  = new BigDecimal(Double.toString(xx));
                  d  = d.setScale(0, RoundingMode.HALF_UP);
	  return d.intValue();
  }
  
  private static int  getDistance(int x1 , int dx1 ) {
	  double value = Math.sqrt(Math.pow(dx1-x1 ,2)  );	
	  
	  MathContext context2 = new MathContext(2, RoundingMode.HALF_UP);
	  BigDecimal  d  = new BigDecimal(Double.toString(value), context2  );
	  return d.intValue();
  }
  
  private static int rotateXToAngleOnPoint(int aroundX, int aroundY , int theta,  int inX,  int inY ) {
		double newX = (inX-aroundX) * Math.cos(Math.toRadians(theta))   - Math.sin(Math.toRadians(theta)) * (inY-aroundY) + aroundX ;
		  
		 
		 			 BigDecimal result = new BigDecimal(Double.toString(newX)); 
		 			 result = result.setScale(0, BigDecimal.ROUND_HALF_UP);			
		 			
		 			
      return  result.intValue()     ;
  }
  
  private static int  rotateYToAngleOnPoint(int aroundX, int aroundY , int theta,  int inX,  int inY ) {
		double newY = (inX-aroundX) * Math.sin(Math.toRadians(theta))   + Math.cos(Math.toRadians(theta)) * (inY-aroundY) + aroundY;
		  
			 		 BigDecimal result = new BigDecimal(Double.toString(newY)); 
		 			 result = result.setScale(0, BigDecimal.ROUND_HALF_UP);		 		
			 		
			 		
    return   result.intValue()  ;
}
  
  
  private static Object[] coordsShuffler (Object[]  inObject){
 	  int X1 = (Integer) inObject[0];
	  int Y1 = (Integer) inObject[1];
	  
	  int X2 = (Integer) inObject[2];
	  int Y2 = (Integer) inObject[3];
	  
	  int X3 = (Integer) inObject[4];
	  int Y3 = (Integer) inObject[5];
	  
	  String text = (String) inObject[6] + ", shuffled"; 
	  
	  Object[] trianglesSet = new Object[6]; //6 шт.
	  
	  trianglesSet[0]=  inObject; //Первый треугольник положить как есть ничего не перемешивая
	  trianglesSet[1]=  new Object[] {X1, Y1, X3, Y3, X2, Y2,  text};   
	  trianglesSet[2]=  new Object[] {X2, Y2, X1, Y1, X3, Y3,  text};
	  trianglesSet[3]=  new Object[] {X2, Y2, X3, Y3, X1, Y1,  text};
	  trianglesSet[4]=  new Object[] {X3, Y3, X1, Y1, X2, Y2,  text};
	  trianglesSet[5]=  new Object[] {X3, Y3, X2, Y2, X1, Y1,  text}; 
 	 
	  //Вернуть набор из первоночального + 5 перемешенных треугольников
	  return trianglesSet;
  }
  
}
