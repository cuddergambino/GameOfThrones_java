
public class Player {

	Configuration config;
	
	Character character;
	int spaceNumber;
	public Player(Configuration configuration, Character c){
		config = configuration;
		character = c;
		spaceNumber = 0;
	}
	
	public void moveForward(int numberOfSpaces){
		spaceNumber = (spaceNumber+numberOfSpaces) % config.spaces.length;
	}
	
}
