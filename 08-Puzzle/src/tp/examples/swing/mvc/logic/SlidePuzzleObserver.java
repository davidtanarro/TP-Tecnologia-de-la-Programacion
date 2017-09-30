package tp.examples.swing.mvc.logic;


public interface SlidePuzzleObserver {
	
	public void onGameStart(Board board);
	
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol);
	
	public void onGameOver(Board board);
	
	public void onError(String msg);
	
}
