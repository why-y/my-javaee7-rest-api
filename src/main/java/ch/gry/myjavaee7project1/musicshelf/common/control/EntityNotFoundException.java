/**
 * 
 */
package ch.gry.myjavaee7project1.musicshelf.common.control;

/**
 * @author gry
 *
 */
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	
	

}
