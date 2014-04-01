import javax.swing.JFrame;
import javax.swing.JScrollPane;





public final class GameOfThrones{			// final?

	/**
	 * This frame is to be used as the root window. Whenever a new window is to be shown,
	 * set this JFrame = thatJFrame
	 */
	private static final long serialVersionUID = 1L;
	static String configurationFile = "src/board.xml";
	static Configuration config;
	static JFrame mainWindow;
	
	public static void main(String[] args) {

		
		try {
			config = new Configuration(configurationFile);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		
		mainWindow = new Window_SelectNumberOfPlayers(config);
		mainWindow = new Window_SelectCharacter(config);
		
	}
	
	public static void runGame(){
		mainWindow = new JFrame();
		mainWindow.getContentPane().add(new JScrollPane(config.createBoard()));
		mainWindow.setSize(config.board.preferredWidth, config.board.preferredHeight);
		mainWindow.setVisible(true);
	}

}


