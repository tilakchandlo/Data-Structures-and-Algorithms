/**
 * Priority Queue
 *
 * @author Tilak Patel
 * @version 1.0
 */
public class PriorityQueue<T extends Comparable<? super T>> implements
       PriorityQueueInterface<T>, Gradable<T> {

    private Heap<T> priorityQueue = new Heap<T>();

    @Override
    public void insert(T item) {
        priorityQueue.add(item);
    }

    @Override
    public T findMin() {
        T ans = priorityQueue.peek();
        return ans;
    }

    @Override
    public T deleteMin() {
        return priorityQueue.remove();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public void makeEmpty() {
        Heap<T> temp = new Heap<T>();
        priorityQueue = temp;
    }

    @Override
    public T[] toArray() {
        return priorityQueue.toArray();
    }


}
