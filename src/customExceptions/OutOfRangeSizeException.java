package customExceptions;

/**
 * <b> Laboratorio unidad 1 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
@SuppressWarnings("serial")
public class OutOfRangeSizeException extends IllegalArgumentException{

	/**
	* The type of out that makes the exception trigger.
	*/
	public static final String OVER = "The size entered is over the permited limit";
	
	/**
	* The type of out that makes the exception trigger.
	*/
	public static final String UNDER = "The size entered is under the permited limit";
	
	/**
	* The type of out that makes the exception trigger.
	*/
	public static final String CONDITION = "The size entered doesn't meet the condition";
	
	/**
	* The type of out that makes the exception trigger.
	*/
	private String out;
	
	/**
	* The size entered by the user.
	*/
	private int size;
	
	
	/**
	 * Initializes a new OutOfRangeSizeException.
	 * @param s The size entered by the user.
	 * @param condition The condition that the size must meet.
	 * @param min The minimum size allowed.
	 * @param max The maximum size allowed.
	 */
	public OutOfRangeSizeException(int s, boolean condition, int min, int max) {
		super("The size entered is not valid");
		size = s;
		
		calculateOut(s, condition, min, max);
	}
	
	
	/**
	 * This function calculates whether or not the exception has to be triggered.
	 * @param s The size entered by the user.
	 * @param condition The condition that the size must meet.
	 * @param min The minimum size allowed.
	 * @param max The maximum size allowed.
	 */
	private void calculateOut(int s, boolean condition, int min, int max) {
		if(s < min) {
			out = UNDER;
		}else if(s>max){
			out = OVER;
		}else if(!condition){
			out = CONDITION;
		}
	}
	
	
	/* 
	 * This function returns the message thrown by the exception.
	 * @return A String representing the message.
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + out;
		
	}
	
	/**
	 * This function obtains the type of out of the exception.
	 * @return A String representing the type of out.
	 */
	public String getOut() {
		return this.out;
	}
}
