package redblacktree;
/**
 * A red-black tree is a kind of self-balancing binary search tree where each node<br> 
 * has an extra data member, and that member is used to store the color (red or black).<br> 
 * These colors are used to ensure that the tree remains balanced during insertions and<br> 
 * deletions. Although the balance of the tree is not perfect, it is good enough to<br>
 * reduce the searching time and maintain it around O(log n) time, where n is the<br>
 * total number of elements in the tree. This tree was invented in 1972 by Rudolf Bayer. 
 * <p>
 * Red Black Tree maintains balance by ensuring every path from a node (including root)<br> 
 * to any of its descendants NULL nodes has the same number of black nodes.
 * </p>
 *  @author Mayur Prakash Pimpale & Manas Miglani 
 *
 * @param <T> 
 */
public class RedBlackTree<T extends Comparable<? super T>> {
	private  RedBlackNode<T> nullNode;   //define null node   
    private RedBlackNode<T> root;   // define header node  
    
    {   //initialize null Node  
        nullNode = new RedBlackNode<T>(null);
        nullNode.isRed=false;
        nullNode.lChild = nullNode;  
        nullNode.rChild = nullNode;  
    }
    
    /**
     * Red Black Tree is <b>self balanced</b> tree.
     * 
     */
    public RedBlackTree()  
    {  
        this.root = this.nullNode;  
    }// End of Constructor
    
    /**
     * This method will accept value and search it in Red Black Tree and returns the reference<br>
     * to the node which has the value or <b>null</b> if not found.
     * @param item : value to be searched
     * @return reference to node if element is found 
     * @exception RuntimeException if tree is empty
     */
    public RedBlackNode<T> find(T item)
    {
    	RedBlackNode<T> current;
    	// Check tree is empty
    	if(root == nullNode) {
    		throw new RuntimeException("Tree is Empty !!!!");
    	}
    	
    	current = root; 									//Set current to root
    	while(!current.equals(nullNode))   					//Continue till nullNode
    	{
    		if(item.compareTo(current.data)==0)				//if item = data=> current node is required node
    		{
        		return current;
        	}
    		else if(item.compareTo(current.data)<0) 		//if item is less than current's data then go to left child of current
    		{
    			current = current.lChild;
    		}
    		else  	 										//if item is greater than current's data then go to right child of current
    		{
    			current = current.rChild;
    		}
    	}
    	return null;
    } //End of Find
    
