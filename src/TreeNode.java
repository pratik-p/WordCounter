public class TreeNode<T> {
	public T item;
	public TreeNode<T> leftChild;
	public TreeNode<T> rightChild;

	public TreeNode(T newItem) {
		// Initializes tree node with item and no children.
		item = newItem;
		leftChild = null;
		rightChild = null;
	} // end constructor

	public TreeNode(T newItem, TreeNode<T> left, TreeNode<T> right) {
		// Initializes tree node with item and
		// the left and right children references.
		item = newItem;
		leftChild = left;
		rightChild = right;
	} // end constructor

} // end TreeNode
