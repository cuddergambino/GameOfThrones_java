import java.awt.Image;

enum SideOfBoard {Top, Right, Bottom, Left}

public abstract class Space {
	
	int x,y;
	int width, height;
	SideOfBoard sideOfBoard;
	String name;
	
	Configuration config;
	
	ImagePanel spacePanel;
	
	public Space(Configuration configuration, String spaceName, String side, int xCoord, int yCoord, int width, int height){
		config = configuration;
		name = spaceName;
		x = xCoord;
		y = yCoord;
		this.width = width;
		this.height = height;
		spacePanel = new ImagePanel();
	}
	
	public void setDimensions(String houseName, int houseNumber, int housePrice, int x, int y) throws IndexOutOfBoundsException{
		int shortSide = Configuration.shortSide;
		int longSide = Configuration.longSide;
		int width, height;
		SpaceHouse h = null;
		for(int i = 0; i < numberOfHouses; i++){
			if(houses[i] == null){
				switch(side){
				case Bottom:
					width = shortSide;
					height = longSide;
					h = new SpaceHouse(config, houseName, houseNumber, housePrice, x, y, width, height);
					h.barx = h.x;
					h.bary = h.y;
					h.barWidth = h.width;
					h.barHeight = h.height - h.width;
					h.backgroundx = h.x;
					h.backgroundy = h.y + h.barHeight;
					break;
				case Top:
					width = shortSide;
					height = longSide;
					h = new SpaceHouse(config, houseName, houseNumber, housePrice, x, y, width, height);
					h.barx = h.x;
					h.bary = h.y + h.width;
					h.barWidth = h.width;
					h.barHeight = h.height - h.width;
					h.backgroundx = h.x;
					h.backgroundy = h.y;
					break;
				case Left:
					width = longSide;
					height = shortSide;
					h = new SpaceHouse(config, houseName, houseNumber, housePrice, x, y, width, height);
					h.barx = h.x + h.height;
					h.bary = h.y;
					h.barWidth = h.width - h.height;
					h.barHeight = h.height;
					h.backgroundx = h.x;
					h.backgroundy = h.y;
					break;
				case Right:
					width = longSide;
					height = shortSide;
					h = new SpaceHouse(config, houseName, houseNumber, housePrice, x, y, width, height);
					h.barx = h.x;
					h.bary = h.y;
					h.barWidth = h.width - h.height;
					h.barHeight = h.height;
					h.backgroundx = h.x + h.barWidth;
					h.backgroundy = h.y;
					break;
				}
				h.barCenterx = h.barx + h.barWidth/2;
				h.barCentery = h.bary + h.barHeight/2;
				h.sigilx = h.barx;
				h.sigily = h.bary;
				houses[i] = h;
				return;
			}
		}
	
	public ImagePanel setBackgroundImage(Image img){
		img = config.getScaledImage(img, Configuration.shortSide, Configuration.shortSide);
		spacePanel = new ImagePanel(img);
		setBounds();
		return spacePanel;
	}
	
	public void setBounds(){
		spacePanel.setBounds(x+1, y+1, width-1, height-1);
	}

}
