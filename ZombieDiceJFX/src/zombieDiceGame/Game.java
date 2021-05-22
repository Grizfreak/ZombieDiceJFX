package zombieDiceGame;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;
import zombieDiceGame.Dice.symbole;

public class Game {
	private Gobelet tas;
	private Player j1;
	private Player j2;
	private Player j3;
	private Player j4;
	private String difficulty;
	private int fusils_en_cours;
	private int cerveaux_en_cours;
	private ArrayList<Dice> des_empreintes;
	private Gobelet copytas;
	private int nbPlayers=0;
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	private Player currentPlayer;
	private Dice d1;
	private Dice d2;
	private Dice d3;
	private String face1;
	private String face2;
	private String face3;

	public Game(String difficulty,Player j1,Player j2, Player j3, Player j4) {
		tas = new Gobelet(difficulty);
		des_empreintes= new ArrayList<Dice>();
		this.difficulty=difficulty;
		this.j1=j1;
		if(j1 !=null)nbPlayers++;
		this.j2=j2;
		if(j2 !=null)nbPlayers++;
		this.j3=j3;
		if(j3 !=null)nbPlayers++;
		this.j4=j4;
		if(j4 !=null)nbPlayers++;
		currentPlayer=j1;
	}
	public Game(String difficulty,Player j1,Player j2) {
		this(difficulty,j1,j2,null,null);
	}
	public Game(String difficulty,Player j1,Player j2, Player j3) {
		this(difficulty,j1,j2,j3,null);
	}


	public void jeterLesDes() {
		Random rando = new Random();
		if(tas.size()+des_empreintes.size()<3) {
			System.out.println("Impossible de jouer CAUSE : plus de d�s");
			finirTour();
			return;
		}
		switch(des_empreintes.size()) {
		case 1:
			d1=des_empreintes.get(0);
			des_empreintes.remove(d1);
			d2 = tas.getDice(rando.nextInt(Gobelet.nb_dice));
			tas.removeDice(d2);
			d3 = tas.getDice(rando.nextInt(Gobelet.nb_dice));
			tas.addDice(d2);
			break;
		case 2:
			d1=des_empreintes.get(0);
			d2=des_empreintes.get(1);
			des_empreintes.remove(d1);
			des_empreintes.remove(d2);
			d3 = tas.getDice(rando.nextInt(Gobelet.nb_dice));
			break;
		case 3:
			d1=des_empreintes.get(0);
			d2=des_empreintes.get(1);
			d3=des_empreintes.get(2);
			des_empreintes.remove(d1);
			des_empreintes.remove(d2);
			des_empreintes.remove(d3);
			break;
		default:
			d1 = tas.getDice(rando.nextInt(Gobelet.nb_dice));
			tas.removeDice(d1);
			d2 = tas.getDice(rando.nextInt(Gobelet.nb_dice));
			tas.removeDice(d2);
			d3 = tas.getDice(rando.nextInt(Gobelet.nb_dice));
			tas.addDice(d1);
			tas.addDice(d2);
			break;
		}
		face1 = d1.throwDice();
		face2 = d2.throwDice();
		face3 = d3.throwDice();
		System.out.println(d1+" | "+face1);
		System.out.println(d2+" | "+face2);
		System.out.println(d3+" | "+face3);
		if(face1.equals(symbole.CERVEAU.toString())) {
			cerveaux_en_cours++;
			tas.removeDice(d1);
		}
		if(face1.equals(symbole.FUSIL.toString())) {
			fusils_en_cours++;
			tas.removeDice(d1);
		}
		if(face1.equals(symbole.EMPREINTE.toString())) {
			tas.removeDice(d1);
			des_empreintes.add(d1);
		}
		if(face2.equals(symbole.CERVEAU.toString())) {
			cerveaux_en_cours++;
			tas.removeDice(d2);
		}
		if(face2.equals(symbole.FUSIL.toString())) {
			fusils_en_cours++;
			tas.removeDice(d2);
		}
		if(face2.equals(symbole.EMPREINTE.toString())) {
			tas.removeDice(d2);
			des_empreintes.add(d2);
		}
		if(face3.equals(symbole.CERVEAU.toString())) {
			cerveaux_en_cours++;
			tas.removeDice(d3);
		}
		if(face3.equals(symbole.FUSIL.toString())) {
			fusils_en_cours++;
			tas.removeDice(d3);
		}
		if(face3.equals(symbole.EMPREINTE.toString())) {
			tas.removeDice(d3);
			des_empreintes.add(d3);
		}
		if(fusils_en_cours>=3) {
			System.out.println("Pompe mort");
			finirTour();
		}
		
	}
	public int getFusils_en_cours() {
		return fusils_en_cours;
	}
	public int getCerveaux_en_cours() {
		return cerveaux_en_cours;
	}
	public Object[] getLaunchedDices() {
		Object obj[]= new Object[6];
		obj[0]=d1;
		obj[1]=d2;
		obj[2]=d3;
		obj[3]=face1;
		obj[4]=face2;
		obj[5]=face3;
		return obj;
		
	}

	public void finirTour() {
		//TODO FAIRE MARCHER LA FONCTION
		tas.reinitialize();
		System.out.println("FIN DU TOUR DU "+currentPlayer);
		for(Dice dice : des_empreintes) {
			tas.addDice(dice);
		}
		tas.afficheGobelet();
		if(tas.size()+des_empreintes.size()<3) {
			currentPlayer.addCerveaux(cerveaux_en_cours);
			fusils_en_cours=0;
			return;
		}
		if(fusils_en_cours>=3) {
			cerveaux_en_cours=0;
			fusils_en_cours=0;
			GameController.isDead=true;
			return;
		}
		currentPlayer.addCerveaux(cerveaux_en_cours);
		if(currentPlayer.equals(j1) && j2!=null) {
			cerveaux_en_cours=0;
			fusils_en_cours=0;
			//currentPlayer=j2;
			return;
		}
		if(currentPlayer.equals(j2) && j3!=null) {
			cerveaux_en_cours=0;
			fusils_en_cours=0;
			//currentPlayer=j3;
			return;
		}
		if(currentPlayer.equals(j3) && j4!=null) {
			cerveaux_en_cours=0;
			fusils_en_cours=0;
			//currentPlayer=j4;
			return;
		}
		currentPlayer=j1;
	}
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
