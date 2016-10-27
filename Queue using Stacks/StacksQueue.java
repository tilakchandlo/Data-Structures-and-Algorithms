/**
 * StacksQueue
 *
 * Implementation of a queue using
 * two stacks as the backing store.
 *
 * @author Tilak Patel
 * @version 1.0
 */
public class StacksQueue<T> implements QueueInterface<T> {

	private ArrayStack<T> enqueueStack = new ArrayStack<T>();
	private ArrayStack<T> dequeueStack = new ArrayStack<T>();
    // TODO Add any private variables you need.
    // HINT Use one stack as the "enqueue" stack
    //      and one stack as the "dequeue" stack.
    @Override
    public void enqueue(Object o) {
    	enqueueStack.push((T)o);
    }

    @Override
    public T dequeue() {
    	if (isEmpty()) {
    		return null;
    	}
    	if (dequeueStack.isEmpty()) {
    		while (!enqueueStack.isEmpty()) {
    			dequeueStack.push(enqueueStack.pop());
    		}
    	}
    	return dequeueStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return (enqueueStack.isEmpty() && dequeueStack.isEmpty());
    }
}
