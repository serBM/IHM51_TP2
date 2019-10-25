import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuCirculaire extends JPanel {
	HashMap<Integer, JButton> listButton;
	
	public MenuCirculaire() {
		listButton = new HashMap<>();

		for (int i = 1; i <= 6; i++) {
			listButton.put(i, new JButton("Bouton " + i));
			add(listButton.get(i));
			listButton.get(i).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		
		listButton.get(1).setBounds(100, 0, 90, 20);
		listButton.get(2).setBounds(200, 60, 90, 20);
		listButton.get(3).setBounds(200, 120, 90, 20);
		listButton.get(4).setBounds(100, 180, 90, 20);
		listButton.get(5).setBounds(0, 120, 90, 20);
		listButton.get(6).setBounds(0, 60, 90, 20);
	}

}
