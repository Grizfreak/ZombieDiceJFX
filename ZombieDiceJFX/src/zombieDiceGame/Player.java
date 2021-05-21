package zombieDiceGame;

public class Player {
	private String name;
	private int cerveaux;
	
	public Player(String name) {
		this.name=name;
		cerveaux=0;
	}

	public void addCerveaux(int cerveaux) {
		// TODO Auto-generated method stub
		this.cerveaux+=cerveaux;
	}

	public int getCerveaux() {
		return cerveaux;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
