package org.kotikov.y.triangle;

public class WrongCoordinatesException extends Exception {

	public WrongCoordinatesException(String text) {
		super(text);
	}

	 
     public WrongCoordinatesException(String text,Exception innerEx)
     {
         super(text,innerEx);
         
     }
	
	 
	
}