    /**
     * This method will accept value which has to be inserted in Red Black Tree.<br>
     *  This method insert's the accepted value at it's <b>proper position with appropriate node color</b>.<br>
     * @param key : value to insert
     */
    public void insert(T key) 
    {
    	RedBlackNode<T> temp,current,parent;
    	//initialize
    	parent = nullNode;
    	current = root;
    	
    	//Loop to find the location in RBT for new node 
    	while(!current.equals(nullNode)) 
    	{
    		parent=current;
    		//if key is less than current's data than left
    		if(key.compareTo(current.data)<0)
    			current=current.lChild;
    		//if key is greater than current's data than right
    		else if(key.compareTo(current.data)>0)
    			current=current.rChild;
    		//if key is equal to current's data than throw exception for duplicate entry
    		else
    			throw new RuntimeException("Duplicate Entry");
    	} // End of while
    	
    	//Allocate memory to new Node.
    	temp = new RedBlackNode<T>(key);
    	//set the values for new node
    	temp.lChild = nullNode;
    	temp.rChild = nullNode;
    	//Every node in Red Black tree is initially Red
    	temp.isRed = true; 					
    	temp.parent = parent;
    	
    	//if parent is null => set root to temp
    	if(parent == nullNode)
    		root= temp;
    	//if temp data is less then parent data => set temp to parent lchild	
    	else if(temp.data.compareTo(parent.data) < 0) 	
    		parent.lChild = temp;
    	//if temp data is less then parent data => set temp to parent lchild	
    	else										  	
    		parent.rChild = temp;
    	//call insert balance function to balance the red black tree
    	insertBalance(temp);
    } // End of Insert 
    
    
    /**
     * This method is helper method for insert Method.
     * @param rbNode
     */
    private void insertBalance(RedBlackNode<T> rbNode) 
    {
    	RedBlackNode<T> uncle,parent,grandParent;
    	while(rbNode.parent.isRed) 
    	{
    		parent = rbNode.parent;
    		grandParent = parent.parent;
    		// if parent is left child of grand parent
    		if(parent.equals(grandParent.lChild)) { 
    			uncle = grandParent.rChild;
    			//if uncle is red.
    			if(uncle.isRed) 
    			{
    				parent.isRed=false;
    				uncle.isRed=false;
    				grandParent.isRed=true;
    				rbNode = grandParent;
    			}
    			else 
    			{ 
    				// uncle is black
    				if(rbNode.data.equals(parent.rChild.data)) 
    				{
    					//rotate left around parent
    					rotateLeft(parent);
    					rbNode=parent;
    					parent=rbNode.parent;
    				} 
    				parent.isRed=false;
    				grandParent.isRed=true;
    				//rotate right around grand parent node
    				rotateRight(grandParent);
    			} 
    		} 
    		else 
    		{
    			// if parent is right child of grand parent
    			if(parent.equals(grandParent.rChild)) 
    			{
    				uncle = grandParent.lChild;
    				// uncle is red
    				if(uncle.isRed) 
    				{
    					parent.isRed=false;
    					uncle.isRed = false;
    					grandParent.isRed=true;
    					rbNode=grandParent;
    				}
    				else 
    				{
    					if(rbNode.equals(parent.lChild)) 
    					{
    						//rotate right around parent node
    						rotateRight(parent);
    						rbNode=parent;
    						parent=rbNode.parent;
    					}
    					parent.isRed=false;
    					grandParent.isRed=true;
    					//rotate left around grand parent node
    					rotateLeft(grandParent);
    				}
    			}
    		}
    	}
    	root.isRed=false;
    }// End of InsertBalance
    
    
    /**
     * This method accepts a value to delete. First, it deletes the node <br>
     * and then balances the Red Black Tree if required. 
     * @param item : value to be deleted from the Red Black Tree.
     * @return <b>True</b> on successfully deleting the node, otherwise <b>False</b>.
     */
    public boolean delete(T item) {
    	RedBlackNode<T> current,child,successor;
    	
    	//first find if item in Red Black Tree
    	current = find(item);
    	
    	//If item is not found return false
    	if(current == null){
    		return false;
    	}
    	
    	//if no child node is nullNode
    	if(!current.lChild.equals(nullNode) && !current.rChild.equals(nullNode)) 
    	{
    		//find the successor of current node
    		successor = successor(current);
    		//interchange data of successor to current's data
    		current.data = successor.data;
    		//set current to successor
    		current = successor;
    	}
    	
    	//if lChild is not nullNode then child = lChild 
    	//else child = rChild
    	if(!current.lChild.equals(nullNode))
    		child = current.lChild;
    	else
    		child = current.rChild;
    	//set child's parent = current's parent
    	child.parent = current.parent;
    	//current's parent is nullNode => set current to root
    	if(current.parent.equals(nullNode))
    		root=child;
    	else if(current.equals(current.parent.lChild))
    		current.parent.lChild = child;
    	else
    		current.parent.rChild =  child;
    	
    	//If child is root set child's color to black
    	if(child.equals(root))
    		child.isRed = false;
    	else if(!current.isRed) 
    	{
    		if(!child.equals(nullNode))
    			child.isRed=false;
    		else
    			delBalance(child); //balance tree
    	}
    	return true;
    } // End of delete
    
