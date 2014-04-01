
public class DaenerysTargaryen extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public DaenerysTargaryen(Configuration config){
		super(config, "Daenerys Targaryen");
		fullImageFile = "src/images/characters/DaenerysTargaryen.png";
		faceImageFile = "src/images/characters/DaenerysTargaryen_icon.png";
		setPlayerHouse(PlayerHouse.Targaryen);
	}

}
