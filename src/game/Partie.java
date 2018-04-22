package game;

import javax.swing.JOptionPane;


public class Partie {
	
	private Board _board;
	private Player _p1;
	private Player _p2;
	
	public Partie(Board board, Player p1, Player p2) {
		this._board = board;
		this._p1 = p1;
		this._p2 = p2;
		run();
	}
	
	public void run() {
		while(_board.checkWinner() == 0 &&  _board.isBoardFull() == false) {
			_board.switchPlayer();
			System.out.println("Tour du joueur " + _board.getActualPlayer());
			_board.displayBoard();
			if(_board.getActualPlayer() == 1) {
				_p1.jouerUnCoup(_board);
			}
			else {
				_p2.jouerUnCoup(_board);
			}
		}
		_board.displayBoard();
		System.out.println("Le gagnant est : " + _board.getActualPlayer());
	}
	
	
	public static void main(String[] args) {
		String p1_name = JOptionPane.showInputDialog("Nom du joueur 1 :");
		String p2_name = JOptionPane.showInputDialog("Nom du joueur 2 :");
		
		Partie p = new Partie(new Board(1 + (int)(Math.random() * 2)),
								new Player(p1_name, 0, 1),
								new Player(p2_name, 0, 2));
		
	}
}
