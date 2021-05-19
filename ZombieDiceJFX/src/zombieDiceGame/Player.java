package zombieDiceGame;

public class Player {
	private String name;
	private int cerveaux;
	private int fusil;
	
	public Player(String name) {
		this.name=name;
		cerveaux=0;
		fusil=0;
	}

	public void addCerveaux(int cerveaux) {
		// TODO Auto-generated method stub
		this.cerveaux+=cerveaux;
	}
}
