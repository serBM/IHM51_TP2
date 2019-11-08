import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CircularMenu extends JPanel {
	HashMap<Integer, JButton> listButton;
	Paint.ColorButton[] colors;
	int width, height, radius, size;
	
	public CircularMenu(Paint.ColorButton[] colors, int width, int height, int radius, int size) {
		this.width = width;
		this.height = height;
		setSize(width, height);
		
		this.radius = radius;
		this.size = size;
		this.colors = new Paint.ColorButton[colors.length];
		this.colors = colors;
		
		this.setOpaque(false);
		build();
	}
	
	private void build() {
		if (colors != null && colors.length > 0) {
			setLayout(null);
			int xCentre = width / 2;
			int yCentre = height / 2;
			if (colors.length < 9) {
				for (int i = 0; i < colors.length ; i++) {
					colors[i].setBounds((int) (xCentre + size/2 + radius * Math.cos( i * 2 * Math.PI / colors.length)),
							(int) (yCentre - size/2 + radius * Math.sin(i * 2 * Math.PI / colors.length)),
							size,
							size);
					add(colors[i]);
				}
			} else {
				for (int i = 0; i < 8 ; i++) {
					colors[i].setBounds((int) (xCentre + size/2 + radius * Math.cos( i * 2 * Math.PI / 8)),
							(int) (yCentre - size/2 + radius * Math.sin(i * 2 * Math.PI / 8)),
							size,
							size);
					add(colors[i]);
				}
				int shift = 1;
				for (int i = 8; i < colors.length; i++,shift+=1) {
					colors[i].setBounds((int) (xCentre),
							(int) (yCentre - size/2 + radius * Math.sin( Math.PI / 2) + size*shift/1.5 + size/2) ,
							size*2,
							(int)(size/1.5));
					add(colors[i]);
				}
				setSize(width, height+shift*size);
			}
		}
	}

}
