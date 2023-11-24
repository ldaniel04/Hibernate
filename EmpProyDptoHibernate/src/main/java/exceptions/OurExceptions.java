package exceptions;

public class OurExceptions extends Exception {

	
	/**
	 * Just for eliminate the warning
	 */
	private static final long serialVersionUID = 1L;

	public OurExceptions() {
		// TODO Auto-generated constructor stub
	}
	
	public OurExceptions(String error) {
		super(error);
	}
	
	
}
