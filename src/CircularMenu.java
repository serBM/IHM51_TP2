import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircularMenu extends JPanel {
	JButton[] buttons;
	int width, height, radius, size, size2;
	
	public CircularMenu(JButton[] buttons, int width, int height, int radius, int size, int size2) {
		this.width = width;
		this.height = height;
		setSize(width, height);
		
		this.radius = radius;
		this.size = size;
		this.size2 = size2;
		this.buttons = new JButton[buttons.length];
		this.buttons = buttons;
		
		this.setOpaque(false);
		build();
	}
	
	private void build() {
		if (buttons != null && buttons.length > 0) {
			setLayout(null);
			int xCentre = width / 2;
			int yCentre = height / 2;
			if (buttons.length < 9) {
				for (int i = 0; i < buttons.length ; i++) {
					buttons[i].setBounds((int) (xCentre + size2/2 + radius * Math.cos( i * 2 * Math.PI / buttons.length)),
							(int) (yCentre - size/2 + radius * Math.sin(i * 2 * Math.PI / buttons.length)),
							size,
							size2);
					add(buttons[i]);
				}
			} else {
				for (int i = 0; i < 8 ; i++) {
					buttons[i].setBounds((int) (xCentre + size2/2 + radius * Math.cos( i * 2 * Math.PI / 8)),
							(int) (yCentre - size/2 + radius * Math.sin(i * 2 * Math.PI / 8)),
							size,
							size2);
					add(buttons[i]);
				}
				int shift = 1;
				for (int i = 8; i < buttons.length; i++,shift+=1) {
					buttons[i].setBounds((int) (xCentre),
							(int) (yCentre - size/2 + radius * Math.sin( Math.PI / 2) + size*shift/1.5 + size/2) ,
							size*2,
							(int)(size2/1.5));
					add(buttons[i]);
				}
				setSize(width, height+shift*size);
			}
		}
	}

}
