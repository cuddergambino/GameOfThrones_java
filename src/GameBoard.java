import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		this.setLayout(null);
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, preferredWidth, preferredHeight);
		g.drawRect(Configuration.longSide, Configuration.longSide, preferredWidth-2*Configuration.longSide, preferredHeight-2*Configuration.longSide);
		
		
		// Drawing Properties a.k.a. Houses
		//*********************************
		
		for(int i = 0; i < config.regions.length; i++){
			Region region = config.regions[i];
			for(int j = 0; j < region.numberOfHouses; j++){
				SpaceHouse house = region.houses[j];
				
				// Bar
				g.setColor(region.color);
				g.fillRect(house.barx, house.bary, house.barWidth, house.barHeight);

				// Sigil on Bar if owned
				if(house.ally!=null){
					g.drawImage(house.getSigil(), house.sigilx, house.sigily, 20,20,this);
				}
				
				// Background Image
				BufferedImage image = null;
				if(house.name.equalsIgnoreCase("Craster's Keep")){
//					BufferedImage image = null;
					try {
						image = ImageIO.read(new File("src/images/houses/purple1.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
//					g.drawImage(image, house.backgroundx, house.backgroundy, config.spaceBackgroundSideLength,config.spaceBackgroundSideLength, this);
//					
					house.setBackgroundImage(image);
					house.spacePanel.setBounds(house.backgroundx+1, house.backgroundy+1, config.spaceBackgroundSideLength-1, config.spaceBackgroundSideLength-1);
					this.add(house.spacePanel);
				}
				
				
				// Characters on Space
				
//				this.add(house.spacePanel);
//				house.spacePanel.setOpaque(true);
//				house.spacePanel.setBackground(Color.red);

				// Outline
				g.setColor(Color.black);
				g.drawRect(house.x, house.y, house.width, house.height);
				g.drawRect(house.barx, house.bary, house.barWidth, house.barHeight);
			}
		}
		
		// Drawing Corner Spaces
		//*********************************
		for(int i = 0; i < config.cornerSpaces.length; i++){
			SpaceCorner cornerSpace = config.cornerSpaces[i];
			
//			g.fillRect(cornerSpace.x, cornerSpace.y, Configuration.longSide, Configuration.longSide);
			g.drawString(cornerSpace.name, cornerSpace.x, cornerSpace.y+10);
						
			this.add(cornerSpace.spacePanel);
		}
		
//		config.cornerSpaces[1].spacePanel.add(config.players[0].character);	
		
		// Drawing CardDrawingSpaces
				//*********************************
				for(int i = 0; i < config.cardDrawingSpaces.length; i++){
					SpaceCardDrawing cardDrawingSpace = config.cardDrawingSpaces[i];
					
					g.fillRect(cardDrawingSpace.x, cardDrawingSpace.y, Configuration.longSide, Configuration.longSide);			
					this.add(cardDrawingSpace.spacePanel);
				}
		
	}//end paintComponent()

}
