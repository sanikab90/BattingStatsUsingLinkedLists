/*
 * Sanika Buche ssb170002
 * 
 * This class holds a head node, current node and a second node which are used for various things in the class
 * Functions going down:
 * Adds a player object to the list
 * Searches through the list for an object
 * Removes a node at a specific index
 * Prints the list recursively
 * Sorts list alphabetically
 * Swap nodes from list
 * Sorts league leaders
 * Finds leage leaders
 * Printing league leaders
 */
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class LinkList {
	private Node<Player> head;
	private Node<Player> current; 
	private Node<Player> second; 
	//public static LinkedList list;
	
	public LinkList() {
		//empty list
		head = null;
		current = null;
	}
	
	//getters
	public Node<Player> getHead(){return head;}
	public Node<Player> getCurrent(){return current;}
	public Node<Player> getSecond() {return second;}
	
	//setters
	public void setHead(Node<Player> h) {this.head = h;}
	public void setCurrent(Node<Player> c) {this.current = c;}
	public void setSecond(Node<Player> s) {this.second = s;}
	
	/*
	 * A function that adds a node to the end of the linked list
	 * 
	 * parameter: Player obj - object to place in linked list
	 * returns: void
	 */
	public void add(Player obj) {
		//create node obj
		Node<Player> temp = new Node<Player>(obj);
		
		//if there is 1 node set temp to the head
		//and set next to null
		if(Node.getNodeCount() == 1) {
			head = temp;
			temp.setNext(null);
		}
		
		//if there are 2 nodes set head.next to memory address of temp
		else if(Node.getNodeCount() == 2) {
			head.setNext(temp);
			//set temp.next to null
			temp.setNext(null);
		}
			
		//if there is more than 2 node
		else if(Node.getNodeCount() > 2) {
			//set current to head of the list
			current = head;
			//go to the last node
			while(this.current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(temp);
			//set temp.next to null
			temp.setNext(null);
		}
	}
	
	/*
	 * A function that searches through the list and looks for a player object
	 * 
	 * parameter: Player obj - object whos index to find
	 * returns: int - -1 if object isn't found and the index of the list where the player is
	 */
	public int search(Player obj) {
		current = head;
		int i = 0;
		
		while(current != null) {
			if(current.getData().compareTo(obj) == 1)
				return i;
			current = current.getNext();
			i++;
		}
		return -1;
	}
	
	/*
	 * A function that searchs through the list and removes the node at said index
	 * 
	 * parameter: int index - index at which to remove the node
	 * returns: void
	 */
	public void remove(int index) {
		//if linklist is empty
		if(head == null)
			return;
		
		//store head node
		current = head;
		
		//if head position is to be removed
		if(index == 0) {
			head = current.getNext(); //change head
			return;
		}
		
		//find previous node of the node to be deleted
		for(int i = 0; current != null && i < index - 1; i++) {
			current = current.getNext();
		}
		
		//if position is more than number of nodes
		if(current == null || current.getNext() == null)
			return;
		
		//current->next is the node to be deleted
		second = current.getNext().getNext();
		
		current = second; //unlink the deleted node
	}
	
	
	/*
	 * A fucntion that prints the list recursively
	 * 
	 * parameter: Node<Player> obj - the node to print
	 * 			  PrintWriter p - printwriter object to print to a file
	 */
	public void printList(Node<Player> obj, PrintWriter p) {
		p.print(obj.getData().getName() + "\t" + (int)obj.getData().atBats() + "\t" + obj.getData().getHit() + "\t" + obj.getData().getWalk() + "\t" 
			    + obj.getData().getStrikeout() + "\t" + obj.getData().getHitByPitch() + "\t" + obj.getData().getSacrifice() + "\t");
				p.print(String.format("%.3f", obj.getData().battingAverage()) + "\t");
				p.println(String.format("%.3f", obj.getData().onBasePercentage()));
		
		if(obj.getNext() != null)
			printList(obj.getNext(), p);
	}
	
	/*
	 * A function that sorts the list alphabetically using a selection sort
	 * 
	 * parameter: none
	 * return: void
	 */
	
	//sort based on name of character
	public void sortName() {
		String first, smallestVal, temp;
		
		//set current to head
		current = this.head;
		second = current.getNext();
		Node<Player> element2swap = null;
		
		//loop through list until you get "smallest" value
		while(current != null) {
			//set first to current name
			first = current.getData().getName();
			//set smallestVal to first
			smallestVal = first;
			
			//loop until second != null
			while(second != null) {
				//set temp to second name
				temp = second.getData().getName();
				
				//compare temp and smallest val
				if(temp.toUpperCase().compareTo(smallestVal.toUpperCase()) < 0) {
					//if temp < smallesVal set smallestVal to temp adn keep going
					smallestVal = temp;
					element2swap = second;
				}
				//increment second
				second = second.getNext();
			}
			//if the smallest value isn't still equal to the first string swap nodes
			if(!(smallestVal.toUpperCase().equals(first.toUpperCase()))) {
				//swap nodes
				swap(current, element2swap); //does this work?
			}
			//increment current
			current = current.getNext();
			//if current isn't null increment second
			if(current != null)
				second = current.getNext();
		}
	}
	
	/*
	 * A function that swaps 2 objects in the list
	 * 
	 * parameter: Node<Player> n1 and n2 - the two nodes to swap
	 * returns: void
	 */
	
	public void swap(Node<Player> n1, Node<Player> n2) {
		//swap player objects inside each node
		Player temp = n1.getData();
		n1.setData(n2.getData());
		n2.setData(temp);
	}
	
	/*
	 * A function that sorts the list based on which leader we need to print out
	 * 
	 * parameter: double highest - the highest value to find
	 * 			  char category - character passed in in order to know which leader we are sorting by
	 * return: double - highest value in list
	 */
	
	public double sortLeaders(double highest, char category) {
		current = head;
		second = current.getNext();
		Node<Player> element2swap = null;
		
		switch (category) {
		
		case 'b':
			sortName();
			
			current = head;
			second = current.getNext();
			while(current != null) {
				
				highest = current.getData().battingAverage();
				
				while(second != null) {
					if(second.getData().battingAverage() > highest) {
						//if next > highest set it and continue
						highest = second.getData().battingAverage();
						element2swap = second;
					}
					//increment next
					second = second.getNext();
				}
				//if the highest value isn't still equal to the first number swap nodes
				if(!(highest == current.getData().battingAverage()))
					//swap nodes
					swap(current, element2swap);
				//increment current
				current = current.getNext();
				
				if(current != null)
					second = current.getNext();
			}
			
			return head.getData().battingAverage();
			
		case 'o':
			sortName();
			
			current = head;
			second = current.getNext();
			
			
			while(current != null) {
				highest = current.getData().onBasePercentage();
				while(second != null) {
					if(second.getData().onBasePercentage() > highest) {
						//if next > highest set it and continue
						highest = second.getData().onBasePercentage();
						element2swap = second;
					}
					//increment next
					second = second.getNext();
				}
				//if the highest value isn't still equal to the first number swap nodes
				if(!(highest == current.getData().onBasePercentage()))
					//swap nodes
					swap(current, element2swap);
				//increment current
				current = current.getNext();
				if(current != null)
					second = current.getNext();
			}
			
			return head.getData().onBasePercentage();
			
		case 'h':
			sortName();
			
			current = head;
			second = current.getNext();
			
		while(current != null) {
			highest = current.getData().getHit();
			while(second != null) {
				if(second.getData().getHit() > highest) {
					//if next > highest set it and continue
					highest = second.getData().getHit();
					element2swap = second;
				}
				//increment next
				second = second.getNext();
			}
			//if the highest value isn't still equal to the first number swap nodes
			if(highest != current.getData().getHit())
				//swap nodes
				swap(current, element2swap);
			//increment current
			current = current.getNext();
			if(current != null)
				second = current.getNext();
		}
		
		return head.getData().getHit();
			
		case 'w':
			//sort alphabetically
			sortName();
			
			current = head;
			second = current.getNext();
			
			//sort walk
			while(current != null) {
				highest = current.getData().getWalk();
				while(second != null) {
					if(second.getData().getWalk() > highest) {
						//if next > highest set it and continue
						highest = second.getData().getWalk();
						element2swap = second;
					}
					//increment next
					second = second.getNext();
				}
				//if the highest value isn't still equal to the first number swap nodes
				if(highest != current.getData().getWalk())
					//swap nodes
					swap(current, element2swap);
				//increment current
				current = current.getNext();
				if(current != null)
					second = current.getNext();
			}
			
			return head.getData().getWalk();
			
		case 's':
			//sort alphabetically first
			sortName();
			
			current = head;
			second = current.getNext();
			
			//sort by strike
			while(current != null) {
				highest = current.getData().getStrikeout();
				while(second != null) {
					if(second.getData().getStrikeout() < highest) {
						//if next > highest set it and continue
						highest = second.getData().getStrikeout();
						element2swap = second;
					}
					//increment next
					second = second.getNext();
				}
				//if the highest value isn't still equal to the first number swap nodes
				if(!(highest == current.getData().getStrikeout()))
					//swap nodes
					swap(current, element2swap);
				//increment current
				current = current.getNext();
				if(current != null)
					second = current.getNext();
			}
			
			return head.getData().getStrikeout();
			
		default:
			//sort alphabetically first
			sortName();
			
			current = head;
			second = current.getNext();
			
			//sort by hitByPitch
			while(current != null) {
				highest = current.getData().getHitByPitch();
				while(second != null) {
					if(second.getData().getHitByPitch() > highest) {
						//if next > highest set it and continue
						highest = second.getData().getHitByPitch();
						element2swap = second;
					}
					//increment next
					second = second.getNext();
				}
				//if the highest value isn't still equal to the first number swap nodes
				if(!(highest == current.getData().getHitByPitch()))
					//swap nodes
					swap(current, element2swap);
				//increment current
				current = current.getNext();
				if(current != null)
					second = current.getNext();
			}
			
			return head.getData().getHitByPitch();
		}
		
	}
	
	/*
	 * A function that calls the sortLeaders and printLeaders is to be called from main to print out the leaders
	 * 
	 * parameter: PrintWriter p - object to write to a file with
	 * returns: void
	 */
	
	public void findLeaders(PrintWriter p) {
		int highestHit = 0, highestWalk = 0, smallestStrike = 999, highestHBP = 0;
		double highestAvg = 0, highestOBP = 0;

		
		//sort list on batting average
		highestAvg = sortLeaders(highestAvg, 'b');
		
		//print list
		printLeaders("\nBATTING AVERAGE\n", p);
		
		//sort list based on ob%
		highestOBP = sortLeaders(highestOBP, 'o');
		
		//print list
		printLeaders("\nON-BASE PERCENTAGE\n", p);
		
		//sort list based on Hits
		highestHit = (int)sortLeaders(highestHit, 'h');
		
		//print list
		printLeaders("\nHITS\n", p);
		
		//sort list based on walks
		highestWalk = (int)sortLeaders(highestWalk, 'w');
		
		//print list
		printLeaders("\nWALKS\n", p);
		
		//sort list based on strikeouts
		smallestStrike = (int)sortLeaders(smallestStrike, 's');
		
		//print list
		printLeaders("\nSTRIKEOUTS\n", p);
		
		//sort list based on hit by pitch
		highestHBP = (int)sortLeaders(highestHBP, 'p');
		
		//print list 
		printLeaders("\nHIT BY PITCH\n", p);
	}
	
	/*
	 * A function that prints the leaders in alphabetical order
	 * 
	 * parameter: String category - string to know which league to print
	 * 			  PrinteWriter p - object to write to a file with
	 * returns: nothing
	 */

	public void printLeaders(String category, PrintWriter p) {
		int totalCount = 0;
		ArrayList<String> name = new ArrayList<String>(1);
		//print the category
		p.print(category);
		
		//print the list
		
		//set current to head
		current = head;
		second = current.getNext();
		
		switch (category) {
		case "\nBATTING AVERAGE\n":
			double ba = current.getData().battingAverage();
			//print the highest
			p.printf("%.3f\t", ba);
			
			while(current != null) {
				if(ba == current.getData().battingAverage()) {
					name.add(current.getData().getName());
					totalCount++;
					current = current.getNext();
				}
				
				else if(totalCount < 3) {
					//sort arraylist
					Collections.sort(name);
					
					//print arraylist
					if(name.size() > 1) {
						p.print(name.get(0));
						for(int i = 1; i < name.size(); i++)
							p.print(", " + name.get(i));
					}
					else {
						p.print(name.get(name.size()-1));
					}
					
					//clear list
					name.clear();
					
					ba = current.getData().battingAverage();
					p.printf("\n%.3f\t", ba);
				}
				else {
					current = null;
				}
				
			}
			
			//sort arraylist
			Collections.sort(name);
			
			//print arraylist
			if(name.size() > 1) {
				p.print(name.get(0));
				for(int i = 1; i < name.size(); i++)
					p.print(", " + name.get(i));
			}
			else {
				p.print(name.get(name.size()-1));
			}
			
			//clear list
			name.clear();
			p.println();
			break;
			
		case "\nON-BASE PERCENTAGE\n":
			double obp = current.getData().onBasePercentage();
			//print the highest
			p.printf("%.3f\t", obp);
			
			while(current != null) {
				if(obp == current.getData().onBasePercentage()) {
					name.add(current.getData().getName());
					totalCount++;
					current = current.getNext();
				}
				
				else if(totalCount < 3) {
					//sort arraylist
					Collections.sort(name);
					
					//print arraylist
					if(name.size() > 1) {
						p.print(name.get(0));
						for(int i = 1; i < name.size(); i++)
							p.print(", " + name.get(i));
					}
					else {
						p.print(name.get(name.size()-1));
					}
					
					//clear list
					name.clear();
					
					obp = current.getData().onBasePercentage();
					p.printf("\n%.3f\t", obp);
				}
				else {
					current = null;
				}
				
			}
			
			//sort arraylist
			Collections.sort(name);
			
			//print arraylist
			if(name.size() > 1) {
				p.print(name.get(0));
				for(int i = 1; i < name.size(); i++)
					p.print(", " + name.get(i));
			}
			else {
				p.print(name.get(name.size()-1));
			}
			
			//clear list
			name.clear();
			p.println();
			break;
		
		case "\nHITS\n":
			int hit = current.getData().getHit();
			//print the highest
			p.print(hit + "\t");
			
			while(current != null) {
				if(hit == current.getData().getHit()) {
					name.add(current.getData().getName());
					totalCount++;
					current = current.getNext();
				}
				
				else if(totalCount < 3) {
					//sort arraylist
					Collections.sort(name);
					
					//print arraylist
					if(name.size() > 1) {
						p.print(name.get(0));
						for(int i = 1; i < name.size(); i++)
							p.print(", " + name.get(i));
					}
					else {
						p.print(name.get(name.size()-1));
					}
					
					//clear list
					name.clear();
					
					hit = current.getData().getHit();
					p.print("\n" + hit + "\t");
				}
				else {
					current = null;
				}
				
			}
			
			//sort arraylist
			Collections.sort(name);
			
			//print arraylist
			if(name.size() > 1) {
				p.print(name.get(0));
				for(int i = 1; i < name.size(); i++)
					p.print(", " + name.get(i));
			}
			else {
				p.print(name.get(name.size()-1));
			}
			
			//clear list
			name.clear();
			p.println();
			break;
			
		case "\nWALKS\n":
			int walk = current.getData().getWalk();
			//print the highest
			p.print(walk + "\t");
			
			while(current != null) {
				if(walk == current.getData().getWalk()) {
					name.add(current.getData().getName());
					totalCount++;
					current = current.getNext();
				}
				
				else if(totalCount < 3) {
					//sort arraylist
					Collections.sort(name);
					
					//print arraylist
					if(name.size() > 1) {
						p.print(name.get(0));
						for(int i = 1; i < name.size(); i++)
							p.print(", " + name.get(i));
					}
					else {
						p.print(name.get(name.size()-1));
					}
					
					//clear list
					name.clear();
					
					walk = current.getData().getWalk();
					p.print("\n" + walk + "\t");
				}
				else {
					current = null;
				}
				
			}
			
			//sort arraylist
			Collections.sort(name);
			
			//print arraylist
			if(name.size() > 1) {
				p.print(name.get(0));
				for(int i = 1; i < name.size(); i++)
					p.print(", " + name.get(i));
			}
			else {
				p.print(name.get(name.size()-1));
			}
			
			//clear list
			name.clear();
			p.println();
			break;
			
		case "\nSTRIKEOUTS\n":
			int strikeout = current.getData().getStrikeout();
			p.print(strikeout + "\t");
			
			while(current != null) {
				if(strikeout == current.getData().getStrikeout()) {
					name.add(current.getData().getName());
					totalCount++;
					current = current.getNext();
				}
				
				else if(totalCount < 3) {
					//sort arraylist
					Collections.sort(name);
					
					//print arraylist
					if(name.size() > 1) {
						p.print(name.get(0));
						for(int i = 1; i < name.size(); i++)
							p.print(", " + name.get(i));
					}
					else {
						p.print(name.get(name.size()-1));
					}
					
					//clear list
					name.clear();
					
					strikeout = current.getData().getStrikeout();
					p.print("\n" + strikeout + "\t");
				}
				else {
					current = null;
				}
				
			}
			
			//sort arraylist
			Collections.sort(name);
			
			//print arraylist
			if(name.size() > 1) {
				p.print(name.get(0));
				for(int i = 1; i < name.size(); i++)
					p.print(", " + name.get(i));
			}
			else {
				p.print(name.get(name.size()-1));
			}
			
			//clear list
			name.clear();
			p.println();
			break;
			
		case "\nHIT BY PITCH\n":
			//print the highest
			int hbp = current.getData().getHitByPitch();
			p.print(hbp + "\t");
			
			while(current != null) {
				if(hbp == current.getData().getHitByPitch()) {
					name.add(current.getData().getName());
					totalCount++;
					current = current.getNext();
				}
				
				else if(totalCount < 3) {
					//sort arraylist
					Collections.sort(name);
					
					//print arraylist
					if(name.size() > 1) {
						p.print(name.get(0));
						for(int i = 1; i < name.size(); i++)
							p.print(", " + name.get(i));
					}
					else {
						p.print(name.get(name.size()-1));
					}
					
					//clear list
					name.clear();
					
					hbp = current.getData().getHitByPitch();
					p.print("\n" + hbp + "\t");
				}
				else {
					current = null;
				}
				
			}
			
			//sort arraylist
			Collections.sort(name);
			
			//print arraylist
			if(name.size() > 1) {
				p.print(name.get(0));
				for(int i = 1; i < name.size(); i++)
					p.print(", " + name.get(i));
			}
			else {
				p.print(name.get(name.size()-1));
			}
			
			//clear list
			name.clear();
			
			p.println();
			p.println();
			break;

		default:
			break;
		}
	}
	
}