
public class JoffreyLannister extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JoffreyLannister(Configuration config){
		super(config, "Joffrey Lannister");
		fullImageFile = "src/images/characters/JoffreyLannister.png";
		faceImageFile = "src/images/characters/JoffreyLannister_icon.png";
		setPlayerHouse(PlayerHouse.Lannister);
	}

}