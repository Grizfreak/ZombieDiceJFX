package zombieDiceGame;

import java.util.ArrayList;
import java.util.Random;

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
	private Player currentPlayer;

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
		Dice d1;
		Dice d2;
		Dice d3;
		String face1;
		String face2;
		String face3;
		Random rando = new Random();
		switch(des_empreintes.size()) {
		case 1:
			d1=des_empreintes.get(0);
			des_empreintes.remove(d1);
			d2 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
			d3 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
			break;
		case 2:
			d1=des_empreintes.get(0);
			d2=des_empreintes.get(1);
			des_empreintes.remove(d1);
			des_empreintes.remove(d2);
			d3 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
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
			d1 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
			d2 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
			d3 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
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
			des_empreintes.add(d3);
		}
	}

	public void finirTour() {
		//TODO gérer multijoueurs
		des_empreintes.clear();
		if(fusils_en_cours==3) {
			cerveaux_en_cours=0;
		}
		currentPlayer.addCerveaux(cerveaux_en_cours);
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
}
