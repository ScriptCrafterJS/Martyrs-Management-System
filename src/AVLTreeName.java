
public class AVLTreeName extends BinarySearchTreeName {

	@Override
	public NameNode insert(Martyr martyr, NameNode nameNode) {
		nameNode = super.insert(martyr, nameNode);
		updateHeight(nameNode);
		return reBalance(nameNode);

	}

	@Override
	public NameNode delete(Martyr martyr, NameNode nameNode) {
		nameNode = super.delete(martyr, nameNode);
		if (nameNode == null) {
			return null;
		}
		updateHeight(nameNode);
		return reBalance(nameNode);
	}

	private void updateHeight(NameNode nameNode) {
		int leftChildHeight = height(nameNode.left);
		int rightChildHeight = height(nameNode.right);
		nameNode.height = max(leftChildHeight, rightChildHeight);
	}

	private int max(int height1, int height2) {
		return height1 > height2 ? height1 : height2;
	}

	private int height(NameNode nameNode) {
		return nameNode != null ? nameNode.height : -1;
	}

	NameNode reBalance(NameNode nameNode) {
		int bf = balanceFactor(nameNode);
		if (bf > 1) {
			if (balanceFactor(nameNode.left) >= 0)
				nameNode = rotateRight(nameNode);
			else {
				nameNode.left = rotateLeft(nameNode.left);
				nameNode = rotateRight(nameNode);
			}
		}
		if (bf < -1) {
			if (balanceFactor(nameNode.right) <= 0)
				nameNode = rotateLeft(nameNode.right);
			else {
				nameNode.right = rotateRight(nameNode.right);
				nameNode = rotateLeft(nameNode);
			}
		}
		return nameNode;
	}

	private int balanceFactor(NameNode nameNode) {
		int bf = height(nameNode.left) - height(nameNode.right);
		return bf;
	}

	private NameNode rotateRight(NameNode nameNode) {
		NameNode x = nameNode.left;
		nameNode.left = x.right;
		x.right = nameNode;
		updateHeight(nameNode);
		updateHeight(x);

		return x;
	}

	private NameNode rotateLeft(NameNode nameNode) {
		NameNode x = nameNode.right;
		nameNode.right = x.left;
		x.left = nameNode;
		updateHeight(nameNode);
		updateHeight(x);
		return x;
	}
}

class BinaryBaseTreeName implements BinaryTreeName {

	NameNode root;

	@Override
	public NameNode getRoot() {
		return root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		appendStringToTree(builder, root);
		return builder.toString();
	}

	private void appendStringToTree(StringBuilder builder, NameNode nameNode) {// LPR InOrder
		if (nameNode.left != null) {
			appendStringToTree(builder, nameNode.left);
		}

		appendNode(builder, nameNode);

		if (nameNode.right != null) {
			appendStringToTree(builder, nameNode.right);
		}
	}

	private void appendNode(StringBuilder builder, NameNode nameNode) {
		builder.append(nameNode.martyr.getName() + " ");
	}

	public String levelByLevel() {
		StringBuilder builder = new StringBuilder();
		builder = levelByLevel(builder, root);
		return builder.toString();
	}
	public StringBuilder levelByLevel(StringBuilder builder, NameNode root) {
	    if (root == null) {
	        return null;
	    }
	    Queue queue = new Queue();
	    queue.enqueue(root);

	    while (!queue.isEmpty()) {
	        NameNode node = queue.dequeue();
	        builder.append(node.martyr.toString() + "\n");
	        if (node.left != null) {
	            queue.enqueue(node.left);
	        }
	        if (node.right != null) {
	            queue.enqueue(node.right);
	        }
	    }
	    return builder;
	}


	public void preOrder() {
		StringBuilder builder = new StringBuilder();
		builder = preOrder(builder, root);
		System.out.println(builder.toString());
	}

	private StringBuilder preOrder(StringBuilder builder, NameNode root) {
		if (root != null) {
			builder.append(root.martyr.getName() + " ");
			preOrder(builder, root.left);
			preOrder(builder, root.right);
			return builder;
		} else {
			return null;
		}
	}

	public String inOrder() {
		StringBuilder builder = new StringBuilder();
		builder = inOrder(builder, root);
		return builder.toString();
	}

	private StringBuilder inOrder(StringBuilder builder, NameNode root) {
		if (root != null) {
			inOrder(builder, root.left);
			builder.append(root.martyr.toString() + "\n");
			inOrder(builder, root.right);
			return builder;
		} else {
			return null;
		}
	}

