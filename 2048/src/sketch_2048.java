import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
//import java.lang.reflect.Array;

@SuppressWarnings("serial")
public class sketch_2048 extends JPanel{
	
	boolean[][] cells;
	int[][] magCells;
	int width = 600, height = 600;
	

	
	public sketch_2048(boolean[][] in, int[][] search) {
		cells = in;
		magCells = search;
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//width = this.getWidth() / cells[0].length;
		//height = this.getHeight() / cells.length;

		// Draw the cells
		//g.setColor(Color.blue);
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (cells[row][col]) {
					g.setColor(Color.getHSBColor(((float)(42 - magCells[row][col]) % 255) / 255.0f, 220.0f / 255.0f, 180.0f / 255.0f));

					g.fillRect(row * 150, col * 150, 150, 150);
					g.setColor(Color.black);
					g.drawString(Integer.toString(magCells[row][col]), row * 150 + 75, col * 150 + 75);
					g.setColor(Color.white);
				}
			}
		}
	}

	public void setCells(boolean[][] in) {
		cells = in;
	}
	
	public void setMag(int[][] in) {
		magCells = in;
	}

}
