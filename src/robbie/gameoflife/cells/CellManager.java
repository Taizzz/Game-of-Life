package robbie.gameoflife.cells;

import java.util.ArrayList;

import robbie.gameoflife.main.GameOfLife;

public class CellManager {

	public static ArrayList<Cell> getNeighbourCells(Cell cell) {
		ArrayList<Cell> neighbourCells = new ArrayList<Cell>();
		int cellX = cell.getX();
		int cellY = cell.getY();
		
		for (int x = cellX - 1; x <= cellX + 1; x++) {
			for (int y = cellY - 1; y <= cellY + 1; y++) {
				if (x < 0 || x >= GameOfLife.CELL_AMOUNT || y < 0 || y >= GameOfLife.CELL_AMOUNT)
					continue;
				
				Cell neighbourCell = GameOfLife.CELLS[x][y];
				if (neighbourCell.equals(cell))
					continue;
				
				if (neighbourCell.isAlive())
					neighbourCells.add(neighbourCell);
			}
		}
		
		return neighbourCells;
	}
	
	public static int getNeighbourCount(Cell cell) {
		return getNeighbourCells(cell).size();
	}
	
	public static void updateCells() {
		ArrayList<Cell> deadCells = new ArrayList<Cell>();
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		
		for (Cell[] cells : GameOfLife.CELLS) {
			for (Cell cell : cells) {
				int neighbourCount = CellManager.getNeighbourCount(cell);
				if (neighbourCount < 2 || neighbourCount > 3)
					deadCells.add(cell);
				else if (cell.isAlive() && (neighbourCount == 2 || neighbourCount == 3))
					aliveCells.add(cell);
				else if (!cell.isAlive() && neighbourCount == 3)
					aliveCells.add(cell);
			}
		}

		for (Cell cell : aliveCells)
			cell.setAlive(true);

		for (Cell cell : deadCells)
			cell.setAlive(false);
	}
	
}
