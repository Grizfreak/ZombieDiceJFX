package zombieDiceGame;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedDice c = new RedDice();
		GreenDice g = new GreenDice();
		YellowDice j = new YellowDice();
		c.throwDice();
		Player Axel = new Player("Moi");
		Player Moch = new Player("Lui");
		Game t = new Game(Difficulty.NORMAL.toString(),Axel, Moch);
		Scanner sc = new Scanner(System.in);
		
		while (Axel.getCerveaux()<=13 || Moch.getCerveaux() <=13) {
			int resul=sc.nextInt();
			if(resul == 1) {
				t.jeterLesDes();
			}
			if (resul == 0){
				t.finirTour();
			}
		}
	}

}
