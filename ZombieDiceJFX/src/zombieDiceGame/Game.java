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
		System.out.println(currentPlayer.toString() + " est en train de jouer");
		/*if(tas.size()+des_empreintes.size()<3) {
			System.out.println("Impossible de jouer CAUSE : plus de dés");
			plusdedes=true;
			finirTour();
			return;
		}*/
		switch(des_empreintes.size()) {
		case 1:
			d1=des_empreintes.get(0);
			des_empreintes.remove(d1);
			d2 = tas.getDice(rando.nextInt(tas.size()));
			tas.removeDice(d2);
			d3 = tas.getDice(rando.nextInt(tas.size()));
			tas.addDice(d2);
			break;
		case 2:
			d1=des_empreintes.get(0);
			d2=des_empreintes.get(1);
			des_empreintes.remove(d1);
			des_empreintes.remove(d2);
			d3 = tas.getDice(rando.nextInt(tas.size()));
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
			d1 = tas.getDice(rando.nextInt(tas.size()));
			tas.removeDice(d1);
			d2 = tas.getDice(rando.nextInt(tas.size()));
			tas.removeDice(d2);
			d3 = tas.getDice(rando.nextInt(tas.size()));
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
		if(tas.size()+des_empreintes.size()<3) {
			System.out.println("Impossible de jouer CAUSE : plus de dés");
			GameController.plusdedes=true;
			finirTour();
			return;
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
		GameController.autoFinishedTurn=true;
		tas.reinitialize();
		System.out.println("FIN DU TOUR DU "+currentPlayer);
		if(fusils_en_cours>=3) {
			fusils_en_cours=0;
			cerveaux_en_cours=0;
		}
		GameController.nb_cerveaux_add=cerveaux_en_cours;
		currentPlayer.addCerveaux(cerveaux_en_cours);
		showStats();
		cerveaux_en_cours=0;
		fusils_en_cours=0;
		if(currentPlayer.equals(j1) && j2!=null) {
			currentPlayer=j2;
			return;
		}
		if(currentPlayer.equals(j2) && j3!=null) {
			currentPlayer=j3;
			return;
		}
		if(currentPlayer.equals(j3) && j4!=null) {
			currentPlayer=j4;
			return;
		}
		currentPlayer=j1;
	}

	public void showStats() {
		System.out.println("-------------------------------Résumé en fin de tour------------------------------------");
		System.out.println(j1.toString() + " : " + j1.getCerveaux() + " cerveaux");
		System.out.println(j2.toString() + " : " + j2.getCerveaux() + " cerveaux");
		if(j3!=null)System.out.println(j3.toString() + " : " + j3.getCerveaux() + " cerveaux");
		if(j4!=null)System.out.println(j4.toString() + " : " + j4.getCerveaux() + " cerveaux");
	}
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public int getGreenDice() {
		int nbGreenDice=0;
		for(int i=0;i<tas.size();i++) {
			if(tas.getDice(i) instanceof GreenDice)nbGreenDice++;
		}
		return nbGreenDice;
	}
	public int getYellowDice() {
		int nbYellowDice=0;
		for(int i=0;i<tas.size();i++) {
			if(tas.getDice(i) instanceof YellowDice)nbYellowDice++;
		}
		return nbYellowDice;
	}
	public int getRedDice() {
		int nbRedDice=0;
		for(int i=0;i<tas.size();i++) {
			if(tas.getDice(i) instanceof RedDice)nbRedDice++;
		}
		return nbRedDice;
	}
}
