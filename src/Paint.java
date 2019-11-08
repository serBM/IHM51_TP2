//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import static java.lang.Math.*;

import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import java.awt.event.*;
import javax.swing.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/* paint *******************************************************************/

class Paint extends JFrame {
	Vector<Shape> shapes = new Vector<Shape>();
	Vector<Color> colors = new Vector<Color>();
	Color couleur = Color.BLACK;
	
	class ColorButton extends JButton implements MouseListener {
        Color  buttonColor;

        public ColorButton(int red, int green, int blue) {
            buttonColor = new Color(red, green, blue);
            setBackground(buttonColor);
            setText(" ");
            addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            couleur = buttonColor;
            //Forwards the click to the Tool click event
            tool.mouseClicked(e);
        }

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
	
    ColorButton[] colorButtons = { new ColorButton(0, 0, 0), new ColorButton(255, 0, 0),
            new ColorButton(0, 255, 0), new ColorButton(0, 0, 255) };
    ColorButton[] colorButtonsCircular = { new ColorButton(0, 0, 0), new ColorButton(255, 0, 0),
            new ColorButton(108, 56, 187), new ColorButton(0, 0, 255), new ColorButton(0, 255, 255), new ColorButton(255, 0, 255),
            new ColorButton(255, 255, 0), new ColorButton(128, 128, 128), new ColorButton(45, 243, 12), new ColorButton(123, 0, 23),
            new ColorButton(13, 25, 87), new ColorButton(45, 145, 45) };
	
	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;
		CircularMenu menu;
		int menuWidth = 300, menuHeight = 235, buttonSize=40;
		
		public Tool(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
			if (menu == null) {
				menu = new CircularMenu(colorButtonsCircular,menuWidth, menuHeight, 65, buttonSize);
				panel.add(menu);
				menu.setLocation(getMousePosition().x-menuWidth/2-buttonSize, getMousePosition().y-menuHeight/2-buttonSize);
				//menu.setBounds(getMousePosition().x-menuWidth/2, getMousePosition().y-menuHeight/2-40, menuWidth, menuHeight);
				menu.setVisible(true);
			} else { 
				menu.setVisible(false);
				menu = null; 
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			Path2D.Double path = (Path2D.Double) shape;
			if (path == null) {
				path = new Path2D.Double();
				path.moveTo(o.getX(), o.getY());
				shapes.add(shape = path);
				colors.add(couleur);
			}
			path.lineTo(e.getX(), e.getY());
			panel.repaint();
		}
	}, new Tool("rect") {
		public void mouseDragged(MouseEvent e) {
			Rectangle2D.Double rect = (Rectangle2D.Double) shape;
			if (rect == null) {
				rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
				shapes.add(shape = rect);
				colors.add(couleur);
			}
			rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
			if (ellipse == null) {
				ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
				shapes.add(shape = ellipse);
				colors.add(couleur);
			}
			ellipse.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	} };
	Tool tool;

	JPanel panel;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
	        	for (JButton button : colorButtons) {
	                add(button);
	        	}
			}
		}, BorderLayout.NORTH);

		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				g2.setColor(couleur);
				for (int i=0; i < shapes.size() ;i++) {
					g2.setColor(colors.get(i));
					g2.draw(shapes.get(i));
				}
			}
		});

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint("paint");
			}
		});
	}
}
