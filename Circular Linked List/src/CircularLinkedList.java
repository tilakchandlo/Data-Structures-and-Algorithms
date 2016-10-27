/**
 * CircularLinkedList implementation
 * @author Tilak Patel
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public void addAtIndex(int index, T data) {
    	if (index < 0 || index > size) {
    		throw new IndexOutOfBoundsException();
    	} else if (index == size) {
    		addToBack(data);
    	} else if (index == 0) {
    		addToFront(data);
    	} else {
    		Node<T> newNode = new Node<T>(data);
    		int count = 0;
    		Node<T> temp = head;
    		while (count < index - 1) {
    			temp = temp.getNext();
    			count++;
    		}
    		Node<T> shiftNode = temp.getNext();
    		temp.setNext(newNode);
    		newNode.setNext(shiftNode);
    		size++;
    	}
    }

    @Override
    public T get(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException();
    	} else if (index == 0) {
    		return head.getData();
    	} else if (index == size - 1) {
    		return tail.getData();
    	} else {
    		Node<T> temp = head;
    		for (int i = 0; i < index; i++) {
    			temp = temp.getNext();
    		}
    		return temp.getData();
    	}
    }

    @Override
    public T removeAtIndex(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException();
    	} else if (index == 0) {
    		return removeFromFront();
    	} else {
    		Node<T> temp = head;
    		for (int i = 0; i < index - 1; i++) {
    			temp = temp.getNext();
    		}
    		Node<T> removed = temp.getNext();
    		temp.setNext(temp.getNext().getNext());
    		size--;
        	return removed.getData();
    	}
    }

    @Override
    public void addToFront(T t) {
        Node<T> newNode = new Node<T>(t, head);
        if (isEmpty()) {
        	head = newNode;
            tail = head;
            tail.setNext(head);
        } else {
            tail.setNext(newNode);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addToBack(T t) {
        if (isEmpty()) {
            addToFront(t);
        } else {
            Node<T> newNode = new Node<T>(t, head);
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    @Override
    public T removeFromFront() {
    	Node<T> removed = head;
    	if (head == null || size == 0) {
    		return null;
    	} else if (size == 1) {
    		head = null;
    		tail = null;
    	} else {
    		head = removed.getNext();
    		tail.setNext(head);
    	}
		size--;
    	return removed.getData();
    }

    @Override
    public T removeFromBack() {
    	Node<T> removed = tail;
    	if (head == null || size == 0) {
    		return null;
    	} else if (size == 1) {
    		head = null;
    		tail = null;
    	} else {
    		Node<T> temp = head;
    		for (int i = 0; i < size - 2; i++) {
    			temp = temp.getNext();
    		}
    		temp.setNext(head);
    		tail = temp;
    	}
    	size--;
    	return removed.getData();
    }

    @Override
    public T[] toList() {
    	T[] array = (T[]) new Object[size];
    	Node<T> temp = head;
    	for (int i = 0; i < size; i++) {
    		array[i] = temp.getData();
    		temp = temp.getNext();
    	}
    	return array;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the head of the linked list
     */
    public Node<T> getHead() {
    	return head;
    }

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {
    	return tail;
    }

    /**
     * This method is for your testing purposes.
     * You may choose to implement it if you wish.
     */
    @Override
    public String toString() {
        return "";
    }
}

