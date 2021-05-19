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
	private int nbPlayers;
	private Player currentPlayer;

	public Game(int nbPlayers, String difficulty,Player j1,Player j2, Player j3, Player j4) {
		tas = new Gobelet(difficulty);
		this.difficulty=difficulty;
		this.nbPlayers=nbPlayers;
		this.j1=j1;
		this.j2=j2;
		this.j3=j3;
		this.j4=j4;
		currentPlayer=j1;
	}


	public void jeterLesDes(Player j) {
		Random rando = new Random();
		Dice d1 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
		Dice d2 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
		Dice d3 = tas.getDice(rando.nextInt(Gobelet.NB_DICE));
		String face1 = d1.throwDice();
		String face2 = d2.throwDice();
		String face3 = d3.throwDice();
		if(face1.equals(symbole.CERVEAU.toString())) {
			cerveaux_en_cours++;
		}
	}
	
	public void finirTour() {
		//TODO gérer multijoueurs/gérer fusils/gérer fin inopinée
	}
}
