/* Sanika Buche ssb170002
 * This class is a generic node class and has a payload called data and a next pointer
 */
public class Node<T>{
	private T data; //payload variable
	private Node<T> next; //next pointer
	static int nodeCount = 0; //get the length of the linked list
	
	//constructor
	Node(){
		this.data = null;
		this.next = null;
		nodeCount++;
	}
	
	//overloaded constructor
	Node(T o){
		this.data = o;
		nodeCount++;
	}
	
	//getters
	public T getData() {return data;}
	public Node<T> getNext() {return next;}
	public static int getNodeCount() {return nodeCount;}
	
	//setters
	public void setData(T temp) {this.data = temp;}
	public void setNext(Node<T> temp) {this.next = temp;}
}




































//nice