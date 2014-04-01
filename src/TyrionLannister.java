
public class TyrionLannister extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TyrionLannister(Configuration config){
		super(config, "Tyrion Lannister");
		fullImageFile = "src/images/characters/TyrionLannister.png";
		faceImageFile = "src/images/characters/TyrionLannister_icon.png";
		setPlayerHouse(PlayerHouse.Lannister);
	}

}