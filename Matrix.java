/*
Created by Daniel Dallaire
Matrix class with several operations related to matrices
*/

public class Matrix{
	private int m;
	private int n;
	private int entries[][];
	
	public Matrix(int m, int n){
		this.m = m;
		this.n = n;
		this.entries = new int[m][n];
	}
	
	public void rowOpAdd(int i, int j, int a){
		for(int col = 0; col < this.n; col++){
			this.entries[i-1][col] += a*this.entries[j-1][col];
		}
	}
	
	public void rowOpExc(int i, int j){
		int temp;
		for(int col = 0; col < this.n; col++){
			temp = this.entries[i-1][col];
			this.entries[i-1][col] = this.entries[j-1][col];
			this.entries[j-1][col] = temp;
		}
	}
	
	public void rowOpMult(int i, int a){
		for(int col = 0; col < this.n; col++){
			this.entries[i-1][col] *= a;
		}
	}
	
	public Matrix multiply(Matrix B){
		
		Matrix AB = new Matrix(this.m, B.getCols());
		
		int val;
		for(int row = 0; row < AB.getRows(); row++){
			for(int col = 0; col < AB.getCols(); col++){
				val = 0;
				for(int i = 0; i < this.n; i++){
					val += this.entries[row][i]*B.getEntry(i+1, col+1);
				}
				AB.setEntry(row+1, col+1, val);
			}
		}
		
		return AB;
	}
	
	public int determinant(){
		if(this.m == 1) 
			return this.entries[0][0];
		if(this.m == 2)
			return this.entries[0][0]*this.entries[1][1] - this.entries[0][1]*this.entries[1][0];
		
		int det = 0;
		
		for(int i = 0; i < this.m; i++){
			det += (i%2 == 0 ? 1 : -1) * this.entries[i][0] * this.subMatrix(i+1,1).determinant();
		}
		
		return det;
	}
	
	public void indentityN(int size){
		this.entries = new int[size][size];
		this.m = this.n = size;
		this.zeroMatrix();
		
		for(int i = 0; i < n; i++)
			this.entries[i][i] = 1;
	}
	
	public int getRows(){
		return this.m;
	}
	
	public int getCols(){
		return this.n;
	}
	
	public void zeroMatrix(){
		for(int row = 0; row < m; row++){
			for(int col = 0; col < n; col++){
				this.entries[row][col] = 0;
			}
		}
	}
	
	public Matrix subMatrix(int i, int j){
		Matrix S = new Matrix(this.m - 1, this.n -1);
		
		for(int row = 0; row < this.m; row++){
			for(int col = 0; col < this.n; col++){
				if(row+1 != i && col+1 != j)
					S.setEntry( 1 + (row >= i ? row - 1 : row), 1+ (col >= j ? col - 1 : col), this.entries[row][col]);
			}
		}
		
		return S;
	}
	
	public void setEntry(int i, int j, int val){
		this.entries[i-1][j-1] = val;
	}
	
	public int getEntry(int i, int j){
		return this.entries[i-1][j-1];
	}
	
	public void addMatrix(Matrix A){
		for(int row = 0; row < m; row++){
			for(int col = 0; col < n; col++){
				this.entries[row][col] += A.entries[row][col];
			}
		}
	}
	
	public Matrix transpose(){
		Matrix T = new Matrix(this.n, this.m);
		
		for(int row = 0; row < this.m; row++){
			for(int col = 0; col < this.n; col++){
				T.setEntry(col+1, row+1, this.entries[row][col]);
			}
		}
		
		return T;
	}
	
	public void display(){
		for(int i = 0; i <= 4*this.n; i++) System.out.print("-");
		System.out.println();
		
		for(int row = 0; row < m; row++){
			System.out.print("|");
			for(int col = 0; col < n; col++){
				System.out.print(" " + this.entries[row][col] + " |");
			}
			System.out.println();
			for(int i = 0; i <= 4*this.n; i++) System.out.print("-");
			System.out.println();
		}
	}
}
