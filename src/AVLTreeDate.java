import java.text.SimpleDateFormat;
import java.util.Date;

public class AVLTreeDate extends BinarySearchTreeDate{

	@Override
	public DateNode insert(Martyr martyr, DateNode dateNode) {
		dateNode = super.insert(martyr,dateNode);
		updateHeight(dateNode);
		return reBalance(dateNode);

	}

	@Override
	public DateNode delete(Date date, DateNode dateNode){
		dateNode = super.delete(date, dateNode);
		if(dateNode == null) {
			return null;
		}
		updateHeight(dateNode);
		return reBalance(dateNode);
	}
	
	@Override
	public DateNode delete(Martyr martyr, DateNode dateNode){
		dateNode = super.delete(martyr, dateNode);
		if(dateNode == null) {
			return null;
		}
		updateHeight(dateNode);
		return reBalance(dateNode);
	}

	private void updateHeight(DateNode dateNode){
		int leftChildHeight = height(dateNode.left);
		int rightChildHeight = height(dateNode.right);
		dateNode.height = max(leftChildHeight, rightChildHeight);
	}

	private int max(int height1, int height2){
		return height1 > height2 ? height1 : height2;
	}

	private int height(DateNode dateNode){
		return dateNode != null ? dateNode.height : -1;
	}

	DateNode reBalance(DateNode dateNode){
		int bf = balanceFactor(dateNode);
		if(bf > 1){
			if(balanceFactor(dateNode.left) >= 0)
				dateNode = rotateRight(dateNode);
			else{
				dateNode.left = rotateLeft(dateNode.left);
				dateNode = rotateRight(dateNode);
			}
		}
		if(bf < -1){
			if(balanceFactor(dateNode.right) <= 0)
				dateNode = rotateLeft(dateNode.right);
			else{
				dateNode.right = rotateRight(dateNode.right);
				dateNode = rotateLeft(dateNode);
			}
		}
		return dateNode;
	}

	private int balanceFactor(DateNode dateNode){
		int bf = height(dateNode.left) - height(dateNode.right);
		return bf;
	}

	private DateNode rotateRight(DateNode dateNode){
		DateNode x = dateNode.left;
		dateNode.left = x.right;
		x.right = dateNode;
		updateHeight(dateNode);
		updateHeight(x);

		return x;
	}

	private DateNode rotateLeft(DateNode dateNode){
		DateNode x = dateNode.right;
		dateNode.right = x.left;
		x.left = dateNode;
		updateHeight(dateNode);
		updateHeight(x);
		return x;
	}
}

class BinaryBaseTreeDate implements BinaryTreeDate {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
	DateNode root;

	@Override
	public DateNode getRoot() {
		return root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		appendStringToTree(builder, root);
		return builder.toString();
	}

	private void appendStringToTree(StringBuilder builder, DateNode dateNode) {// LPR InOrder
		if (dateNode.left != null) {
			appendStringToTree(builder, dateNode.left);
		}

		appendNode(builder, dateNode);

		if (dateNode.right != null) {
			appendStringToTree(builder, dateNode.right);
		}
	}

	private void appendNode(StringBuilder builder, DateNode dateNode) {
		String formattedDate = dateFormat.format(dateNode.dateStack.getDate());
		builder.append(formattedDate + " ");
	}

	public void preOrder() {
		StringBuilder builder = new StringBuilder();
		builder = preOrder(builder, root);
		System.out.println(builder.toString());
	}

	private StringBuilder preOrder(StringBuilder builder, DateNode root) {
		if (root != null) {
			String formattedDate = dateFormat.format(root.dateStack.getDate());
			builder.append(formattedDate + " ");
			preOrder(builder, root.left);
			preOrder(builder, root.right);
			return builder;
		} else {
			return null;
		}
	}

	public void inOrder() {
		StringBuilder builder = new StringBuilder();
		builder = inOrder(builder, root);
		System.out.println(builder.toString());
	}

	private StringBuilder inOrder(StringBuilder builder, DateNode root) {
		if (root != null) {
			inOrder(builder, root.left);
			String formattedDate = dateFormat.format(root.dateStack.getDate());
			builder.append(formattedDate + "\n");
			inOrder(builder, root.right);
			return builder;
		} else {
			return null;
		}
	}

	public String inOrderBackward() {
		StringBuilder builder = new StringBuilder();
		builder = inOrderBackward(builder, root);
		return builder.toString();
	}

	private StringBuilder inOrderBackward(StringBuilder builder, DateNode root) {// we have to append the whole info
																					// about the martyrs
		if (root != null) {
			inOrderBackward(builder, root.right);
			Stack tempStack = new Stack();
			while (!root.dateStack.getMartyrsStack().isEmpty()) {
				Martyr martyr = root.dateStack.getMartyrsStack().pop();
				builder.append(martyr.toString() + "\n");// *** here we have to append the martyr info
				tempStack.push(martyr);
			}

			inOrderBackward(builder, root.left);
			while (!tempStack.isEmpty()) {// to bring them back to the original stack
				root.dateStack.getMartyrsStack().push(tempStack.pop());
			}
			return builder;
		} else {
			return null;
		}
	}

	public MaxMartyrsInfo findMaximumNumber() {
		return findMaximumNumber(root);
	}

