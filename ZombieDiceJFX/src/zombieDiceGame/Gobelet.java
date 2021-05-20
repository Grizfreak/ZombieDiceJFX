package zombieDiceGame;

import java.util.ArrayList;

public class Gobelet {
	public static int nb_dice=0;
	public static final int FINAL_NB_DICE=13;
	private ArrayList<Dice> dices;
	private ArrayList<Dice> unusable_dices;
	//TODO add difficulty
	public Gobelet(String difficult) {
		int i;
		unusable_dices = new ArrayList<Dice>();
		dices = new ArrayList<Dice>();
		if(difficult.equals(Difficulty.EASY.toString())) {
			for(i=0;i<8;i++) {
				dices.add(new GreenDice());
				nb_dice++;
			}
			for(i=0;i<3;i++) {
				dices.add(new YellowDice());
				nb_dice++;
			}
			for(i=0;i<2;i++) {
				dices.add(new RedDice());
				nb_dice++;
			}
			return;
		}
		if(difficult.equals(Difficulty.NORMAL.toString())) {
			for(i=0;i<6;i++) {
				dices.add(new GreenDice());
				nb_dice++;
			}
			for(i=0;i<4;i++) {
				dices.add(new YellowDice());
				nb_dice++;
			}
			for(i=0;i<3;i++) {
				dices.add(new RedDice());
				nb_dice++;
			}
			return;
			
		}
		if(difficult.equals(Difficulty.HARD.toString())) {
			for(i=0;i<4;i++) {
				dices.add(new GreenDice());
				nb_dice++;
			}
			for(i=0;i<5;i++) {
				dices.add(new YellowDice());
				nb_dice++;
			}
			for(i=0;i<4;i++) {
				dices.add(new RedDice());
				nb_dice++;
			}
			return;
		}
	}

	public void afficheGobelet() {
		for(Dice dice : dices) {
			System.out.println(dice);
		}
	}
	public int size() {
		return nb_dice;
	}
	public void addDice(Dice dice) {
		dices.add(dice);
		nb_dice++;
	}
	public Dice getDice(int i) {
		return dices.get(i);
		
	}
	public void removeDice(Dice dice) {
		unusable_dices.add(dice);
		dices.remove(dice);
		nb_dice--;
	}
	public void reinitialize() {
		for(Dice dice : unusable_dices) {
			dices.add(dice);
		}
		unusable_dices.removeAll(dices);
	}
	
}
