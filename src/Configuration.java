import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


class Configuration {
	
	String imagesDirectory = "src/images/";

	static int longSide = 80;
	static int shortSide = 60;
	int fullCharacterImageWidth = 200;
	int fullCharacterImageHeight = (int) (fullCharacterImageWidth*1.5);
	int characterFaceImageSideLength = 13;
	int spaceBackgroundSideLength = shortSide;
	
	
	// Automatic data set up
	
	Document doc;
	Character[] characters = {
			new DaenerysTargaryen(this),
			new TyrionLannister(this),
			new JoffreyLannister(this),
			new JonSnow(this), 
			new RobbStark(this),
			new SansaStark(this), 
			new PetyrBaelish(this),
			new TheonGreyjoy(this),
			
	};
	Region[] regions;		// contains Houses
	SpaceCorner cornerSpaces[];
	SpaceCardDrawing[] cardDrawingSpaces;
	Space[] spaces;
	GameBoard board;
	
	int numberOfTurns = 0;
	
	// Data gathered from user input
	
	int numPlayers;
	Player[] players;
	
	public Configuration(String filename) throws ParserConfigurationException, SAXException, IOException{
		loadXML(filename);
		orderSpaces();
	}

	void setNumberOfPlayers(int num){
		numPlayers = num;
		players = new Player[num];
	}
	
	void loadXML(String filename) throws ParserConfigurationException, SAXException, IOException{
		File file = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbFactory.newDocumentBuilder();
		doc = db.parse(file);
		doc.getDocumentElement().normalize();
		
		// Region and Houses
		//*********************************
		NodeList regionList = doc.getElementsByTagName("region");
		regions = new Region[regionList.getLength()];
		for(int i = 0; i < regionList.getLength(); i++){
			Node regionNode = regionList.item(i);
			if(regionNode.getNodeType() == Node.ELEMENT_NODE){
				Element regionElement = (Element) regionNode;
				
				Color c = new Color(Integer.parseInt(regionElement.getAttribute("rgb")));
				String sideOfBoard = regionElement.getAttribute("sideOfBoard");
				int total = Integer.valueOf(regionElement.getAttribute("total"));
				
				Region r = new Region(this, c, sideOfBoard, total);
				regions[i] = r;
				
				NodeList houseList = regionElement.getElementsByTagName("house");
				for(int j = 0; j < houseList.getLength(); j++){
					Node houseNode = houseList.item(j);
					if(houseNode.getNodeType() == Node.ELEMENT_NODE){
						Element houseElement = (Element) houseNode;
						
						String name = houseElement.getAttribute("name");
						int number = Integer.valueOf(houseElement.getAttribute("number"));
						int price = Integer.valueOf(houseElement.getAttribute("price"));
						int x = Integer.valueOf(houseElement.getAttribute("x"));
						int y = Integer.valueOf(houseElement.getAttribute("y"));
						
						r.addHouse(name, number, price, x, y);
					}
					
				}
			}
		}
		
		// Corner Spaces
		//*********************************
		NodeList cornerList = doc.getElementsByTagName("corner");
		cornerSpaces = new SpaceCorner[cornerList.getLength()];
		for(int i = 0; i < cornerList.getLength(); i++){
			Node cornerNode = cornerList.item(i);
			if(cornerNode.getNodeType() == Node.ELEMENT_NODE){
				Element cornerElement = (Element) cornerNode;
				
				String name = cornerElement.getAttribute("name");
				int x = Integer.valueOf( cornerElement.getAttribute("x") );
				int y = Integer.valueOf( cornerElement.getAttribute("y") );
				
				cornerSpaces[i] = new SpaceCorner(this, name, x, y);
			}
		}
		
		// Card Drawing Spaces
		//*********************************
		NodeList cardDrawingList = doc.getElementsByTagName("battle");
		cardDrawingSpaces = new SpaceCardDrawing[cardDrawingList.getLength()];
		for(int i = 0; i < cardDrawingList.getLength(); i++){
			Node cardDrawingNode = cardDrawingList.item(i);
			if(cardDrawingNode.getNodeType() == Node.ELEMENT_NODE){
				Element cardDrawingElement = (Element) cardDrawingNode;

				String name = cardDrawingElement.getAttribute("name");
				int x = Integer.valueOf( cardDrawingElement.getAttribute("x") );
				int y = Integer.valueOf( cardDrawingElement.getAttribute("y") );

				cardDrawingSpaces[i] = new SpaceCardDrawing(this, name, x, y);
			}
		}

	}
	
	private void orderSpaces(){
		
	}
	
	public GameBoard createBoard(){
		// Set character images to small icons to display on board
		for(int i = 0; i < players.length; i++){
			Character c = players[i].character;
			c.setText("");
			c.setIcon(new ImageIcon(c.characterFace));
			c.setFocusable(false);
			c.setBorderPainted(false);
			c.setEnabled(true);
		}
		
		board = new GameBoard(this);
		return board;
	}
	
	public Image getScaledImage(Image img, int newWidth, int newHeight){
		BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, newWidth, newHeight, null);
		return bi;
	}
	
	public Image getScaledImage(ImageIcon srcImg, int newWidth, int newHeight){
		Image img = srcImg.getImage();
		BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, newWidth, newHeight, null);
		return bi;
	}
}
