/* Sanika Buche ssb170002
 * 
 * This class holds all the data that each player needs
 * - calculates batting average, at bats, and on-base percentages
 */
public class Player{
	private String name;
	private int hit;
	private int out;
	private int strikeout;
	private int walk;
	private int hitByPitch;
	private int sacrifice;
	private boolean multiple;
	
	//constructors
	public Player() {
		name = null;
		hit = 0;
		out = 0;
		strikeout = 0;
		walk = 0;
		hitByPitch = 0;
		sacrifice = 0;
		multiple = false;
	}
	
	//overloaded constructor
	public Player(String n, int h, int o, int s, int w, int hbp, int sac) {
		this.name = n;
		this.hit = h;
		this.out = o;
		this.strikeout = s;
		this.walk = w;
		this.hitByPitch = hbp;
		this.sacrifice = sac;
		
	}
	
	//accessors
	public String getName() {return name;}
	public int getHit() {return hit;}
	public int getOut() {return out;}
	public int getStrikeout() {return strikeout;}
	public int getWalk() {return walk;}
	public int getHitByPitch() {return hitByPitch;}
	public int getSacrifice() {return sacrifice;}
	public boolean getMultiple() {return multiple;}
	
	//mutators
	public void setName(String n) {this.name = n;}
	public void setHit(int h) {this.hit = h;}
	public void setOut(int o) {this.out = o;}
	public void setStrikeout(int s) {this.strikeout = s;}
	public void setWalk(int w) {this.walk = w;}
	public void setHitByPitch(int h) {this.hitByPitch = h;}
	public void setSacrifice(int s) {this.sacrifice = s;}
	public void setMultiple() {this.multiple = true;}
	
	//functions
	
	/*
	 * A function that calculates the at bats of the player object
	 * 
	 * parameters: none
	 * return: double - the at bats
	 */
	public double atBats() {
		return (this.hit + this.out + this.strikeout);
	}
	
	/*
	 * A function that calculates the batting average of the player object
	 * 
	 * parameters: none
	 * return: double - 0 if function is dividing by 0
	 * 				  - the batting average if the function is not dividing by 0
	 */
	public double battingAverage() {
		double atBats = atBats();
		
		if (atBats == 0)
			return 0;
		else {
			return (this.hit / atBats);
		}
	}
	
	/*
	 * A function that calculates the on-base percentage of the player object
	 * 
	 * parameters: none
	 * return: double - 0 if function is dividing by 0
	 * 				  - the on-base percentage if the function is not dividing by 0
	 */
	public double onBasePercentage() {
		double plateApperances = atBats() + this.walk + this.hitByPitch + this.sacrifice;
		
		if(plateApperances == 0)
			return 0;
		else {
			return ((this.hit + this.walk + this.hitByPitch) / plateApperances);
		}
	}

	/*
	 * A function that compares every value in the player object with this
	 * if every value in this is the same as the passed in player object, we return 1, otherwise
	 * 0 is returned
	 * 
	 * parameters: Player obj - object to compare against
	 * return: int 1 or 0 stating whether this == player object
	 */
	public int compareTo(Player obj) {
		if(this.name.compareToIgnoreCase(obj.name) == 0) {
			if(this.sacrifice == obj.sacrifice) {
				if(this.hitByPitch == obj.hitByPitch) {
					if(this.walk == obj.walk) {
						if(this.strikeout == obj.strikeout) {
							if(this.out == obj.out) {
								if(this.hit == obj.hit) {
									if(this.battingAverage() == obj.battingAverage()) {
										if(this.onBasePercentage() == obj.onBasePercentage())
											return 1;
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
}
