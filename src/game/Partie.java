package game;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Partie {
	
	private Engine _board;
	private Player _p1;
	private Player _p2;
	private Graphic _fenetre;
	
	public Partie(Engine engine, String p1_name, String p2_name) {
		this._board = engine;
		this._p1 = new Player(p1_name, 0, 1);
		this._p2 = new Player(p2_name, 0, 2);
        
		this._fenetre = new Graphic(p1_name, p2_name, engine);
		this._board.ajouterObservateur(_fenetre);
		run();
	}
	
	
	public void run() {
		_board.displayBoard();
		while(_board.checkWinner() == 0 &&  _board.isBoardFull() == false) {
			_board.switchPlayer();
			System.out.println("Tour du joueur " + _board.getActualPlayer());
			if(_board.getActualPlayer() == 1) {
				_p1.jouerUnCoup(_board);
			}
			else {
				_p2.jouerUnCoup(_board);
			}
			_board.displayBoard();
		}
		System.out.println("--------------------------------");
		_board.displayBoard();
		System.out.println("Le gagnant est : " + _board.getActualPlayer());
	}
	
	
	public static void main(String[] args) {
		String p1_name = JOptionPane.showInputDialog("Nom du joueur 1 :");
		String p2_name = JOptionPane.showInputDialog("Nom du joueur 2 :");
		
		Partie p = new Partie(new Engine(1 + (int)(Math.random() * 2)), p1_name, p2_name);
	}
}
