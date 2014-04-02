import java.awt.Image;



public class SpaceHouse extends Space{
	enum State{Independent, PaidOff, Sworn, United}
	
	String imageName;
	int number;
	int price;
	State state;
	Player ally;
	
	int barx, bary, barHeight, barWidth, barCenterx, barCentery;
	int backgroundx, backgroundy, backgroundCenter;
	int sigilx, sigily;
	
	public SpaceHouse(Configuration configuration, String houseName, int houseNumber, int housePrice, int x, int y, int width, int height){
		super(configuration, houseName, x, y, width, height);
		number = houseNumber;
		price = housePrice;
		state = State.Independent;
	}
	
	public Image getSigil(){
		switch(state){
		case Independent:
			return null;
		case PaidOff:
			return ally.character.sigils[0];
		case Sworn:
			return ally.character.sigils[1];
		case United:
			return ally.character.sigils[2];
		}
		return null;
	}
	
	public void setBounds(){
		spacePanel.setBounds(backgroundx+1, backgroundy+1, config.spaceBackgroundSideLength-1, config.spaceBackgroundSideLength-1);
	}
	
	
}
