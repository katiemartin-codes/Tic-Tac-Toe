package tic_tac_toe;

public class Game {

	public final int PLAYER1 = 1;
	public final int PLAYER2 = 2;
	public final char PLAYER1_SYMBOL = 'X';
	public final char PLAYER2_SYMBOL = 'O';
	public final char EMPTY_SQUARE_SYMBOL = '-';
	public static final int MAX_MOVES = 9;
	public static final int MIN_MOVES_FOR_WIN = 5;
	
	private int currentPlayer = PLAYER1;
	
	// check for win variables
	int player1Counter;
	int player2Counter;
	int checkPlayerWin = 0;
	
	private GUI gui;
	
	public Game() {}
	
	public Game(GUI gui) {
		this.gui = gui;
	}
	
	public void switchPlayerTurn() {
		if (this.currentPlayer == PLAYER1) {
			this.currentPlayer = PLAYER2;
		}
		else {
			this.currentPlayer = PLAYER1;
		}
	}
	
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public boolean checkRowWin(GUI gui) {
		this.gui = gui;
		
		for (int row = 0; row < gui.NUM_ROWS; row++) {
	        player1Counter = 0;
	        player2Counter = 0;
	        for (int col = 0; col < gui.NUM_COLS; col++) {
	            if (gui.board[row][col] == EMPTY_SQUARE_SYMBOL) {
	                break; // Row win is not possible if there's an empty square
	            } 
	            else if (gui.board[row][col] == PLAYER1_SYMBOL) {
	                player1Counter++;
	            } 
	            else if (gui.board[row][col] == PLAYER2_SYMBOL) {
	                player2Counter++;
	            }
	        }
            // check for win after each outer loop iteration
            if (player1Counter == 3 || player2Counter == 3) {
                return true;
            }
	    }
	    return false;
	}

	public boolean checkColWin(GUI gui) {
		this.gui = gui;
		
		for (int col = 0; col < gui.NUM_ROWS; col++) {
	        player1Counter = 0;
	        player2Counter = 0;
	        for (int row = 0; row < gui.NUM_COLS; row++) {
	            if (gui.board[row][col] == EMPTY_SQUARE_SYMBOL) {
	                break; // Row win is not possible if there's an empty square
	            } 
	            else if (gui.board[row][col] == PLAYER1_SYMBOL) {
	                player1Counter++;
	            } 
	            else if (gui.board[row][col] == PLAYER2_SYMBOL) {
	                player2Counter++;
	            }
	        }
            // check for win after each outer loop iteration
            if (player1Counter == 3 || player2Counter == 3) {
                return true;
            }
	    }
	    return false;
	}

	public boolean checkDiagWin() {
		int centerSymbol = gui.board[1][1];
		
		if (centerSymbol == EMPTY_SQUARE_SYMBOL) {
	        return false;
	    }

	    // check diagonals
	    if ((centerSymbol == gui.board[0][0] && centerSymbol == gui.board[2][2]) ||
	        (centerSymbol == gui.board[0][2] && centerSymbol == gui.board[2][0])) {
	        return true;
	    }
	    return false;
	}
	
	public String whoWon(int currentPlayer) {
		String winner;
		if (currentPlayer == PLAYER1) {
			winner = "player1";
		}
		else {
			winner = "player2";
		}
		return winner;
	}
	
	public boolean isGameOver(GUI gui, int counter) {
		this.gui = gui;
		boolean rowWin = false, colWin = false, diagWin = false;
		
		if (counter >= MIN_MOVES_FOR_WIN) {
			rowWin = checkRowWin(gui);
			colWin = checkColWin(gui);
			diagWin = checkDiagWin();
			if (rowWin == true || colWin == true || diagWin == true) {
				String winner = whoWon(currentPlayer);
				gui.winnerLabel(winner);
				return true;
			}
		}
		if (counter == MAX_MOVES) {
			gui.drawLabel();
			return true;
		}
		return false;	
	}
	
}


