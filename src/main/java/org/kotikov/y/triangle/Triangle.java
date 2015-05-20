package org.kotikov.y.triangle;

public class Triangle implements Rtriangle {
	
	private int X1;
	private int Y1;
	private int X2;
	private int Y2;
	private int X3;
	private int Y3;
	
	
public  Triangle(int X1,int Y1, int X2, int Y2, int X3, int Y3 ) {
	 this.X1=X1;
	 this.Y1=Y1;
	 this.X2=X2;
	 this.Y2=Y2;
	 this.X3=X3;
	 this.Y3=Y3;
	        
}
	
	public int getApexX1() {
		 
		return X1;
	}

	public int getApexY1() {
		 
		return Y1;
	}

	public int getApexX2() {
		 
		return X2;
	}

	public int getApexY2() {
		 
		return Y2;
	}

	public int getApexX3() {
		 
		return X3;
	}

	public int getApexY3() {
		 
		return Y3;
	}

	
	@Override
	public String toString() {
		return "Triangle [X1=" + X1 + ", Y1=" + Y1 + ", X2=" + X2
				+ ", Y2=" + Y2 + ", X3=" + X3 + ", Y3=" + Y3 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X1;
		result = prime * result + X2;
		result = prime * result + X3;
		result = prime * result + Y1;
		result = prime * result + Y2;
		result = prime * result + Y3;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle other = (Triangle) obj;
		if (X1 != other.X1)
			return false;
		if (X2 != other.X2)
			return false;
		if (X3 != other.X3)
			return false;
		if (Y1 != other.Y1)
			return false;
		if (Y2 != other.Y2)
			return false;
		if (Y3 != other.Y3)
			return false;
		return true;
	}
}
