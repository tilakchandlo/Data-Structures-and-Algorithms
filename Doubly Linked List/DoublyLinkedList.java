/**
 * Doubly linked list implementation
 * @author Tilak Patel
 * @version 1.1
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            Node<T> prevNode = temp.getPrevious();
            temp.setPrevious(newNode);
            newNode.setPrevious(prevNode);
            prevNode.setNext(newNode);
            newNode.setNext(temp);
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
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            Node<T> removed = temp.getNext();
            Node<T> nextNode = temp.getNext().getNext();
            temp.setNext(nextNode);
            nextNode.setPrevious(temp);
            size--;
            return removed.getData();
        }
    }

    @Override
    public void addToFront(T t) {
        Node<T> newNode = new Node<T>(t);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            //head.setNext(tail);
            //head.setPrevious(null);
            //tail.setPrevious(head);
            //tail.setNext(null);
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
            //head.setPrevious(null);
        }
        size++;
    }

    @Override
    public void addToBack(T t) {
        Node<T> newNode = new Node<T>(t);
        if (isEmpty()) {
            addToFront(t);
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
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
            head.setPrevious(null);
        }
        size--;
        return removed.getData();
    }

    @Override
    public T removeFromBack() {
        Node<T> removed = tail;
        if (tail == null || size == 0) {
            return null;
        } else if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = removed.getPrevious();
            tail.setNext(null);
        }
        size--;
        return removed.getData();

    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
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
     * You will get a 0 if you do not implement this method.
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
     * You will get a 0 if you do not implement this method.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {
        return tail;
    }
}
