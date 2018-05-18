package game;

import java.util.ArrayList;

public class Engine implements Observable{
	protected int tab[][];
	private int current_turn;
	private ArrayList tabObservateur;
	
	public Engine(int startinPlayer) {
		tabObservateur=new ArrayList();
		this.tab = new int[6][7];
		this.current_turn = startinPlayer;
		
		for (int[] is : tab) {
			for (int i : is) {
				is[i] = 0;
			}
		}
	}
	
	public void switchPlayer() {
		if(getActualPlayer() == 1) {
			this.current_turn = 2;
		}
		else {
			this.current_turn = 1;	
		}
		
	}
	
	public int[][] getBoard() {
		return tab;
	}
	
	public void setValue(int column, int line) {
		this.tab[column][line] = 0;
	}
	
	public int getValueCase(int column, int line) {
		return this.tab[column][line];
	}
	
	public int getActualPlayer() {
		return this.current_turn;
	}
	
	public void displayBoard() {
		for(int i = tab.length-1; i >=0 ; i--) {
			for(int j = 0; j < tab[0].length; j++) {
				//System.out.print(i + ","+ j + " ");
				
				switch (tab[i][j]) {
				case 0: System.out.print(" . ");
				break;
				case 1: System.out.print(" X ");
				break;
				case 2: System.out.print(" O ");
				break;
				}
				
			}
			System.out.println();
		}
		notifierObservateurs();
	}
	

	public boolean isBoardFull() {
		for(int line = 0; line < tab.length; line++) {
			for(int column = 0; column < tab[0].length; column++) {
				if(tab[line][column] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public int checkWinner() {
		int winner = 0;
		
		int countColorInARow = 0;
		for(int column = 0; column < tab.length; column++) {
			for(int line = 0; line < tab[1].length; line++) {
				if(getValueCase(column, line) == getActualPlayer()) {
					countColorInARow++;
				}
				else {
					countColorInARow = 0;
				}
				if(countColorInARow == 4) {
					winner = getActualPlayer();
				}
			}
			countColorInARow = 0;
		}
		
		
		for(int line = 0; line < tab[1].length; line++) {
			for(int column = 0; column < tab.length; column++) {
				if(getValueCase(column, line) == getActualPlayer()) {
					countColorInARow++;
				}
				else {
					countColorInARow = 0;
				}
				if(countColorInARow == 4) {
					winner = getActualPlayer();
				}
			}
			countColorInARow = 0;
		}
		
		
		
		//TODO  CHECK DIAGONALE
		
		for(int line = 0; line < tab[1].length-4; line++) {
			for(int column = 0; column < tab.length-4; column++) {
				if(getValueCase(column, line) == getActualPlayer()) {
					countColorInARow++;
					for(int i = 1; i < 4; i++) {
						if(getValueCase(column+i, line+i) == getActualPlayer()) {
							countColorInARow++;
						}
					}
					if(countColorInARow == 4) {
						winner = getActualPlayer();
					}
					countColorInARow = 0;
				}
				else {
					countColorInARow = 0;
				}
			}
			countColorInARow = 0;
		}
		
		
		// * ceci est la diago descendante et ne fonctionne pas
		for(int line = tab[1].length-1; line > 2 ; line--) {
			for(int column = 0; column < tab.length-4; column++) {
				if(getValueCase(column, line) == getActualPlayer()) {
					countColorInARow++;
					for(int i = 1; i < 4; i++) {
						if(getValueCase(column+i, line-i) == getActualPlayer()) {
							countColorInARow++;
						}
					}
					if(countColorInARow == 4) {
						winner = getActualPlayer();
					}
					countColorInARow = 0;
				}
				else {
					countColorInARow = 0;
				}
			}
			countColorInARow = 0;
		}
		
		
		
		
		return winner;
	}

	@Override
	public void ajouterObservateur(Observateur o) {
		tabObservateur.add(o);
	}

	@Override
	public void supprimerObservateur(Observateur o) {
		tabObservateur.remove(o);
	}

	@Override
	public void notifierObservateurs() {
		for(int i = 0; i < tabObservateur.size(); i++) {
			Observateur o = (Observateur) tabObservateur.get(i);
            o.actualiser(this);// On utilise la méthode "tiré".
		}
	}
}



