	public int numberOfMartyrs() {
		return numberOfMartyrs(root) + 1;// + 1 to count the first node
	}

	private int numberOfMartyrs(NameNode root) {
		if (root != null) {
			int leftCount = 1 + numberOfMartyrs(root.left);
			int rightCount = 1 + numberOfMartyrs(root.right);
			return leftCount + rightCount;
		} else {
			return -1;// represent that the tree is empty
		}
	}

	public int heihgt() {
		return height(root);
	}

	private int height(NameNode root) {
		if (root != null) {
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);

			return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
		} else {
			return -1; // represent that the tree is empty because the first layer is 0
		}
	}

}

class BinarySearchTreeName extends BinaryBaseTreeName implements BSTIFNAME {

	@Override
	public NameNode findNode(String martyrName) {
		return findNode(martyrName, root);
	}

	NameNode findNode(String martyrName, NameNode nameNode) {// to find a specific name or to get the first one to
																// appear
		if (nameNode == null) {
			return null;
		}
		if (nameNode.martyr.getName().compareToIgnoreCase(martyrName) > 0) {
			nameNode = findNode(martyrName, nameNode.left);
		} else if (nameNode.martyr.getName().compareToIgnoreCase(martyrName) < 0) {
			nameNode = findNode(martyrName, nameNode.right);
		}
		return nameNode;
	}

	public SingleLinkedList findNodes(String marytrName) {// find martyr by part of its name (it will return multiple
															// names)
		SingleLinkedList list = new SingleLinkedList();
		findNodes(list, marytrName, root);
		return list;
	}

	void findNodes(SingleLinkedList list, String martyrName, NameNode nameNode) {
		if (nameNode == null) {
			return;
		}
		if (nameNode.martyr.getName().contains(martyrName)) {
			list.add(nameNode.martyr);
		}
		findNodes(list, martyrName, nameNode.right);
		findNodes(list, martyrName, nameNode.left);
	}

	@Override
	public void insert(Martyr martyr) {
		root = insert(martyr, root);
	}

	NameNode insert(Martyr martyr, NameNode nameNode) {
		if (nameNode == null) {
			nameNode = new NameNode(martyr);
		} else if (martyr.getName().compareToIgnoreCase(nameNode.martyr.getName()) > 0) {
			nameNode.right = insert(martyr, nameNode.right);
		} else {
			nameNode.left = insert(martyr, nameNode.left);
		}

		return nameNode;
	}

	@Override
	public void delete(Martyr martyr) {
		root = delete(martyr, root);
	}

	NameNode delete(Martyr martyr, NameNode nameNode) {
		if (nameNode == null) {
			return null;
		} else if (martyr.getName().compareTo(nameNode.martyr.getName()) > 0) {
			nameNode.right = delete(martyr, nameNode.right);
		} else if (martyr.getName().compareTo(nameNode.martyr.getName()) < 0) {
			nameNode.left = delete(martyr, nameNode.left);
		} else {
			// here to check for the rest of the attributes so we delete the right martyr
			if (martyr.getDataOfDeath().equals(nameNode.martyr.getDataOfDeath())
					&& martyr.getAge() == nameNode.martyr.getAge() && martyr.getGender() == nameNode.martyr.getGender()
					&& martyr.getMaridStatud() == nameNode.martyr.getMaridStatud()) {

				if (nameNode.left == null && nameNode.right == null) {
					nameNode = null;
				} else if (nameNode.left == null) {
					nameNode = nameNode.right;
				} else if (nameNode.right == null) {
					nameNode = nameNode.left;
				} else {
					deleteTwoChildNodes(nameNode);
				}
			}
		}
		return nameNode;
	}

	private void deleteTwoChildNodes(NameNode nameNode) {
		NameNode inOrderSuccessor = findMin(nameNode.right);
		nameNode.martyr = inOrderSuccessor.martyr;
		nameNode.right = delete(inOrderSuccessor.martyr, inOrderSuccessor);
	}

	private NameNode findMin(NameNode nameNode) {
		if (nameNode == null) {
			return null;
		} else if (nameNode.left == null) {
			return nameNode;
		} else {
			return findMin(nameNode.left);
		}
	}
}

interface BinaryTreeName {
	NameNode getRoot();
}

interface BSTIFNAME {

	NameNode findNode(String martyrName);

	void insert(Martyr martyr);

	void delete(Martyr martyr);
}

class NameNode {
	NameNode left, right;
	Martyr martyr;
	int height;

	public NameNode(Martyr martyr) {
		this.martyr = martyr;
	}
}
