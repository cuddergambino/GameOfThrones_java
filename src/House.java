import java.awt.Image;



public class House extends Space{
	enum State{Independent, PaidOff, Sworn, United}
	
	
	String name;
	String imageName;
	int number;
	int price;
	State state;
	Player ally;
	
	int barx, bary, barHeight, barWidth, barCenterx, barCentery;
	int backgroundx, backgroundy, backgroundCenter;
	int sigilx, sigily;
	
	public House(Configuration configuration, String houseName, int houseNumber, int housePrice, int x, int y, int width, int height){
		super(configuration, x, y, width, height);
		name = houseName;
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
	
	
}
