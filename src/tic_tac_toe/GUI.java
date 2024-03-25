package tic_tac_toe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JPanel implements ActionListener {
	
	public final int NUM_ROWS = 3;
	public final int NUM_COLS = 3;
	
	public int[][] board = new int[NUM_ROWS][NUM_COLS];
	private static JButton[][] buttons;
	JButton button;
	
    JFrame frame;
	JPanel panel;
	
	public GUI() {}
	
	private Game game;
	public GUI(Game game) {
		this.game = game;
		this.frame = new JFrame();
		this.panel = new JPanel();
		
		// add player labels to board
		JLabel player1 = new JLabel("player1: X");
		JLabel player2 = new JLabel("player2: O");
		
		player1.setBounds(45, 190, 100, 100);			// set position of player1 label
		player2.setBounds(190, 190, 100, 100);			// set position of player2 label
		panel.add(player1);
		panel.add(player2);
		
		// add surrounding border
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		
		// add buttons
		buttons = new JButton[NUM_ROWS][NUM_COLS];
		int buttonSize = 50;
		int x_padding = 12;
		int y_padding = 10;
		for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
            	button = new JButton(String.valueOf(game.EMPTY_SQUARE_SYMBOL));
            	button.addActionListener(this);
                button.setBounds(col * (buttonSize + x_padding)+63, row * (buttonSize + y_padding)+52, buttonSize, buttonSize);
                buttons[row][col] = button;
                add(button);
                panel.add(button);
            }
         }
		
		// add panel to frame and set frame settings
		frame.setSize(300, 300);
		frame.add(panel);
		frame.setContentPane(panel);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tic-Tac-Toe game");
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void winnerLabel(String winner) {
		JLabel winnerLabel = new JLabel(winner + " won!");
		winnerLabel.setBounds(115, -65, 200, 200);
		panel.add(winnerLabel);
		panel.revalidate();
		frame.add(this);
		panel.revalidate();
		panel.repaint();
	}
	
	public void drawLabel() {
		JLabel drawLabel = new JLabel("it's a draw!");
		drawLabel.setBounds(125, -65, 200, 200);
		panel.add(drawLabel);
		frame.add(this);
		panel.revalidate();
		panel.repaint();
	}
	
	private void updateGUI() {
	    for (int row = 0; row < NUM_ROWS; row++) {
	        for (int col = 0; col < NUM_COLS; col++) {
	            if (buttons[row][col].getText().isEmpty()) {
	                buttons[row][col].setText(String.valueOf(board[row][col]));
	            }
	        }
	    }
	}
	
	private void updateBoardArray(JButton buttonClicked, int currentPlayer) {
		int row = -1, col = -1;
	    for (int i = 0; i < NUM_ROWS; i++) {
	        for (int j = 0; j < NUM_COLS; j++) {
	            if (buttons[i][j] == buttonClicked) {
	                row = i;
	                col = j;
	                break;
	            }
	        }
	        if (row != -1 && col != -1) {
	            break;
	        }
	    }
	    if (currentPlayer == game.PLAYER1) {
	    	board[row][col] = game.PLAYER1_SYMBOL;
	    }
	    else {
	    	board[row][col] = game.PLAYER2_SYMBOL;
	    }
	}
	
	private void disableButtons() {
		for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
	}
		
	private int counter = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();
		int currentPlayer = game.getCurrentPlayer();
		if (currentPlayer == game.PLAYER1) {
			buttonClicked.setText(String.valueOf(game.PLAYER1_SYMBOL));
			buttonClicked.setEnabled(false);
		}
		else {
			buttonClicked.setText(String.valueOf(game.PLAYER2_SYMBOL));
			buttonClicked.setEnabled(false);
		}

		updateGUI();
		updateBoardArray(buttonClicked, currentPlayer);
		counter++;
		
		if (!game.isGameOver(this, counter)) {
			game.switchPlayerTurn();
		}
		else {
			disableButtons();		
		}
	}
	
}