    /**
     * This is a helper method of delete method. After a node gets deleted this method<br> 
     * will perform balancing of Red Black Tree.
     * @param rbNode
     */
    private void delBalance(RedBlackNode<T> rbNode) {
    	RedBlackNode<T> sibling;
    	
    	//Run till whole tree is balanced 
    	while(!rbNode.equals(root)) 
    	{
    		//if rbNode is left child
    		if(rbNode.equals(rbNode.parent.lChild)) 
    		{
    			sibling = rbNode.parent.rChild;
    			//if rbNode's sibling is red
    			if(sibling.isRed) 
    			{
    				sibling.isRed = false;
    				rbNode.parent.isRed = true;
    				//rotateLeft around rbNode's parent
    				rotateLeft(rbNode.parent);
    				sibling= rbNode.parent.rChild;
    			}
    			//if sibling's both child are black
    			if(!sibling.lChild.isRed && !sibling.rChild.isRed) 
    			{
    				sibling.isRed = true;
    				//if rbNode's parent is red
    				if(rbNode.parent.isRed) 
    				{
    					//set rbNode's parent color to black
    					rbNode.parent.isRed=false;
    					return;
    				}
    				else
    					rbNode=rbNode.parent;
    			}
    			else 
    			{
    				if(!sibling.rChild.isRed) 
    				{
    					sibling.lChild.isRed=false;
    					sibling.isRed=true;
    					//rotateRight around sibling
    					rotateRight(sibling);
    					sibling=rbNode.parent.rChild;
    				}
    				sibling.isRed=rbNode.parent.isRed;
    				rbNode.parent.isRed = false;
    				sibling.rChild.isRed = false;
    				//rotateLeft around parent
    				rotateLeft(rbNode.parent);
    				return;
    			}
    		}
    		else 
    		{
    			sibling=rbNode.parent.lChild;
    			//if rbNode's sibling is red
    			if(sibling.isRed) 
    			{
    				sibling.isRed = false;
    				rbNode.parent.isRed = true;
    				//rotateRight around parent
    				rotateRight(rbNode.parent);
    				sibling= rbNode.parent.lChild;
    			}
    			//if sibling's both child are black
    			if(!sibling.lChild.isRed && !sibling.rChild.isRed) 
    			{
    				sibling.isRed=true;
    				//if rbNode's parent is red
    				if(rbNode.parent.isRed) 
    				{
    					rbNode.parent.isRed=false;
    					return;
    				}
    				else
    					rbNode = rbNode.parent;
    			}
    			else {
    				if(!sibling.lChild.isRed) 
    				{
    					sibling.rChild.isRed=false;
    					sibling.isRed=true;
    					//rotateLeft around sibling
    					rotateLeft(sibling);
    					sibling=rbNode.parent.lChild;
    				}
    				sibling.isRed=rbNode.parent.isRed;
    				rbNode.parent.isRed= false;
    				sibling.lChild.isRed = false;
    				//rotateRight around parent
    				rotateRight(rbNode.parent);
    				return;
    			}
    		}
    	}
    } // End of del_balnce
    
    //In-order successor => minimum value in right sub tree
    private RedBlackNode<T> successor(RedBlackNode<T> rbNode){
    	RedBlackNode<T> succ = rbNode.rChild;
    	while(succ.lChild.data != null) 
    	{
    		succ = succ.lChild;
    	}
    	return succ;
    } // End of successor 
    
    //helper function
    private void rotateLeft(RedBlackNode<T> rbNode) {
    	RedBlackNode<T> temp;
    	
    	//set temp to rbNode's right child 
    	temp=rbNode.rChild;
    	//set rbNode's right child to temp's lChild
    	rbNode.rChild=temp.lChild;
    	//if left child is not nullNode => set left child's parent to rbNode
    	if(!temp.lChild.equals(nullNode))
    		temp.lChild.parent=rbNode;
    	temp.parent=rbNode.parent;
    	//if rbnode's parent is nullNode set root to temp
    	if(rbNode.parent.equals(nullNode))
    		root=temp;
    	else if(rbNode.equals(rbNode.parent.lChild))
    		rbNode.parent.lChild=temp;
    	else
    		rbNode.parent.rChild=temp;
    	temp.lChild=rbNode;
    	rbNode.parent=temp;
    } // End of rotateLeft
    
    //helper function
    private void rotateRight(RedBlackNode<T> rbNode) {
    	RedBlackNode<T> temp;
    	
    	//set temp to rbNode's left child 
    	temp=rbNode.lChild;
    	//set rbNode's left child to temp's right Child
    	rbNode.lChild=temp.rChild;
    	//if right child is nullNode
    	if(!temp.rChild.equals(nullNode))
    		temp.rChild.parent=rbNode;
    	temp.parent=rbNode.parent;
    	//if rbnode's parent is nullNode set root to temp
    	if(rbNode.parent.equals(nullNode))
    		root=temp;
    	else if(rbNode.equals(rbNode.parent.rChild))
    		rbNode.parent.rChild=temp;
    	else
    		rbNode.parent.lChild=temp;
    	temp.rChild=rbNode;
    	rbNode.parent=temp;
    } // End of rotateRight
    
    /**
     * This method display in-order traversal of Red Black Tree.
     */
    public void inOrder() {
    	inOrderHelp(root);
    } // End of inOrder
    
    //helper function for in-order
    private void inOrderHelp(RedBlackNode<T> rbNode) {
    	if(rbNode.data!=null) {
    		inOrderHelp(rbNode.lChild);
    		System.out.println(rbNode.data+"-"+(rbNode.isRed?"R":"B")+ " ");
    		inOrderHelp(rbNode.rChild);
    	}
    } // End of inOrderHelp       
}
