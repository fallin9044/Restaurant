package restaurant.utils;

public class PageValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PageValidationException() {
		super();
	}

	public PageValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PageValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageValidationException(String message) {
		super(message);
	}

	public PageValidationException(Throwable cause) {
		super(cause);
	}

}
