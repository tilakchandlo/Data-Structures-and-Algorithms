public T remove(T data) {
	Node<T> toReturn = new Node<>(null);
	root = remove(root, data, toReturn);
	return toReturn.getData();
}

private Node<T> remove(Node<T> curr, T data, Node<T> toReturn){
	if (curr == null) {
		return null;
	}
	if (data.compareTo(curr.getData()) < 0 ) {
		curr.setLeft(remove(curr.getLeft(), data, toReturn));
	}
	else if (data.compareTo(curr.getData()) > 0) {
		curr.setRight(remove(curr.getRight(), data, toReturn));
	}
	else {
		size--;
		toReturn.setData(curr.getData());

		if (curr.getLeft() != null && curr.getRight() != null) {
			curr.setData(twoChildren(curr));
		}
		else if (curr.getLeft() == null) {
			curr = curr.getRight();
		}
		else{
			curr = curr.getLeft();
		}

	}
	return curr;
}

private T twoChildren(Node<T> curr){
	Node<T> pred = curr.getLeft();
	Node<T> predParent = null;
	while (pred.getRight() != null) {
		predParent = pred;
		pred = pred.getRight();
	}
	T ret = pred.getData();
	if (predParent == null) {
		curr.setLeft(pred.getLeft());
	}
	else {
		predParent.setRight(pred.getLeft());
	}
	return ret;
}