/**
 * ArrayStack
 *
 * Implementation of a stack using an array
 * as the backing store.
 *
 * @author Tilak Patel
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

	private T[] array;
	private int tracker = 0;
    private static final int STACK_SIZE = 200;
    //TODO Add any private variables you may need.
    //HINT Use an object array and something to keep track
    //     of what the next available slot or last added index is.

    public ArrayStack() {
        //TODO Initialize an array of the size specified.
    	array = (T[]) new Object [STACK_SIZE];
    }

    @Override
    public void push(T t) {
    	if (tracker == STACK_SIZE) {
    		throw new RuntimeException();
    	} else if (t == null) {
    		throw new IllegalArgumentException();
    	} else {
    		array[tracker] = t;
    		tracker++;
    	}
    }

    @Override
    public T pop() {
    	if (tracker == 0) {
    		return null;
    	}
    	T removedData = array[tracker - 1];
        array[tracker - 1] = null;
        tracker--;
        return removedData;
    }

    @Override
    public T[] toArray() {
        return array;
    }

    @Override
    public boolean isEmpty() {
        return (tracker == 0);
    }
}
