/**
 * Heap
 *
 * @author Tilak Patel
 * @version 1.0
 */
public class Heap<T extends Comparable<? super T>> implements HeapInterface<T>,
       Gradable<T> {

    private T[] array;
    private int size;
    /**
     * Constructor for the Heap class
     */
    public Heap() {
        array = (T[]) new Comparable[10];
        size = 0;
    }
    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= array.length - 1) {
            resize();
        }
        size++;
        array[size] = item;
        siftUp();
    }

    /**
     * Resizes the backing array when the size is full
     */
    private void resize() {
        T[] temp = (T[]) new Comparable[array.length * 2];
        for (int i = 1; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    /**
     * Swaps two elements in the array
     * @param index1 the 1st index to be swapped
     * @param index2 the 2nd index to be swapped
     */
    private void swap(int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Ensures the heap property is maintained
     * after adding an element at the end of the array.
     * Shifts the added element up if the it is less than
     * the parent.
     */
    private void siftUp() {
        int i = size;

        while (i > 1
               && (array[i].compareTo(array[i / 2]) < 0)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    /**
     * Ensures the heap property is maintained
     * after removing the 1st element of the array.
     * Shifts the added element down if the it is greater than
     * the child(ren).
     */
    private void heapify() {
        int i = 1;
        while (i * 2 <= size) {

            int smallerChild = i * 2;
            if (i * 2 + 1 <= size
                && array[i * 2].compareTo(array[i * 2 + 1]) > 0) {
                smallerChild = i * 2 + 1;
            }

            if (array[i].compareTo(array[smallerChild]) > 0) {
                swap(i, smallerChild);
            } else {
            	i = size;
            }
            i = smallerChild;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array[1];
    }

    @Override
    public T remove() {
        T removed = peek();
        array[1] = array[size];
        array[size] = null;
        size--;
        heapify();
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        return array;
    }
}
