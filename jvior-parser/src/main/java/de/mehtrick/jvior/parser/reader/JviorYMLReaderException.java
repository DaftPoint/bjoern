package de.mehtrick.jvior.parser.reader;

public class JviorYMLReaderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JviorYMLReaderException(String path, Throwable cause) {
		super(String.format("The File under %s could not be read. " + cause.getMessage(), path), cause);
	}

}
