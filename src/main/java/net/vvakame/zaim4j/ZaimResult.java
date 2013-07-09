package net.vvakame.zaim4j;

/**
 * Result for Zaim API.<br>
 * This class likes Either.
 * @author vvakame
 * @param <R> result type
 */
public class ZaimResult<R> {

	final R value;

	final ErrorResponse error;


	private ZaimResult(ErrorResponse error) {
		this.value = null;
		this.error = error;
	}

	private ZaimResult(R value) {
		this.value = value;
		this.error = null;
	}

	static <R>ZaimResult<R> left(ErrorResponse error) {
		return new ZaimResult<R>(error);
	}

	static <R>ZaimResult<R> right(R value) {
		return new ZaimResult<R>(value);
	}

	/**
	 * Check result.
	 * @return success or error
	 * @author vvakame
	 */
	public boolean isSuccess() {
		return error == null;
	}

	/**
	 * @return the value
	 * @category accessor
	 */
	public R getValue() {
		if (!isSuccess()) {
			throw new IllegalStateException("response failure. please check by isSuccess() method.");
		}
		return value;
	}

	/**
	 * @return the error
	 * @category accessor
	 */
	public ErrorResponse getError() {
		if (isSuccess()) {
			throw new IllegalStateException("response success. please check by isSuccess() method.");
		}
		return error;
	}
}
