import javax.swing.JLabel;

public class Board {

	private Canvas board;
	private JLabel[][] solution;
	private boolean[][] visitedCells;
	private int xPosition, yPosition;
	
	
	Board() {    	
		visitedCells = new boolean[Parameters.X_BOXES][Parameters.Y_BOXES];
		solution = new JLabel[Parameters.X_BOXES][Parameters.Y_BOXES];
		
		xPosition = Parameters.INITIAL_X_POSITION;
		yPosition = Parameters.INITIAL_Y_POSITION;
		
		board = new Canvas();
	}		
	
	
	public void move(String direction) {
		switch (direction) {
		case Parameters.LEFT: updateVisuals(Parameters.PATH, xPosition, --yPosition); break;
		case Parameters.UP: updateVisuals(Parameters.PATH, --xPosition, yPosition); break;	
		case Parameters.RIGHT: updateVisuals(Parameters.PATH, xPosition, ++yPosition); break;	
		case Parameters.DOWN: updateVisuals(Parameters.PATH, ++xPosition, yPosition); break;
		} 
	}
	
	
	public void moveOppositeDirection(String direction) {
		switch (direction) {
		case Parameters.LEFT: updateVisuals(Parameters.EMPTY, xPosition, yPosition++); break;
		case Parameters.UP: updateVisuals(Parameters.EMPTY, xPosition++, yPosition); break;
		case Parameters.RIGHT: updateVisuals(Parameters.EMPTY, xPosition, yPosition--); break;
		case Parameters.DOWN: updateVisuals(Parameters.EMPTY, xPosition--, yPosition); break;
		} 
	}
	
	
	private void updateVisuals(String object, int x, int y) {
		try {
			Thread.sleep(Parameters.WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (!(board.isEnd(x, y))) {
			board.changeLabelTo(object, x, y);
		}
	}
	
	
	public void showEndVisuals() {
		board.changeLabelTo(Parameters.PATH, xPosition, yPosition);
		
		try {
			Thread.sleep(Parameters.WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		board.changeLabelTo(Parameters.END, xPosition, yPosition);
	}
	
	
	public boolean neighbourCellIsFree(String direction) {
		switch (direction) {
		case Parameters.LEFT: return (!(board.isBoulder(xPosition, yPosition-1))); 
		case Parameters.UP: return (!(board.isBoulder(xPosition-1, yPosition))); 
		case Parameters.RIGHT: return (!(board.isBoulder(xPosition, yPosition+1))); 
		case Parameters.DOWN: return (!(board.isBoulder(xPosition+1, yPosition)));
		} 
		
		System.out.println("Incorrect command.");
		System.exit(0);
		return true;
	}
	
	
	public boolean neighbourCellIsVisited(String direction) {
		switch (direction) {
		case Parameters.LEFT: return (visitedCells[xPosition][yPosition-1]); 
		case Parameters.UP: return (visitedCells[xPosition-1][yPosition]); 
		case Parameters.RIGHT: return (visitedCells[xPosition][yPosition+1]);
		case Parameters.DOWN: return (visitedCells[xPosition+1][yPosition]);
		} 
		
		System.out.println("Incorrect command.");
		System.exit(0);
		return true;
	}
	
	
	public void addCellVisited() {
		visitedCells[xPosition][yPosition] = true;
	}
	
	
	public void removeCellVisited() {
		visitedCells[xPosition][yPosition] = false;
	}
	
	
	public boolean goalReached() {
		return (board.isEnd(xPosition, yPosition));
	}
	
	
	public void saveSolution() {
		for (int x=0; x<Parameters.X_BOXES; x++) {
    		for (int y=0; y<Parameters.Y_BOXES; y++) {
    			solution[x][y] = new JLabel();
    			solution[x][y].setIcon(board.getJLabel(x, y).getIcon()); 
    		}
    	}
	}
	
	
	public void printSolution() {
		board.printBoard(solution);
	}
}
