import java.util.LinkedList;

public class TreeIterator<T> implements java.util.Iterator<T> {
	private BinaryTreeBasis<T> binTree;
	private TreeNode<T> currentNode;
	private LinkedList<TreeNode<T>> queue; // from JCF

	public TreeIterator(BinaryTreeBasis<T> bTree) {
		binTree = bTree;
		currentNode = null;
		// empty queue indicates no traversal type currently
		// selected or end of current traversal has been reached
		queue = new LinkedList<TreeNode<T>>();
	} // end constructor

	public boolean hasNext() {
		return !queue.isEmpty();
	} // end hasNext

	public T next() throws java.util.NoSuchElementException {
		currentNode = queue.remove();
		return currentNode.item;
	} // end next

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	} // end remove

	public void setPreorder() {
		queue.clear();
		preorder(binTree.root);
	} // setPreOrder

	public void setInorder() {
		queue.clear();
		inorder(binTree.root);
	} // end setInorder

	public void setPostorder() {
		queue.clear();
		postorder(binTree.root);
	} // end setPostorder

	private void inorder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			inorder(treeNode.leftChild);
			queue.add(treeNode);
			inorder(treeNode.rightChild);
		} // end if
	} // end inorder

	private void preorder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			queue.add(treeNode);
			preorder(treeNode.leftChild);
			preorder(treeNode.rightChild);
		} // end if
	} // end preorder

	private void postorder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			postorder(treeNode.leftChild);
			postorder(treeNode.rightChild);
			queue.add(treeNode);
		} // end if
	} // end postorder
} // end TreeIterator
