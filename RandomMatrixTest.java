/*
Created by Daniel Dallaire

Finding the average value of the determinant of 'sampleSize' 4 by 4 matrices utilizing my Matrix Class
*/
import java.util.Random;

public class Test{
  public static void main(String[] args){
    Random gen = new Random();
	int m = 4, n = 4;
	Matrix M;
	
	
	double sum = 0, sampleSize = 100000;
	
	for(int a = 0; a < sampleSize; a++){
		M = new Matrix(m,n);
		
		for(int i = 1; i <= m; i++)
			for(int j = 1; j <= n; j++)
				M.setEntry(i,j,gen.nextInt(10));

		sum += M.determinant()/sampleSize;
	}
	
	System.out.println("The average was: " + sum);
  }
}
