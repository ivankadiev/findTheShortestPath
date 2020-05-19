public class Algorithm {
	
	private Board board;
	private int currentLength, shortestLength;

	
	Algorithm() {
		board = new Board();
	}
	
	
	public void findShortestPath(String direction) {		
		currentLength++;
		board.move(direction);
		board.addCellVisited();
		
		if (board.goalReached()) {
			if (currentLength < shortestLength) {
				shortestLength = currentLength;
				board.saveSolution();
			}
			
			System.out.println("A solution was found with length " + currentLength + "!");
			board.showEndVisuals();
		} else {
			if (board.neighbourCellIsFree(Parameters.LEFT) && (!board.neighbourCellIsVisited(Parameters.LEFT))) {
				findShortestPath(Parameters.LEFT);
			}
			if (board.neighbourCellIsFree(Parameters.UP) && (!board.neighbourCellIsVisited(Parameters.UP))) {
				findShortestPath(Parameters.UP);
			}
			if (board.neighbourCellIsFree(Parameters.RIGHT) && (!board.neighbourCellIsVisited(Parameters.RIGHT))) {
				findShortestPath(Parameters.RIGHT);
			}
			if (board.neighbourCellIsFree(Parameters.DOWN) && (!board.neighbourCellIsVisited(Parameters.DOWN))) {
				findShortestPath(Parameters.DOWN); 
			}
		}
		
		board.removeCellVisited();
		board.moveOppositeDirection(direction);
		currentLength--;
	}
	
	
	public void run() {
		shortestLength = Integer.MAX_VALUE;
		currentLength = 0;
		
		findShortestPath(Parameters.INITIALIZE);
		
		if (!(shortestLength == Integer.MAX_VALUE)) {
			System.out.println("\nThis is (one of) the shortest path(s) with a length of " + shortestLength + "!");
			board.printSolution();
		} else {
			System.out.println("\nThis rendering does not have a solution!");
		}
	}
}
