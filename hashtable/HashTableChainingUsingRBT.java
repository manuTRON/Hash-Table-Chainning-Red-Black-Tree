package hashtable;

import redblacktree.*;
import java.lang.reflect.Array;

public class HashTableChainingUsingRBT<T extends Comparable<? super T>>  
{
	private RedBlackTree<T>[] hashTable;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashTableChainingUsingRBT(int size)
	{
		this.hashTable = (RedBlackTree<T>[]) Array.newInstance((RedBlackTree.class), size);
		this.size = size;
	}
	
	// Hash Function
	private int hashFunction(int  element)
	{
		return element % size;
	}
	
	/**
	 * This method inserts an element into the hash table.
	 * @param element
	 */
	public void insert(T element)
	{
		// get id
		int id = hashFunction(element.hashCode());
		
		// Create RBT if doesnot Exists
		if(hashTable[id] == null)
			hashTable[id] = new RedBlackTree<T>();
		
		// insert into RBT

		hashTable[id].insert(element);
	} // End of insert
	
	/**
	 * This method deletes an element from hash table
	 * @param element
	 * @return True if element is deleted and False if element is not found.
	 */
	public boolean delete(T element)
	{
		// get id
		int id = hashFunction(element.hashCode());
		
		// if nothing is stored in location
		if(hashTable[id] == null)
			return false;
		
		// delete from RBT
		return hashTable[id].delete(element);
	} // End of delete
	
	public boolean search(T element)
	{
		// get id
		int id = hashFunction(element.hashCode());
		
		// if no bucket initialized at id
		if(hashTable[id] == null)
			return false;
		
		// search from RBT
		return !(hashTable[id].find(element) == null); 
	} // End of search
	
	/**
	 * This function displays the data in the buckets of the hash table via Inorder Traversal. 
	 */
	public void display()
	{
		for(int i=0; i<size; i++)
		{
			System.out.println("Bucket"+i+" : ");
			hashTable[i].inOrder();
			System.out.println();
		}
	} // End of display
	
}
