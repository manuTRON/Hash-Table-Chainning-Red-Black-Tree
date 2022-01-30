package tester;

import hashtable.*;
import java.util.Scanner;

public class HashTableUsingRBTMain 
{

	public static void main(String[] args) 
	{			
		try(Scanner sc = new Scanner(System.in))
		{
			HashTableChainingUsingRBT<Mobile> hashTable = new HashTableChainingUsingRBT<>(5);
			
			//Populate hash table 
			for(int i=0; i<20; i++)
			{
				hashTable.insert(new Mobile(123456789+(i+1), String.format("model%d", i), String.format("brand%d", i), 250*i+1));			
			}
			System.out.println("#########################");
			System.out.println("#     W E L C O M E     #");
			System.out.println("#########################");
			
			boolean exit = false;
			while(!exit)
			{
				System.out.println("1. Insert\r\n"
						+ "2. Delete\r\n"
						+ "3. Search\r\n"
						+ "4. Display\r\n"
						+ "0. Exit");
				System.out.println("=====================");
				try
				{
					switch(sc.nextInt())
					{
						case 1:
							System.out.println("Enter the Details of Mobile: imeiNo, modelNo, brandNo, price");
							hashTable.insert(new Mobile(sc.nextLong(), sc.next(), sc.next(), sc.nextDouble()));
							System.out.println("Data Inserted Successfully!!");
							break;
							
						case 2:
							System.out.println("Enter the IMEI No to be Deleted: ");
							System.out.println(hashTable.delete(new Mobile(sc.nextLong())));
							break;
							
						case 3:
							System.out.println("Enter the IMEI No to be Searched: ");
							System.out.println(hashTable.search(new Mobile(sc.nextLong())));
							break;
							
						case 4:
							hashTable.display();
							break;
							
						case 0:
							exit = true;
							System.out.println("#########################");
							System.out.println("#    T H A N K Y O U    #");
							System.out.println("#########################");
							break;
						default:
							System.out.println("Invalid Selection !!!!!!");
							break;
					}
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				sc.nextLine();
			}	
		}
	}

}
