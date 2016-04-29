//author: drodev

public class Percolation {
	int[][] board;
	private int N;
	boolean[][] beenThere;
	
	//constructor
	public Percolation(int N){
		this.N=N;
		board = new int[N][N];
		beenThere = new boolean[N][N];
	}
	
	public boolean isOpen(int i, int j){
		if (board[i][j]==0) return false;
		return true;
	}
	
	public void open(int i, int j){
		board[i][j]=1;
	}
	
	
	public boolean isFull(int row, int col){
		beenThere[row][col]=false;
		if (recurs(row, col)) {
			board[row][col]=2; //mark 2 the blue square
			return true;
		}
		return false;
	}
	
	
	
	public boolean recurs(int row, int col){
	
		if(row<0 || row>N-1|| col<0 || col>N-1) return false; //out of bounds
		if (!isOpen(row, col))return false; //block is closed
		if (row==0) return true; //for the top row
		if(board[row][col]==2) return true; //is blue
		
		if (beenThere[row][col]) return false; //been there, done that! Get OUT!
		beenThere[row][col]=true;
		
		if (recurs(row-1,col)) return true;	
		if (recurs(row,col+1)) return true;
		if (recurs(row,col-1)) return true;
		return (recurs(row+1,col));
					
		}
	
	
	public boolean percolates(){
		for (int i=0; i<N-1;i++){
			if(board[N-1][i]==2) return true; //if one block from the last row is blue return true
		}
		return false;
	}
}
