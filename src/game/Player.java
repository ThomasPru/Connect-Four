package game;

import java.util.Scanner;

import game.Board;

public class Player {
	private String name;
	private int score;
	private int id;
	
	public Player(String name, int score, int id) {
		this.name = name;
		this.score = score;
		this.id = id;
	}
	
	public void jouerUnCoup(Board b) {
		int hauteur = 0;
		int column;
		
		Scanner sc = new Scanner(System.in);
		
		do {
		System.out.println("Veuillez saisir la colonne :");
		column = sc.nextInt()-1;
		}
		while(column < 0 || column > 6 || b.tab[5][column] !=0);
		
		while(b.tab[hauteur][column] != 0 && hauteur <b.tab.length) {
			hauteur ++;
		}
		
		b.tab[hauteur][column] = b.getActualPlayer();
	}
	
	
	public int getId() {
		return id;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return this.name;
	}
}
