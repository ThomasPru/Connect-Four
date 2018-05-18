package game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Graphic extends JFrame implements Observateur{
	
	private ImageIcon americain;
	private ImageIcon communiste;
	private ImageIcon plateau_empty;
	
	private Engine engine;
	
	private String namep1;
	private String namep2;
	
	JLabel turn_player_img = null;
	
	public Graphic(String p1_name, String p2_name, Engine board) {
		this.engine = board;
		this.namep1 = p1_name;
		this.namep2 = p2_name;
		
		BufferedImage ame = null;
		BufferedImage com = null;
		BufferedImage plat = null;
		try {
		    ame = ImageIO.read(new File("ressources/img/americain.png"));
		    com = ImageIO.read(new File("ressources/img/communiste.png"));
		    plat = ImageIO.read(new File("ressources/img/board.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimgAme = ame.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image dimgCom = com.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image dimgPlat = plat.getScaledInstance(350, 300, Image.SCALE_SMOOTH);
		
		this.americain = new ImageIcon(dimgAme);
		this.communiste = new ImageIcon(dimgCom);
		this.plateau_empty = new ImageIcon(dimgPlat);
		
		JPanel TotalContent = new JPanel();
		TotalContent.setPreferredSize(new Dimension(600, 400));
		
		JPanel infosColDroite = new JPanel();
		infosColDroite = CreateRightInfos();
	    
		JPanel infosColGauche = new JPanel();
		infosColGauche = CreateLeftInfos();
		
		TotalContent.add(infosColDroite);
		TotalContent.add(infosColGauche);
		
		setContentPane(TotalContent);
		
		
		setTitle("Connect-Four - 4 aligned pawns and you win !");
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	@Override
	public void actualiser(game.Observable o) {
		if(engine.getActualPlayer() == 1) {
			turn_player_img = new JLabel(americain);
		}
		else {
			turn_player_img = new JLabel(communiste);
		}
		
		JPanel TotalContent = new JPanel();
		TotalContent.setPreferredSize(new Dimension(600, 400));
		
		JPanel infosColDroite = new JPanel();
		infosColDroite = CreateRightInfos();
	    
		JPanel infosColGauche = new JPanel();
		infosColGauche = CreateLeftInfos();
		
		TotalContent.add(infosColDroite);
		TotalContent.add(infosColGauche);
		
		setContentPane(TotalContent);
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
	
	public JPanel CreateLeftInfos() {
		JPanel infosColGauche = new JPanel();
		infosColGauche.setPreferredSize(new Dimension(420, 360));
		infosColGauche.setBorder(BorderFactory.createTitledBorder("THE GAME"));
		infosColGauche.setLayout(null);
		
		JLabel plateau = new JLabel(plateau_empty);
		Insets insetsPlateau = infosColGauche.getInsets();
		Dimension sizePlat = new Dimension(420, 360);
		plateau.setBounds(insetsPlateau.left, insetsPlateau.right, 
				sizePlat.width, sizePlat.height);
		infosColGauche.add(plateau);
		
		Insets insets = infosColGauche.getInsets();
		Dimension size = new Dimension(110, 60);
		
		for(int column = 0; column < engine.getBoard().length; column++) {
			for(int line = 0; line < engine.getBoard()[0].length; line++) {
				switch (engine.getValueCase(column, line)) {
				case 1: 
					JLabel pawnA = new JLabel(americain);
					infosColGauche.add(pawnA);
					pawnA.setBounds(5+50*line+insets.left, 275-50*column+insets.right, 
							size.width, size.height);
				break;
				case 2: 
					JLabel pawnC = new JLabel(communiste);
					infosColGauche.add(pawnC);
					pawnC.setBounds(5+50*line+insets.left, 275-50*column+insets.right, 
							size.width, size.height);
				break;
				}
				
			}
		}

		return infosColGauche;
	}
	
	
	public JPanel CreateRightInfos() {
		JPanel infosColDroite = new JPanel();
		
        //panel.setBounds(61, 11, 81, 140);
		infosColDroite.setLayout(new BoxLayout(infosColDroite, BoxLayout.Y_AXIS));
		
		//-----------------------------------------------------------
		//----Affichage du joueur actuel
		JPanel infosColDroiteHIGH = new JPanel();
		//indique l'arrangement des éléments sur la fenetre
		FlowLayout layoutHIGH = new FlowLayout(FlowLayout.RIGHT);
		
		//affichage du tour en cours
		JLabel turn_player = new JLabel("Tour du joueur : ");
		if(this.engine.getActualPlayer() == 1) {
			turn_player_img = new JLabel(americain);
		}
		else {
			turn_player_img = new JLabel(communiste);
		}
		turn_player_img.setOpaque(true);
		infosColDroiteHIGH.setLayout(layoutHIGH);
		infosColDroiteHIGH.add(turn_player);
		infosColDroiteHIGH.add(turn_player_img);
		
		//-----------------------------------------------------------
		//---Identification joueur 1
		JPanel infosColDroiteP1 = new JPanel();
		//indique l'arrangement des éléments sur la fenetre
		FlowLayout layoutP1 = new FlowLayout(FlowLayout.RIGHT);
		
		//affichage du tour en cours
		JLabel info_p1 = new JLabel(namep1 + " : ");
		JLabel info_p1_img = new JLabel(americain);
		info_p1_img.setPreferredSize(new Dimension(50,50));
		turn_player_img.setOpaque(true);
		infosColDroiteP1.setLayout(layoutP1);
		infosColDroiteP1.add(info_p1);
		infosColDroiteP1.add(info_p1_img);
		//------------------------------------------------------------
		//---Identification joueur 2
		JPanel infosColDroiteP2 = new JPanel();
		//indique l'arrangement des éléments sur la fenetre
		FlowLayout layoutP2 = new FlowLayout(FlowLayout.RIGHT);
		
		//affichage du tour en cours
		JLabel info_p2 = new JLabel(namep2 + " : ");
		JLabel info_p2_img = new JLabel(communiste);
		turn_player_img.setOpaque(true);
		infosColDroiteP2.setLayout(layoutP2);
		infosColDroiteP2.add(info_p2);
		infosColDroiteP2.add(info_p2_img);
		//-------------------------------------------------------------
		
		infosColDroite.add(infosColDroiteHIGH);
		infosColDroite.add(infosColDroiteP1);
		infosColDroite.add(infosColDroiteP2);
		
		return infosColDroite;
	}
	
}
