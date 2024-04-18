

public class CircularDoublyLinkedList implements LinkedList {// Sorted by the location****

	private Node head;
	private Node tail;
	private int length = 0;

	public class Node implements Comparable<Node> {

		LocationRecord locationRecord;
		Node next;
		Node previous;

		public Node(LocationRecord locationRecord) {
			this.locationRecord = locationRecord;
		}

		public LocationRecord getLocationRecord() {
			return locationRecord;
		}

		public void setLocationRecord(LocationRecord locationRecord) {
			this.locationRecord = locationRecord;
		}
		
		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		@Override
		public int compareTo(Node node) {
			return this.locationRecord.getLocationName().compareToIgnoreCase(node.locationRecord.getLocationName());
		}

	}
	
	public Node getFirst() {
		if(head == null) {
			return null;
		}
		return head;
	}
	public Node getLast() {
		if(tail == null) {
			return null;
		}
		return tail;
	}

	// getting the node by the index
	public Node getNode(int index) {// complexity of: O(n)
		if (isEmpty()) {
			return null;
		} else if (index == length()) {
			return tail;
		} else if (index == 1) {
			return head;
		} else {
			Node current = head;
			for (int i = 1; i < index; i++) {// it will reach the node wanted if 3 move 1 2 steps
				current = current.next;
			}
			return current;
		}
	}

	// getting the node by city name
	public Node getNode(String cityName) {// complexity of: O(n)
		if (isEmpty()) {
			return null;
		} else {
			Node current = head;
			if (current.previous.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {// check for the last node using the previous
				return tail;
			}
			while (current != tail) {// reach the last node and not check it (out of the loop)
				if (current.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {
					return current;
				}
				current = current.next;
			}
		}
		return null;
	}

	// adding node to the doubly linked list
	public void add(LocationRecord locationRecord) {// adding processes depends on the sorting you asked for (alphabetical order)
		 Node newNode = new Node(locationRecord);
		    if (isEmpty()) {
		        head = tail = newNode;
		        head.previous = head;
		        head.next = head;
		    } else if (newNode.compareTo(head) < 0) {//to insert the node before the head
		        newNode.next = head;
		        head.previous = newNode;
		        newNode.previous = tail;
		        tail.next = newNode;
		        head = newNode;
		    } else if (newNode.compareTo(tail) >= 0) {//to insert the node after the tail
		        newNode.next = head;
		        head.previous = newNode;
		        newNode.previous = tail;
		        tail.next = newNode;
		        tail = newNode;
		    } else {
		        Node current = head.next;
		        while (current != tail && newNode.compareTo(current) >= 0) {
		            current = current.next;
		        }
		        newNode.next = current;
		        newNode.previous = current.previous;
		        current.previous.next = newNode;
		        current.previous = newNode;
		    }
		    length++;
	}

	// removing node from the doubly linked list
	public void remove(String cityName) {// complexity of: O(n)
		if (isEmpty()) {
			return;
		} else {
			Node current = head;
			if (length() == 1) {
				head = tail = null;
				current = null;
				length--;
				return;
			}
			while (current != tail) {
				if (current.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {
					if (current == head) {// remove from start
						head.next.previous = tail;
						tail.next = current.next;
						head = head.next;
						current.next = null;
						current.previous = null;
						current = null;
					} else {// remove between the nodes not the last or first
						current.next.previous = current.previous;// the current remove it self from the list
						current.previous.next = current.next;
						current.next = null;
						current.previous = null;
						current = null;
					}
					length--;
					return;
				}
				current = current.next;
			}
			if (current.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {// current removing itself from the tail one
				current.previous.next = head;
				head.previous = current.previous;
				tail = tail.previous;
				current.next = null;
				current.previous = null;
				length--;
			}
			return;
		}

	}

	// contains method
	public boolean contains(String cityName) {// complexity of: O(n)
		if (isEmpty()) {
			return false;
		} else {
			Node current = head;
			if (current.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {//check for the first node
				return true;
			} else if (current.previous.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {// check for the last node
				return true;
			}
			while (current != tail) {// wont check for the last node
				if (current.locationRecord.getLocationName().equalsIgnoreCase(cityName)) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}

	// print the list values
	public void print() {// complexity of: O(n)
		if (isEmpty()) {
			System.out.println("null");
			return;
		} else {
			Node current = head;
			while (current != tail) {
				System.out.print(current.locationRecord.getLocationName() + " --> ");
				current = current.next;
			}
			System.out.print(tail.locationRecord.getLocationName() + " --> ");
		}
		System.out.println("null");
	}

	// check if the list is empty
	public boolean isEmpty() {// complexity of: O(1)
		return length == 0;
	}

	// the length of the list
	public int length() {// complexity of: O(1)
		return length;
	}

	// getters and setters for the data fields of the class****
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

}
