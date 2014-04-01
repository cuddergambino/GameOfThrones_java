
public class SansaStark extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SansaStark(Configuration config){
		super(config, "Sansa Stark");
		fullImageFile = "src/images/characters/SansaStark.png";
		faceImageFile = "src/images/characters/SansaStark_icon.png";
		setPlayerHouse(PlayerHouse.Stark);
	}

}