
public class JonSnow extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JonSnow(Configuration config){
		super(config, "Jon Snow");
		fullImageFile = "src/images/characters/JonSnow.png";
		faceImageFile = "src/images/characters/JonSnow_icon.png";
		setPlayerHouse(PlayerHouse.Stark);
	}

}