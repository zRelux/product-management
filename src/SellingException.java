public class SellingException extends Exception {

	private static final long serialVersionUID = 4069369204664173840L;

	/***
	 * Constucts object with error Message
	 * @param errorMessage
	 */
	public SellingException(String errorMessage) {
		super(errorMessage);
	}
	
}
