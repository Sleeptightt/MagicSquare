package customExceptions;

@SuppressWarnings("serial")
public class OutOfRangeSizeException extends Exception{

	public static final String OVER = "The size entered is over the permited limit";
	public static final String UNDER = "The size entered is under the permited limit";
	public static final String CONDITION = "The size entered doesn't meet the condition";
	
	
	private String out;
	private int size;
	
	public OutOfRangeSizeException(int s, boolean condition, int min, int max) {
		super("The size entered is not valid");
		size = s;
		
		calculateTypeOfOut(s, condition, min, max);
	}
	
	private void calculateTypeOfOut(int s, boolean condition, int min, int max) {
		if(s < min) {
			out = UNDER;
		}else if(s>max){
			out = OVER;
		}else if(!condition){
			out = CONDITION;
		}
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + out;
		
	}
	
	public String getOut() {
		return this.out;
	}
}
