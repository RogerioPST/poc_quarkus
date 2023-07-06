package rogerio.pst.exception;

public class ProjetoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean isValidation;
	
	private Boolean shouldLog;
	
	public ProjetoException(final String cause, Boolean isValidation, Boolean shouldLog) {
		this(cause);
		this.isValidation = isValidation;
		this.shouldLog = shouldLog;
	}

	public ProjetoException(final String cause) {
		super(cause);
		this.shouldLog = false;
	}

}
