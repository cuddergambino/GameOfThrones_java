import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Configuration config;
	int preferredWidth = 700;
	int preferredHeight = 700;
	
	public GameBoard(Configuration configuration){
		config = configuration;
		config.board = this;
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, preferredWidth, preferredHeight);
		g.drawRect(Configuration.longSide, Configuration.longSide, preferredWidth-2*Configuration.longSide, preferredHeight-2*Configuration.longSide);
		
		for(int i = 0; i < config.regions.length; i++){
			Region region = config.regions[i];
			for(int j = 0; j < region.numberOfHouses; j++){
				House house = region.houses[j];
				
				// Bar
				g.setColor(region.color);
				g.fillRect(house.barx, house.bary, house.barWidth, house.barHeight);

				// Sigil on Bar if owned
				if(house.ally!=null){
					g.drawImage(house.getSigil(), house.sigilx, house.sigily, 20,20,this);
				}
				
				// Background Image
				if(house.name.equalsIgnoreCase("Craster's Keep")){
					BufferedImage image = null;
					try {
						image = ImageIO.read(new File("src/images/houses/purple1.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(image, house.backgroundx, house.backgroundy, 60,60, this);
					
				}
				
				// Outline
				g.setColor(Color.black);
				g.drawRect(house.x, house.y, house.width, house.height);
				g.drawRect(house.barx, house.bary, house.barWidth, house.barHeight);
			}
		}
		
		
		
		
	}

}
