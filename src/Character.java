import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


abstract public class Character extends JButton{

	/**
	 * - Character image must have ratio 3:2 height to width
	 * Add actionListener so when player clicked their Info pops up (money, property, house, etc.)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum PlayerHouse{ Baratheon, Greyjoy, Stark, Lannister, Tully, Targaryen }
	
	Configuration config;
	
	PlayerHouse myHouse;
	Image sigils[] = new Image[3];
	Image characterImage, characterFace;
	String fullImageFile, faceImageFile;
	
	public Character(Configuration configuration, String name){
		super(name);
		config = configuration;
		
	}
	
	
	
	
	
		// Member functions
	
	public void setPlayerHouse(PlayerHouse ph){
		BufferedImage cImage = null, cFace = null;
		try {
			cImage = ImageIO.read(new File(fullImageFile));
			cFace = ImageIO.read(new File(faceImageFile));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		characterImage = config.getScaledImage(cImage, config.fullCharacterImageWidth, config.fullCharacterImageHeight);
		characterFace = config.getScaledImage(cFace, config.characterFaceImageSideLength, config.characterFaceImageSideLength);
		
		this.setIcon(new ImageIcon(characterImage));
		this.setVerticalTextPosition(BOTTOM);
		this.setHorizontalTextPosition(CENTER);
		
		myHouse = ph;
		String rootFileName = null;
		switch(ph){
		case Baratheon:
			rootFileName = "sigils/baratheon_";
			break;
		case Greyjoy:
			rootFileName = "sigils/greyjoy_";
			break;
		case Stark:
			rootFileName = "sigils/stark_";
			break;
		case Lannister:
			rootFileName = "sigils/lannister_";
			break;
		case Tully:
			rootFileName = "sigils/tully_";
			break;
		case Targaryen:
			rootFileName = "sigils/targaryen_";
			break;
		}
		for(int i = 1; i <= sigils.length; i++){
			try {
				String fileLocation = config.imagesDirectory + rootFileName + i + ".png";
				sigils[i-1] = ImageIO.read(new File(fileLocation));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
