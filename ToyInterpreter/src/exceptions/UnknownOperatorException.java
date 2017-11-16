package exceptions;

public class UnknownOperatorException extends Exception {
	private String e;
	public UnknownOperatorException(String s){
		super(s);
	}
}

