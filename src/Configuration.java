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
	Region[] regions;
	Space[] nonHouseSpaces;
	GameBoard board;
	
	// Data gathered from user input
	
	int numPlayers;
	Player[] players;
	
	public Configuration(String filename) throws ParserConfigurationException, SAXException, IOException{
		loadXML(filename);
		
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
	}
	
	public GameBoard createBoard(){
		board = new GameBoard(this);
		return board;
	}
	
	public ImageIcon getScaledImage(Image img, int newWidth, int newHeight){
		BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, newWidth, newHeight, null);
		return new ImageIcon(bi);
	}
	
	public ImageIcon getScaledImage(ImageIcon srcImg, int newWidth, int newHeight){
		Image img = srcImg.getImage();
		BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, newWidth, newHeight, null);
		return new ImageIcon(bi);
	}
}
