package jgit.test;

public class GitException extends RuntimeException {

	public GitException(String message, Throwable cause) {
		super(message, cause);
	}

	public GitException(Throwable cause) {
		super(cause);
	}
}
