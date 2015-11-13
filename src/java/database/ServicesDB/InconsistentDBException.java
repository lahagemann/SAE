/**
 * 
 */
package database.ServicesDB;

/**
 * @author gustavo
 *
 */
public class InconsistentDBException extends Exception {

	private static final long serialVersionUID = 1L;

	public InconsistentDBException() {
		super();
	}

	public InconsistentDBException(String message) {
		super(message);
	}
	
}
