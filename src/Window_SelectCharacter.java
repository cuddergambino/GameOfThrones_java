import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window_SelectCharacter extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int currentPlayerNumber = 1;
	Character[] characters;
	Configuration config;
	
	public Window_SelectCharacter(Configuration configuration){
		super("Select Your Player");
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
		
		setSize(1200,700);
		setLocation(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		config = configuration;
		
		setLayout(new FlowLayout());
		characters = config.characters;
		for(int i = 0; i < characters.length; i++){
			class SelectCharacterActionListener implements ActionListener{
				Character c;
				@Override
				public void actionPerformed(ActionEvent e) {
					int selection = JOptionPane.showConfirmDialog(Window_SelectCharacter.this,
							"Join the "+c.myHouse+" House?",
							"Player "+currentPlayerNumber,
							JOptionPane.YES_NO_OPTION);
					if(selection == JOptionPane.YES_OPTION){
						c.setEnabled(false);
						for(int i = 0; i <characters.length; i++){
							if(characters[i].myHouse == c.myHouse)
								characters[i].setEnabled(false);
						}
						config.players[currentPlayerNumber-1] = new Player(c);
						if(config.numPlayers == currentPlayerNumber++){
							Window_SelectCharacter.this.setVisible(false);
							Window_SelectCharacter.this.dispose();
							moveOn();
						}
						
					}
					
				}
				public SelectCharacterActionListener(Character c){
					this.c = c;
				}
				
			}
			
			characters[i].addActionListener(new SelectCharacterActionListener(characters[i]));
			add(characters[i]);
		}
		
		
		setVisible(true);
		
		
		
	}

	public void moveOn(){
		GameOfThrones.runGame();
	}

}
