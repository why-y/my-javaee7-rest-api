/**
 * 
 */
package ch.gry.myjavaee7project1.musicshelf.common.control;

/**
 * @author gry
 *
 */
public class EntityNotPersistedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntityNotPersistedException(String message) {
		super(message);
	}
	
	public EntityNotPersistedException(String message, Throwable cause) {
		super(message, cause);
	}

	
	

}
