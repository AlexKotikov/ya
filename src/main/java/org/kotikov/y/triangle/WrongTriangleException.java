package org.kotikov.y.triangle;

public class WrongTriangleException extends Exception {

	public WrongTriangleException(String text) {
		super(text);
	}

	 
     public WrongTriangleException(String text,Exception innerEx)
     {
         super(text,innerEx);
         
     }
 }
	
	