	private MaxMartyrsInfo findMaximumNumber(DateNode root) {
		if (root != null) {
			int maxNumber = root.dateStack.getMartyrsStack().length();
			Date maxDate = root.dateStack.getDate();
			
			if (root.left != null) {
				MaxMartyrsInfo left = findMaximumNumber(root.left);
				if (left.getMaxNumber() > maxNumber) {
					maxNumber = left.getMaxNumber();
					maxDate = left.getMaxDate();
				}
			}
			
			if (root.right != null) {
				MaxMartyrsInfo right = findMaximumNumber(root.right);
				if (right.getMaxNumber() > maxNumber) {
					maxNumber = right.getMaxNumber();
					maxDate = right.getMaxDate();
				}
			}
			return new MaxMartyrsInfo(maxNumber, maxDate);
		}else {
			throw new IllegalArgumentException("Tree is Empty");
		}
	}
	
	public int heihgt() {
		return height(root);
	}
	
	private int height(DateNode root) {
		if(root != null) {
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			return leftHeight >= rightHeight ? leftHeight+1 : rightHeight+1;
		}else {
			return -1; //represent that the tree is empty because the first layer is 0
		}
	}
}

class BinarySearchTreeDate extends BinaryBaseTreeDate implements BSTIFDATE {

	@Override
	public DateNode findNode(Date date) {
		return findNode(date, root);
	}

	DateNode findNode(Date date, DateNode dateNode) {// to find a specific name or to get the first one to
														// appear
		if (dateNode == null) {
			return null;
		}
		if (date.compareTo(dateNode.dateStack.getDate()) < 0) {
			dateNode = findNode(date, dateNode.left);
		} else if (date.compareTo(dateNode.dateStack.getDate()) > 0) {
			dateNode = findNode(date, dateNode.right);
		}
		return dateNode;
	}

	@Override
	public void insert(Martyr martyr) {
		root = insert(martyr, root);
	}

	DateNode insert(Martyr martyr, DateNode dateNode) {
		if (dateNode == null) {
			DateStack dateStack = new DateStack(martyr.getDataOfDeath());
			dateStack.getMartyrsStack().push(martyr);
			dateNode = new DateNode(dateStack);
		} else if (martyr.getDataOfDeath().compareTo(dateNode.dateStack.getDate()) > 0) {
			dateNode.right = insert(martyr, dateNode.right);
		} else if (martyr.getDataOfDeath().compareTo(dateNode.dateStack.getDate()) < 0) {
			dateNode.left = insert(martyr, dateNode.left);
		} else {
			dateNode.dateStack.getMartyrsStack().push(martyr);
		}

		return dateNode;
	}

	@Override
	public void delete(Date date) {
		root = delete(date, root);
	}

	DateNode delete(Date date, DateNode dateNode) {
		if (dateNode == null) {
			return null;
		} else if (date.compareTo(dateNode.dateStack.getDate()) > 0) {
			dateNode.right = delete(date, dateNode.right);
		} else if (date.compareTo(dateNode.dateStack.getDate()) < 0) {
			dateNode.left = delete(date, dateNode.left);
		} else if (dateNode.left == null && dateNode.right == null) {
			dateNode = null;
		} else if (dateNode.left == null) {
			dateNode = dateNode.right;
		} else if (dateNode.right == null) {
			dateNode = dateNode.left;
		} else {
			deleteTwoChildNodes(dateNode);
		}

		return dateNode;

	}
	
	@Override
	public void delete(Martyr martyr) {
	    root = delete(martyr, root);
	}

	DateNode delete(Martyr martyr, DateNode dateNode) {
	    if (dateNode == null) {
	        return null;
	    } else if (martyr.getDataOfDeath().compareTo(dateNode.dateStack.getDate()) > 0) {
	        dateNode.right = delete(martyr, dateNode.right);
	    } else if (martyr.getDataOfDeath().compareTo(dateNode.dateStack.getDate()) < 0) {
	        dateNode.left = delete(martyr, dateNode.left);
	    } else {
	        deleteFromStack(dateNode.dateStack.getMartyrsStack(), martyr);
	        
	        if (dateNode.dateStack.getMartyrsStack().isEmpty()) {// when there is no martyrs left then delete the whole node
	            if (dateNode.left == null) {
	                return dateNode.right;
	            } else if (dateNode.right == null) {
	                return dateNode.left;
	            } else {
	                deleteTwoChildNodes(dateNode);
	            }
	        }
	    }

	    return dateNode;
	}

	private void deleteFromStack(Stack stack, Martyr martyr) {// delete the right martyr from the stack
	    Stack tempStack = new Stack();
	    Martyr martyrPoped = stack.pop();
	    while(!martyr.equals(martyrPoped)) {
	    	tempStack.push(martyrPoped);
	    	martyrPoped = stack.pop();
	    }
	    martyrPoped = null;
	    while(!tempStack.isEmpty()) {
	    	stack.push(tempStack.pop());
	    }
	}

	private void deleteTwoChildNodes(DateNode dateNode) {
		DateNode inOrderSuccessor = findMin(dateNode.right);
		dateNode.dateStack = inOrderSuccessor.dateStack;
		dateNode.right = delete(inOrderSuccessor.dateStack.getDate(), inOrderSuccessor);
	}

	private DateNode findMin(DateNode dateNode) {
		if (dateNode == null) {
			return null;
		} else if (dateNode.left == null) {
			return dateNode;
		} else {
			return findMin(dateNode.left);
		}
	}
}

interface BinaryTreeDate {
	DateNode getRoot();
}

interface BSTIFDATE {
	
	DateNode findNode(Date date);

	void insert(Martyr martyr);

	void delete(Date date);
	
	void delete(Martyr martry);
}

class DateNode {
	DateNode left,right;
	DateStack dateStack;
	int height;
	public DateNode(DateStack dateStack) {
		this.dateStack = dateStack;
	}
}
