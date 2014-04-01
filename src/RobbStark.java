
public class RobbStark extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RobbStark(Configuration config){
		super(config, "Robb Stark");
		fullImageFile = "src/images/characters/RobbStark.png";
		faceImageFile = "src/images/characters/RobbStark_icon.png";
		setPlayerHouse(PlayerHouse.Stark);
	}

}