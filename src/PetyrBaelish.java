
public class PetyrBaelish extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PetyrBaelish(Configuration config){
		super(config, "Petyr Baelish");
		fullImageFile = "src/images/characters/PetyrBaelish.png";
		faceImageFile = "src/images/characters/PetyrBaelish_icon.png";
		setPlayerHouse(PlayerHouse.Baratheon);
	}

}
