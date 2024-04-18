
public class Stack {
	
	private Node top;
	private int length;

	class Node {
		Node next;
		Martyr martyr;

		public Node(Martyr martyr) {
			this.martyr = martyr;
		}
	}
	
	public int length() {
		return length;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public void push(Martyr martyr) {
		Node newNode = new Node(martyr);
		newNode.next = top;
		top = newNode;
		length++;
	}

	public Martyr pop() {// returning the popped element
		if (isEmpty()) {
			throw new IllegalArgumentException("Empty Stack");
		} else {
			Node temp = top;
			top = top.next;
			temp.next = null;
			length--;
			return temp.martyr;
		}
	}

	public Martyr peek() {
		if (isEmpty()) {
			return null;
		} else {
			return top.martyr;
		}
	}

	public void print() {
		if (isEmpty()) {
			return;
		} else {
			Node current = top;
			while (current != null) {
				System.out.println(current.martyr + " ");
				current = current.next;
			}
		}
	}
}
