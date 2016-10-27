import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * CircularLinkedList implementation
 * @author CS 1332 TAs
 * @author Tilak Patel
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T>, Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addToFront(data);
        }
        else if (index == size) {
            addToBack(data);
        }
        else {
            Node<T> prev = getNodeAtIndex(index - 1);
            Node<T> newData = new Node<T>(data);
            newData.setNext(prev.getNext());
            prev.setNext(newData);
            size += 1;
        }
    }

    @Override
    public T get(int index) {
        return getNodeAtIndex(index).getData();
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == (size - 1)) {
            return removeFromBack();
        }
        Node<T> prev = getNodeAtIndex(index - 1);
        T data = prev.getNext().getData();
        prev.setNext(prev.getNext().getNext());
        size -= 1;
        return data;
    }

    @Override
    public void addToFront(T t) {
        if (head == null) {
            head = new Node<T>(t);
            tail = head;
            head.setNext(tail);
        } else {
            Node<T> newNode = new Node<T>(t);
            newNode.setNext(head);
            tail.setNext(newNode);
            head = newNode;
        }
        size += 1;
    }

    @Override
    public void addToBack(T t) {
        if (tail == null) {
            head = new Node<T>(t);
            tail = head;
            head.setNext(tail);
        } else {
            Node<T> newNode = new Node<T>(t);
            newNode.setNext(head);
            tail.setNext(newNode);
            tail = newNode;
        }
        size += 1;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }

        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            tail.setNext(head);
        }
        size -= 1;
        return data;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T data = tail.getData();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> curNode = head;
            while (curNode.getNext() != tail) {
                curNode = curNode.getNext();
            }
            tail = curNode;
            tail.setNext(head);
        }
        size -= 1;
        return data;
    }

    @Override
    public T[] toList() {
        T[] items = (T[]) new Object[size];
        Node<T> curNode = head;
        for (int i = 0; i < size; i++) {
            items[i] = curNode.getData();
            curNode = curNode.getNext();
        }
        return items;
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
        head = null;
        tail = null;
        size = 0;
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
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * Returns the Node at the index specified or throws IndexOutOfBoundsException
     */
    private Node<T> getNodeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head;
        }
        if (index == (size - 1)) {
            return tail;
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new CircularIterator();
	}
	
	private class CircularIterator implements Iterator<T> {
		private Node<T> current = head;
		private int count = 0;
        // private variables go here
		@Override
		public boolean hasNext() {
			if (isEmpty()) {
				return false;
			}
			return (count < size);
		}

		@Override
		public T next() {
			T returnValue = null;
			if(hasNext()) {
				returnValue = current.getData();
				current = current.getNext();
				count++;
				return returnValue;
			} else {
				throw new NoSuchElementException("No next element.");
			}
		}
	}
}

