package org.etherton.life;

/**
 * Class used to represent a generation in the game of life. The class
 * can also create the nextGeneration, thus you could chain generations
 * if you'd like.
 * @author etherton
 *
 */
public class Generation {
	private boolean[][] board;
	
	
	/**
	 * Constructor, takes in a board, which for the sake of this class
	 * is a 2D array of boolean values
	 * @param board
	 */
	public Generation(boolean[][] board){
		this.board = board;
	}
	
	 /**
     * This method will take in a 2d array with true representing
     * alive cells and false representing dead cells.
     * 
     * This will evolve the cells according to these rules:
     * 1) Any live cell with fewer than two live neighbors dies (under-population)
     * 2) Any live cell with two or three live neighbors lives on to
     * 	the next generation (survival)
     * 3) Any live cell with more than three live neighbors dies (overcrowding)
     * 4) Any dead cell with exactly three live neighbors becomes 
     * 	alive cell (reproduction)
     * 
     * arrays must row, column ordering, and every column must be the same
     * length. 2d Array must have a width and height of 1 or greater.
     *  
     * @return the output board with the updated cells
     */
    public Generation createNextGeneration(){
    	//check for error conditions
    	if(board.length == 0 || board[0].length == 0) {
    		throw new IllegalArgumentException("Board must have some cells");
    	}
    	
    	boolean[][] nextGenBoard = new boolean[board.length][board[0].length];
    	
    	//iterate over the cells and figure out there new state
    	for(int x = 0; x < board.length; x++){
    		for(int y = 0; y < board[x].length; y++){
    			//figure out how many living neighbors this cell has
    			int livingNeighbors = calcLivingNeighbors(x, y);
    			//if the cell is already living
    			if(board[x][y]){
    				//rule 1
    				if(livingNeighbors < 2){
    					nextGenBoard[x][y] = false;
    				//rule 2
    				} else if(livingNeighbors == 2 || livingNeighbors == 3){
    					nextGenBoard[x][y] = true;
    				//rule 3
    				} else if(livingNeighbors > 3){
    					nextGenBoard[x][y] = false;
    				}
    			} else { //cell is currently dead
    				if(livingNeighbors == 3){
    					nextGenBoard[x][y] = true;
    				} else {
    					//thought it's not explicit stated in the requirements, we'll assume
    					//that a dead cell with not 3 living neighbors stays dead.
    					nextGenBoard[x][y] = false;
    				}
    			}
    		}
    	}
    	
    	return new Generation(nextGenBoard);    	
    }
    
    /**
     * Given a 2d boolean array and the coordinates of a specific
     * cell, this counts the number of neighboring
     * cells that have a true value.
     * @param board 2d array of living or dead cells
     * @param x row index of the cell in questions
     * @param y column index of the cell in question
     * @return number of living neighbors to the cell in question
     */
    public int calcLivingNeighbors(int x, int y){
    	int retVal = 0;
    	
    	for(int i = x - 1; i <= x + 1; i++){
    		for(int j = y - 1; j <= y + 1; j++){
    			//Check if our indexes are less than zero
    			//or if they're greater than their bounds
    			//or if they're the cell in question
    			//if so, don't bother checking
    			if(i < 0 || j < 0 || 
    					i >= board.length || j >= board[i].length ||
    					(x == i && y == j)){
    				continue;
    			}    			
    			//finally, if living, add it
    			if(board[i][j]){
    				retVal++;
    			}
    		}
    	}
    	
    	return retVal;
    }
    
    /**
     * Just your average toString
     */
    @Override
    public String toString(){
    	String retVal = "";
    	for(int x = 0; x < board.length; x++){
    		retVal = retVal.length() > 0 ? retVal + "\n" : retVal;
    		String line = "";
    		for(int y = 0; y < board[x].length; y++){
    			line = line.length() > 0 ? line + " " : line;
    			line = line + (board[x][y] ? "1" : "0");
    		}
    		retVal += line;
    	}
    	return retVal;
    }
    
    
    /**
     * Used for simple testing
     * @param args
     */
    public static void main( String[] args )
    {
        boolean[][] firstGenBoard = {
        		{false, true, false, false, false},
        		{true, false, false, true, true},
        		{true, true, false, false, true},
        		{false, true, false, false, false},
        		{true, false, false, false, true}
        };
        
        Generation gen1 = new Generation(firstGenBoard);
        Generation gen2 = gen1.createNextGeneration();
        
        System.out.println(gen2);
    }
}
