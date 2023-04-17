package groovy.example.application.exception

class NoSuchTaskExistsException extends RuntimeException {
	private String message;
	
	public NoSuchTaskExistsException() {}
	
	public NoSuchTaskExistsException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}
