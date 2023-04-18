package groovy.example.application.exception

class TaskAlreadyExistsException extends RuntimeException {
	private String message;
	
	public TaskAlreadyExistsException() {}
	
	public TaskAlreadyExistsException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}
