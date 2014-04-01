import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;



public class Window_SelectNumberOfPlayers extends JFrame{

	private static final long serialVersionUID = 1L;
	
	final int MAX_NUM_PLAYERS = 4;
	
	public Window_SelectNumberOfPlayers(Configuration config){
		super("Game of Thrones - Number of Players");
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
		setVisible(false);
		
		
		Object values[] = new Integer[MAX_NUM_PLAYERS - 1];
		for(int i = 2; i<=MAX_NUM_PLAYERS; i++){
			values[i-2] = new Integer(i);
		}
		
		Object defaultValue = 2;
		Object value = JOptionPane.showInputDialog(this,
				"Please select the number of players",
				"Number of Players", JOptionPane.QUESTION_MESSAGE, null, // icon
				values,
				defaultValue);
		config.setNumberOfPlayers( (int)(Integer)value );
		
		this.dispose();
	}
	
}
