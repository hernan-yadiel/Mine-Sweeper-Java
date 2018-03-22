import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	
	
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		switch (e.getButton()) {
			case 1:		//Left mouse button
//				Component c = e.getComponent();
//				while (!(c instanceof JFrame)) {
//					c = c.getParent();
//					if (c == null) {
//						return;
//					}
//				}
//				
//				JFrame myFrame = (JFrame) c;
//				MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
//				Insets myInsets = myFrame.getInsets();
//				int x1 = myInsets.left;
//				int y1 = myInsets.top;
				e.translatePoint(-x1, -y1);
				int x = e.getX();
				int y = e.getY();
				myPanel.x = x;
				myPanel.y = y;
				myPanel.mouseDownGridX = myPanel.getGridX(x, y);
				myPanel.mouseDownGridY = myPanel.getGridY(x, y);
				myPanel.repaint();
				break;
			case 3:		//Right mouse button
				e.translatePoint(-x1, -y1);
				x = e.getX();
				y = e.getY();
				myPanel.x = x;
				myPanel.y = y;
				myPanel.mouseDownGridX = myPanel.getGridX(x, y);
				myPanel.mouseDownGridY = myPanel.getGridY(x, y);
				myPanel.repaint();
				break;
			default:    //Some other button (2 = Middle mouse button, etc.)
				//Do nothing
				break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
		
		switch (e.getButton()) {
			case 1:		//Left mouse button
//				Component c = e.getComponent();
//				while (!(c instanceof JFrame)) {
//					c = c.getParent();
//					if (c == null) {
//						return;
//					}
//				}
//				JFrame myFrame = (JFrame)c;
//				MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
//				Insets myInsets = myFrame.getInsets();
//				int x1 = myInsets.left;
//				int y1 = myInsets.top;
//				e.translatePoint(-x1, -y1);
//				int x = e.getX();
//				int y = e.getY();
//				myPanel.x = x;
//				myPanel.y = y;
//				int gridX = myPanel.getGridX(x, y);
//				int gridY = myPanel.getGridY(x, y);

				if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
					//Had pressed outside
					//Do nothing
				} else {
					if ((gridX == -1) || (gridY == -1)) {
						//Is releasing outside
						//Do nothing
					} else {
						if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
							//Released the mouse button on a different cell where it was pressed
							//Do nothing
						} else {
							//Released the mouse on a mine
							if (myPanel.verifyCoordinates(myPanel.minesArray, myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK; 
							}else {
								//Released the mouse button on the same cell where it was pressed
								///myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GRAY;
								///myPanel.repaint();
								if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.RED) {
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
								} else {
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
								}
							}
						}
					}
				}
				myPanel.repaint();
				break;
			case 3:		//Right mouse button
				if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.LIGHT_GRAY) {
					//myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
					System.out.println("Entro aqui");
					//Do nothing
				}
				// Check whether there was a flag in the clicked grid or not, if so the grid is changed back to uncovered (White).
				else if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.RED) {
					myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
				} else { // If NOT, a flag is placed in the grid(is painted in red).
					myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
				}
				myPanel.repaint();
				break;
			default:    //Some other button (2 = Middle mouse button, etc.)
				//Do nothing
				break;
		}
	}
}