
public class SpaceCorner extends Space {


	String name;
	
	public SpaceCorner(Configuration configuration, String name, int xCoord, int yCoord) {
		
		super(configuration, name, xCoord, yCoord, Configuration.longSide, Configuration.longSide);
		
	}

}
