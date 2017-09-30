package tp.examples.swing.mvc.logic;


public interface Board {
	
	public int getWidth();
	
	public int getHeight();
	
	public int getPosition(int row, int col);
	
	public boolean setPosition(int row, int col, int num);
	
}
