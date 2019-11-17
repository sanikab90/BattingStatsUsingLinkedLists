//sanika buche ssb170002
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception{
		String fileName;
		//input the file
		Scanner read = new Scanner(System.in); //create Scanner obj
		fileName = read.nextLine(); //read user input
		
		//user file
		File inFile = new File(fileName);
		
		//scanner object to read inFile
		Scanner in = new Scanner(inFile);
		
		//linked list object
		LinkList list = new LinkList();
		
		if(inFile.canRead()) {
			while(in.hasNext()) {
				//get line
				String line = in.nextLine();
			
				//call parse function
				Player obj = parse(line, list);
				
				//add node to list
				if(!(obj.getMultiple()))
					list.add(obj);
			}
			
			//sort the list
			list.sortName();
			
			//print the list
			PrintWriter p = new PrintWriter("leaders.txt");
			list.printList(list.getHead(), p);
			
			p.print("\nLEAGUE LEADERS");
			
			//print the leaders
			list.findLeaders(p);
			p.close();
		}
		
		//close scanners
		read.close();
		in.close();
	}
	
	/*
	 * A function that parses one line from the file and then returns it to a player object
	 * Also checks if a player object with the same name exists and if the object exits it adds
	 * the counts to the already existing object
	 * 
	 * parameters: String line - one line from the file
	 * 			   LinkList list - list to insert nodes in
	 * 
	 * return: Player object to insert in a linked list
	 */
	public static Player parse(String line, LinkList list) {
		//counter variables
		int hCount = 0, oCount = 0, kCount = 0, wCount = 0, pCount = 0, sCount = 0;
		
		//initialize player object
		Player obj = new Player();
		
		//get name
		String name = line.substring(0, line.indexOf(' '));
		line = line.substring(line.indexOf(' ') + 1);
		
		//get name of character
		obj.setName(name);
		
		//loop trough the line and parse the variables in each int variable		
		for(int i = 0; i < line.length(); i++) {
			switch (line.charAt(i)) {
			
			case 'H':
				hCount++;
				break;
				
			case 'O':
				oCount++;
				break;
				
			case 'K':
				kCount++;
				break;
				
			case 'W':
				wCount++;
				break;
				
			case 'P':
				pCount++;
				break;
				
			case 'S':
				sCount++;
				break;
				
			default:
				break;
			}
		}
		
		//check if theres more than 1 node in the list then loop through it to find name
		if(Node.getNodeCount() > 0) {
			
			//loop through list
			list.setCurrent(list.getHead());
			
			//while name in list is NOT the same as name in object and not at end of list
			while(!(list.getCurrent().getData().getName().equalsIgnoreCase(obj.getName())) && list.getCurrent().getNext() != null) {
				list.setCurrent(list.getCurrent().getNext());
			}
			
			//if name in list is the same as name in object put all data in that object
			if(list.getCurrent().getData().getName().equalsIgnoreCase(obj.getName())) {
				Player temp = list.getCurrent().getData();
				
				//put counts into already filled object
				temp.setMultiple();
				temp.setHit(temp.getHit() + hCount);
				temp.setOut(temp.getOut() + oCount);
				temp.setStrikeout(temp.getStrikeout() + kCount);
				temp.setWalk(temp.getWalk() + wCount);
				temp.setHitByPitch(temp.getHitByPitch() + pCount);
				temp.setSacrifice(temp.getSacrifice() + sCount);
				
				return list.getCurrent().getData();
			}
		}
		
		//put counts into player object
		obj.setHit(hCount);
		obj.setOut(oCount);
		obj.setStrikeout(kCount);
		obj.setWalk(wCount);
		obj.setHitByPitch(pCount);
		obj.setSacrifice(sCount);
		
		//return object
		return obj;
	}
}