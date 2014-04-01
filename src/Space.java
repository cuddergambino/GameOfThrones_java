import javax.swing.JButton;
import javax.swing.JPanel;


public class Space {

	int x,y;
	int width, height;		// set by Region
	Configuration config;
	
	JPanel SpacePanel;
	
	public Space(Configuration configuration, int xCoord, int yCoord, int width, int height){
		config = configuration;
		x = xCoord;
		y = yCoord;
		this.width = width;
		this.height = height;

		SpacePanel = new JPanel();
		SpacePanel.setSize(width, height);
		SpacePanel.setLocation(x, y);
//		config.board.add(SpacePanel);
//		JButton jb = new JButton("test");
//		SpacePanel.add(jb);
	}
}
