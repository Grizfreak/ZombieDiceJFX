package zombieDiceGame;

public class Player {
	private String name;
	private int cerveaux;
	private boolean hasFinished=false;
	

	public Player(String name) {
		this.name=name;
		cerveaux=0;
	}

	public void addCerveaux(int cerveaux) {
		this.cerveaux+=cerveaux;
	}

	public int getCerveaux() {
		return cerveaux;
	}
	public boolean isFinishing() {
		return hasFinished;
	}
	
	public void setHasFinished(boolean hasFinished) {
		this.hasFinished = hasFinished;
	}

	@Override
	public String toString() {
		return name;
	}
}
