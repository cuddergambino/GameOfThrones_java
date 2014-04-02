import java.awt.Color;


public class Region {
	
	
	Color color;
	SideOfBoard side;
	int numberOfHouses;
	SpaceHouse[] houses;
	Configuration config;
	
	public Region(Configuration configuration, Color c, String sideOfBoard, int numHouses){
		config = configuration;
		color = c;
		numberOfHouses = numHouses;
		houses = new SpaceHouse[numHouses];
		
		if(sideOfBoard.equalsIgnoreCase("top"))
				side = SideOfBoard.Top;
		else if(sideOfBoard.equalsIgnoreCase("right"))
			side = SideOfBoard.Right;
		else if(sideOfBoard.equalsIgnoreCase("bottom"))
			side = SideOfBoard.Bottom;
		else
			side = SideOfBoard.Left;
	}
	
	public void addHouse(String houseName, int houseNumber, int housePrice, int x, int y) throws IndexOutOfBoundsException{
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
		
		throw new IndexOutOfBoundsException("Adding too many Houses to Region " + color);
	}
}
