

public class SingleLinkedList implements LinkedList {// Sorted by the data of death****

	private Node head;
	private Node tail;
	private int length = 0;

	public class Node implements Comparable<Node> {
		private Martyr obj;
		private Node next;

		public Node(Martyr obj) {
			this.obj = obj;
		}

		public Martyr getObj() {
			return obj;
		}

		public void setObj(Martyr obj) {
			this.obj = obj;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public int compareTo(Node node) {
			return this.obj.getDataOfDeath().compareTo(node.obj.getDataOfDeath());
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

	// search for a given index
	public Martyr getObject(int index) {// complexity of: O(n)
		if (isEmpty()) {
			return null;
		} else if (index == length()) {
			return tail.obj;
		} else if (index == 1) {
			return head.obj;
		} else {
			Node current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			return current.obj;
		}
	}

	// search for a given martyr name
	public Martyr getObject(String name) {// complexity of: O(n)
		if (isEmpty()) {
			return null;
		} else {
			Node current = head;
			while (current != null) {//it will check for the last one and go for null and then out
				if (current.obj.getName().equals(name)) {
					return current.obj;
				}
				current = current.next;
			}
		}
		return null;
	}
	
	//add an object at specifide index
	public void add(Martyr car, int index) {// complexity of: O(n)
		if (isEmpty()) {
			head = tail = new Node(car);
		}
		else if (index > length || index <= 0) {
			addLast(car);
		}else {
			Node current = head;
			for(int i =0;i<index;i++) {//reach before the index and get help to add with newNode 
				current = current.next;
			}
			Node newNode = new Node(car);
			newNode.next = current.next;
			current.next = newNode;
			
		}
		length++;
	}

	// add martyr object for the linked list
	public void add(Martyr obj) {// complexity of: O(n)
		Node newNode = new Node(obj);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			Node current = head;
			Node previous = null;
			while (current != null && current.compareTo(newNode) >= 0) {//if the date comes before && keep moving 
				previous = current;
				current = current.next;
			}
			if (current == null) {// being at the last node
				tail.next = newNode;
				tail = newNode;
			} else if (previous == null) {// being at the first node
				newNode.next = head;
				head = newNode;
			} else {// between the other nodes
				previous.next = newNode;
				newNode.next = current;
			}
		}
		length++;
	}

	public void addFirst(Martyr car) {// complexity of: O(1)
		if (isEmpty()) {
			head = tail = new Node(car);
		} else {
			Node newNode = new Node(car);
			newNode.next = head;
			head = newNode;
		}
		length++;
	}

	public void addLast(Martyr car) {// complexity of: O(1)
		if (isEmpty()) {
			head = tail = new Node(car);
		} else {
			Node newNode = new Node(car);
			tail.next = newNode;
			tail = tail.next;
		}
		length++;
	}

	public void removeFirst() {// complexity of: O(1)
		if (isEmpty()) {
			return;
		}
		Node current = head;
		head = head.next;
		current.next = null;
		current = null;
		length--;
	}

	public void removeLast() {// complexity of: O(n)
		if (isEmpty()) {
			return;
		} else if (length == 1) {
			head = tail = null;
		} else {
			Node current = head;
			while (current.next.next != null) {//standing before the last node
				current = current.next;
			}
			tail = current;
			current.next = null;
		}
		length--;

	}

	// remove a given martyr by his name
	public void remove(String name) {// complexity of: O(n)
		if (isEmpty()) {
			return;
		} else {
			if (name.equals(head.obj.getName())) {// equals the first one
				removeFirst();
			} else if (name.equals(tail.obj.getName())) {// equals the last one
				removeLast();
			} else {// between the rest of the nodes
				Node current = head;
				Node previous = null;
				while (!current.obj.getName().equals(name) && current.next.next != null) {// while not the wanted object and standing before the last node
					previous = current;
					current = current.next;
				}
				if(current.obj.getName().equals(name)) {// to check again for the one before the last
					previous.next = current.next;
					current.next = null;
					current = null;
				}
			}
		}
	}

	// method to check if the list contains the wanted martyr 
	public boolean contains(String marytrName) {// complexity of: O(n)
		if (isEmpty()) {
			return false;
		} else {
			Node current = head;
			while (current != null) {
				if (current.obj.getName().equals(marytrName)) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}

	// print the list
	public void print() {// complexity of: O(n)
		if (isEmpty()) {
			return;
		} else {
			Node current = head;
			while (current != null) {
				System.out.print(current.obj.getName() + " --> ");
				current = current.next;
			}
			System.out.println("null");
		}
	}

	// find the length of the list
	public int length() {
		return length;
	}

	// check if the list is empty or not
	public boolean isEmpty() {
		return length == 0;
	}

}
