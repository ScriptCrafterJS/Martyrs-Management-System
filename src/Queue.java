
public class Queue {
	private Node front;
	private Node rear;
	private int length;
	
	public class Node {
		Node next;
		NameNode martyr;
		
		public Node(NameNode name) {
			this.martyr = name;
		}
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public void enqueue(NameNode root) {
		Node newNode = new Node(root);
		if(isEmpty()) {// if its empty for the first time
			front = newNode;
		}else {// if not empty the rest will be added at the last (at the rear)
			rear.next = newNode;
			
		}
		rear = newNode;
		length++;
	}
	
	public NameNode dequeue() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Empty Queue");
		}else {
			Node current = front;
			front = front.next;
			current.next = null;
			length--;
			return current.martyr;
		}
	}
	
	public void printQueue() {
		if(isEmpty()) {
			return;
		}else {
			Node current = front;
			while(current != null) {
				System.out.print(current.martyr + " --> ");
				current = current.next;
			}
			System.out.println("null");
		}
	}
}
