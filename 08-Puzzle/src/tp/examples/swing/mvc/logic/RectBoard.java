package tp.examples.swing.mvc.logic;


public class RectBoard implements Board {
	
	private int rows;
	private int cols;
	
	private int[][] board;


	public RectBoard(int rows, int cols) {
		
		this.rows = rows;
		this.cols = cols;
		
		board = new int[rows][cols];
		
	}

	
	@Override
	public int getWidth() {
		return cols;
	}

	@Override
	public int getHeight() {
		return rows;
	}
	
	@Override
	public int getPosition(int row, int col) {
		
		if ( row >= 1 && row <= rows && col >= 1 && col <= cols ) {
			return board[row-1][col-1];
		} else {
			return -1;
		}
		
	}
	
	@Override
	public boolean setPosition(int row, int col, int v) {
		
		if ( row >= 1 && row <= rows && col >= 1 && col <= cols ) {
			board[row-1][col-1] = v;
			return true;
		} else
			return false;
		
	}

	
}
