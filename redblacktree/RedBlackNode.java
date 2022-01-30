package redblacktree;


public class RedBlackNode<T extends Comparable<? super T>> {
	T data;
	boolean isRed;					// true for red node and false for black node
	RedBlackNode<T> lChild;			// Left child Node
	RedBlackNode<T> rChild;			// Right child Node
	RedBlackNode<T> parent;			// Parent Node
	
	public RedBlackNode(T data) {		// create a node with data and initial isRed = true
		this.data = data;
		this.isRed = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		// if both object has null store in data then return true, needed for nullNode or external node or extended node.
		RedBlackNode<T> rbObj = (RedBlackNode<T>)obj;
		if(this.data == null) 
		{
			if(rbObj.data == null)
				return true; 
			else
				return false;
		}
		if(rbObj.data == null) 
		{
			if(this.data == null)
				return true;
			else 
				return false;
		}
		return this.data.equals(rbObj.data);
	} // End of Equals
}