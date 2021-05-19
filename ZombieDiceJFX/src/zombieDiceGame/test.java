package zombieDiceGame;

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
		t.jeterLesDes();
		t.finirTour();
	}

}
