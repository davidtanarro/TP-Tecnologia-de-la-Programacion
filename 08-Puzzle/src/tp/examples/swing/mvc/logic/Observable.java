package tp.examples.swing.mvc.logic;


/**
 * Generic interface for the Observable pattern.
 */

public interface Observable<T> {
	
   /** 
    * Adds an observer o        
	*/
	public void addObserver(T o);

   /**
	* Removes an observer o        
	*/
	public void removeObserver(T o);

}
