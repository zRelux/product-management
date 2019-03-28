public class SellingException extends Exception {
        
        /***
	 * Construct object with error Message
	 * @param errorMessage
	 */
	public SellingException(String errorMessage) {
		super(errorMessage);
	}
        
	/***
	 * Construct object with error Message
	 * @param errorMessage
	 */
	public SellingException() {
		super("Error when trying to sell product");
	}
        
}
