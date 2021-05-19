package zombieDiceGame;

import java.util.ArrayList;

public class Gobelet {
	public static final int NB_DICE=13;
	private ArrayList<Dice> dices;
	private ArrayList<Dice> unusable_dices;
	//TODO add difficulty
	public Gobelet(String difficult) {
		int i;
		dices = new ArrayList<Dice>();
		if(difficult.equals(Difficulty.EASY.toString())) {
			for(i=0;i<8;i++) {
				dices.add(new GreenDice());
			}
			for(i=0;i<3;i++) {
				dices.add(new YellowDice());
			}
			for(i=0;i<2;i++) {
				dices.add(new RedDice());
			}
			return;
		}
		if(difficult.equals(Difficulty.NORMAL.toString())) {
			for(i=0;i<6;i++) {
				dices.add(new GreenDice());
			}
			for(i=0;i<4;i++) {
				dices.add(new YellowDice());
			}
			for(i=0;i<3;i++) {
				dices.add(new RedDice());
			}
			return;
			
		}
		if(difficult.equals(Difficulty.HARD.toString())) {
			for(i=0;i<4;i++) {
				dices.add(new GreenDice());
			}
			for(i=0;i<5;i++) {
				dices.add(new YellowDice());
			}
			for(i=0;i<4;i++) {
				dices.add(new RedDice());
			}
			return;
		}
	}

	public void afficheGobelet() {
		for(Dice dice : dices) {
			System.out.println(dice);
		}
	}
	public Dice getDice(int i) {
		return dices.get(i);
		
	}
	public void removeDice(Dice dice) {
		unusable_dices.add(dice);
		dices.remove(dice);
	}
	public void reinitialize() {
		for(Dice dice : unusable_dices) {
			dices.add(dice);
		}
		unusable_dices.removeAll(dices);
	}
	
}
