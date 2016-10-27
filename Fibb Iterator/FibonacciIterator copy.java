import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a class that will allow you to iterate through the first n
 * Fibonacci elements
 * @author Calvin Lin
 *
 */
public class FibonacciIterator implements Iterator<Integer> {
	private Integer n;
	private Integer current;
	private Integer runningValue = 1;
	private Integer previousValue = 0;

	public FibonacciIterator(Integer n) {
		this.n = n;
		current = 0;
	}

	@Override
	public boolean hasNext() {
		return (n != 0);

	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		current = runningValue + previousValue;
		previousValue = runningValue;
		runningValue = current;
		n--;
		return previousValue;
	}
}
