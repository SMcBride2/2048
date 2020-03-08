import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
//import java.awt.KeyEventDispatcher;
//import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.event.KeyListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.util.Random;
//import java.util.Timer;
import java.lang.Object.*;

//import javax.imageio.ImageIO;

public class make_2048 implements ActionListener {//KeyListener { //KeyEventDispatcher {//, {
	//private InputHandler inputHandler;
	
	static boolean[][] cells = new boolean[4][4];
	static int[][] magCells = new int[4][4];
	

	int[][] nCells;
	static JFrame frame = new JFrame("2048 simulation");
	static sketch_2048 panel = new sketch_2048(cells, magCells);
	JButton left = new JButton("<");
	JButton right = new JButton(">");
	JButton up = new JButton("/\\");
	JButton down = new JButton("\\/");
	JPanel bar;
	//Timer keyRepeatTimer;


/*
    public synchronized void init() {
        if (!isInited()) {
            keyRepeatTimer = new Timer("Key Repeat Timer");
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
        }
    }


    public synchronized boolean isInited() {
        return keyRepeatTimer != null;
    }*/
	
	public make_2048() {
		frame.setSize(600,640);;
		frame.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		bar.setLayout(new BorderLayout(4,1));
		bar.add(left);
		bar.add(up);
		bar.add(right);
		bar.add(down);
		frame.add(bar, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new make_2048();
		loadState();
		panel.setCells(cells);
		panel.setMag(magCells);
		frame.repaint();
		//moveUp();
		//newCell();
		moveLeft();
		newCell();
		moveLeft();
		newCell();
		System.out.println("Encrypt");
	}
	
	public static void loadState() {
		magCells[0][0] = 2;
		cells[0][0] = true;
		magCells[2][3] = 2;
		cells[2][3] = true;
		magCells[1][1] = 4;
		cells[1][1] = true;
		//magCells[3][0] = 2;
		
		/*for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (magCells[i][j] > 0) {
					cells[i][j] = true;
				}
			}
		}*/
	}
	
	public static void moveUp() {
		int[] combineSlot = new int[4];

		for (int row = 1; row < 4; row++) {
			combineSlot[row] = 0;
			for (int col = 0; col < 4; col++) {
				if (cells[row][col]) {
					if (cells[row][combineSlot[row]]) {
						if (magCells[row][combineSlot[row]] == magCells[row][col]) {
							magCells[row][combineSlot[row]] *= 2;
							magCells[row][col] = 0;
							cells[row][col] = false;
							combineSlot[row]++;
						} else {
							if (col > combineSlot[row] + 1) {
								combineSlot[row]++;
								magCells[row][combineSlot[row]] = magCells[row][col];
								cells[row][combineSlot[row]] = true;
								magCells[row][col] = 0;
								cells[row][col] = false;
							} else {
								combineSlot[row]++;
							}
						}
					} else {
						magCells[row][combineSlot[row]] = magCells[row][col];
						cells[row][combineSlot[row]] = true;
						magCells[row][col] = 0;
						cells[row][col] = false;
						combineSlot[row]++;
					}
				}
			}
		}
	}
	
	public static void moveDown() {
		int[] combineSlot = new int[4];

		for (int row = 2; row >= 0; row--) {
			combineSlot[row] = 3;
			for (int col = 0; col < 4; col++) {
				if (cells[row][col]) {
					if (cells[row][combineSlot[row]]) {
						if (magCells[row][combineSlot[row]] == magCells[row][col]) {
							magCells[row][combineSlot[row]] *= 2;
							magCells[row][col] = 0;
							cells[row][col] = false;
							combineSlot[row]--;
						} else {
							if (col > combineSlot[row] - 1) {
								combineSlot[row]--;
								magCells[row][combineSlot[row]] = magCells[row][col];
								cells[row][combineSlot[row]] = true;
								magCells[row][col] = 0;
								cells[row][col] = false;
							} else {
								combineSlot[row]--;
							}
						}
					} else {
						magCells[row][combineSlot[row]] = magCells[row][col];
						cells[row][combineSlot[row]] = true;
						magCells[row][col] = 0;
						cells[row][col] = false;
						combineSlot[row]--;
					}
				}
			}
		}
	}
	
	public static void moveLeft() {
		int[] combineSlot = new int[4];

		for (int row = 0; row < 4; row++) {
			for (int col = 1; col < 4; col++) {
				combineSlot[col] = 0;
				if (cells[col][row]) {
					if (cells[combineSlot[col]][col]) {
						if (magCells[combineSlot[col]][col] == magCells[col][row]) {
							magCells[combineSlot[col]][col] *= 2;
							magCells[col][row] = 0;
							cells[col][row] = false;
							combineSlot[row]++;
						} else {
							if (col > combineSlot[row] + 1) {
								combineSlot[row]++;
								magCells[combineSlot[col]][col] = magCells[col][row];
								cells[combineSlot[col]][col] = true;
								magCells[col][row] = 0;
								cells[col][row] = false;
							} else {
								combineSlot[row]++;
							}
						}
					} else {
						magCells[combineSlot[col]][col] = magCells[col][row];
						cells[combineSlot[col]][col] = true;
						magCells[col][row] = 0;
						cells[col][row] = false;
						combineSlot[row]++;
					}
				}
			}
		}
	}
	
	public static void moveRight() {
		int[] combineSlot = new int[4];

		for (int row = 0; row < 4; row++) {
			for (int col = 2; col >= 0; col--) {
				combineSlot[col] = 3;
				if (cells[col][row]) {
					if (cells[combineSlot[col]][col]) {
						if (magCells[combineSlot[col]][col] == magCells[col][row]) {
							magCells[combineSlot[col]][col] *= 2;
							magCells[col][row] = 0;
							cells[col][row] = false;
							combineSlot[row]--;
						} else {
							if (col > combineSlot[row] - 1) {
								combineSlot[row]--;
								magCells[combineSlot[col]][col] = magCells[col][row];
								cells[combineSlot[col]][col] = true;
								magCells[col][row] = 0;
								cells[col][row] = false;
							} else {
								combineSlot[row]--;
							}
						}
					} else {
						magCells[combineSlot[col]][col] = magCells[col][row];
						cells[combineSlot[col]][col] = true;
						magCells[col][row] = 0;
						cells[col][row] = false;
						combineSlot[row]--;
					}
				}
			}
		}
	}
	
	public static void newCell() {
		Random n = new Random(1);
		int x = n.nextInt(4);
		int y = n.nextInt(4);
		while (cells[x][y]) {
			x = n.nextInt(4);
			y = n.nextInt(4);
		}
		if (!cells[x][y]) {
			cells[x][y] = true;
			if (new Random(1).nextDouble() < 0.1) {
				magCells[x][y] = 4;
			} else {
				magCells[x][y] = 2;
			}
		}
	}
/*
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		int key = arg0.getKeyCode();
		int[] combineSlot = new int[4];
		int combNum = 0;
		System.out.println(key);
		if (key == KeyEvent.VK_LEFT) {
			System.out.println("Left");
			
			for (int row = 1; row < 4; row++) {
				combineSlot[row] = 0;
				for (int col = 1; col < 4; col++) {
					if (cells[row][col]) {
						if (cells[row][combineSlot[row]]) {
							if (magCells[row][combineSlot[row]] == magCells[row][col]) {
								magCells[row][combineSlot[row]] *= 2;
								magCells[row][col] = 0;
								cells[row][col] = false;
								combineSlot[row]++;
							} else {
								if (col > combineSlot[row] + 1) {
									combineSlot[row]++;
									magCells[row][combineSlot[row]] = magCells[row][col];
									cells[row][combineSlot[row]] = true;
									magCells[row][col] = 0;
									cells[row][col] = false;
								} else {
									combineSlot[row]++;
								}
							}
						} else {
							magCells[row][combineSlot[row]] = magCells[row][col];
							cells[row][combineSlot[row]] = true;
							magCells[row][col] = 0;
							cells[row][col] = false;
							combineSlot[row]++;
						}
					}
				}
			}
		} else if (key == KeyEvent.VK_RIGHT) {
			for (int row = 0; row < 4; row++) {
				for (int col = 2; col >= 0; col--) {
					
				}
			}
			
		} else if (key == KeyEvent.VK_UP) {
			
		} else if (key == KeyEvent.VK_DOWN) {
			
		}
		arg0.consume();
		newCell();
		panel.setCells(cells);
		panel.setMag(magCells);
		frame.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

		int key = arg0.getKeyCode();
		System.out.println(key);
		
	}
/*
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		int[] combineSlot = new int[4];
		int combNum = 0;
		System.out.println(key);
		if (key == KeyEvent.VK_LEFT) {
			System.out.println("Left");
			
			for (int row = 1; row < 4; row++) {
				combineSlot[row] = 0;
				for (int col = 1; col < 4; col++) {
					if (cells[row][col]) {
						if (cells[row][combineSlot[row]]) {
							if (magCells[row][combineSlot[row]] == magCells[row][col]) {
								magCells[row][combineSlot[row]] *= 2;
								magCells[row][col] = 0;
								cells[row][col] = false;
								combineSlot[row]++;
							} else {
								if (col > combineSlot[row] + 1) {
									combineSlot[row]++;
									magCells[row][combineSlot[row]] = magCells[row][col];
									cells[row][combineSlot[row]] = true;
									magCells[row][col] = 0;
									cells[row][col] = false;
								} else {
									combineSlot[row]++;
								}
							}
						} else {
							magCells[row][combineSlot[row]] = magCells[row][col];
							cells[row][combineSlot[row]] = true;
							magCells[row][col] = 0;
							cells[row][col] = false;
							combineSlot[row]++;
						}
					}
				}
			}
		} else if (key == KeyEvent.VK_RIGHT) {
			for (int row = 0; row < 4; row++) {
				for (int col = 2; col >= 0; col--) {
					
				}
			}
			
		} else if (key == KeyEvent.VK_UP) {
			
		} else if (key == KeyEvent.VK_DOWN) {
			
		}
		newCell();
		panel.setCells(cells);
		panel.setMag(magCells);
		frame.repaint();
		return false;
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
