package robbie.gameoflife.main;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import robbie.gameoflife.cells.Cell;
import robbie.gameoflife.cells.CellManager;

public class GameOfLife {

	private static final String TITLE = "Game of Life";
	
	public static final int CELL_SIZE = 10, CELL_AMOUNT = 50;
	public static final int WIDTH = CELL_SIZE * CELL_AMOUNT, HEIGHT = WIDTH;
	
	public static final Dimension DIMENSIONS = new Dimension(WIDTH, HEIGHT);
	
	private static final boolean RUNNING = true;
	
	public static final Cell[][] CELLS = new Cell[CELL_AMOUNT][CELL_AMOUNT];
	
	public static void main(String[] args) {
		JFrame frame = new JFrame(TITLE);
		frame.setSize(DIMENSIONS);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		GameCanvas canvas = new GameCanvas();
		frame.add(canvas);
		canvas.createBufferStrategy(3);
	
		loadCells();

		CELLS[10][10].setAlive(true);
		CELLS[10][11].setAlive(true);
		CELLS[10][12].setAlive(true);
		
		startGameLoop(canvas);
	}
	
	private static void loadCells() {
		for (int x = 0; x < CELLS.length; x++) {
			for (int y = 0; y < CELLS[x].length; y++) {
				if (CELLS[x][y] == null)
					CELLS[x][y] = new Cell(x, y);
			}
		}
	}
	
	private static void startGameLoop(GameCanvas canvas) {
		Timer timer = new Timer();
		
		if (RUNNING) {
			timer.schedule(new TimerTask() {
	
				@Override
				public void run() {
					canvas.repaint();
					CellManager.updateCells();
				}
				
			}, 0, 2000);
		}
	}

}
