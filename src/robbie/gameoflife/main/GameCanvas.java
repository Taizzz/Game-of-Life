package robbie.gameoflife.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import robbie.gameoflife.cells.Cell;

public class GameCanvas extends Canvas {

	public GameCanvas() {
		super();
		
		this.setSize(GameOfLife.WIDTH, GameOfLife.HEIGHT);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		Graphics graphics = this.getGraphics();
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		Random random = new Random();
		graphics.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));

		for (Cell[] cells : GameOfLife.CELLS) {
			for (Cell cell : cells) {
				if (cell.isAlive()) {
					int size = GameOfLife.CELL_SIZE;
					int x = cell.getX() * size;
					int y = cell.getY() * size;

					graphics.fillRect(x, y, size, size);
				}
			}
		}
	}
	
}
