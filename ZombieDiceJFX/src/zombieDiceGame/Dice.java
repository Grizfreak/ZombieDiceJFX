package zombieDiceGame;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
	private final static int NB_FACES=6;
	private ArrayList<String> faces;
	public enum symbole{
		CERVEAU,EMPREINTE,FUSIL;
	}
	
	public Dice(int nbCerveau, int nbEmpreintes, int nbFusil) {
		if(nbCerveau+nbEmpreintes+nbFusil==NB_FACES){
			faces = new ArrayList<>();
			int i=0;
			for(i=0;i<=nbCerveau-1;i++) {
				faces.add(symbole.CERVEAU.toString());
			}
			for(i=0;i<=nbEmpreintes-1;i++) {
				faces.add(symbole.EMPREINTE.toString());
			}
			for(i=0;i<=nbFusil-1;i++) {
				faces.add(symbole.FUSIL.toString());
			}
			
		}
		else System.out.println("Erreur");
		
	}
	
	public void afficheFace() {
		for(String face : faces) {
			System.out.println(face);
		}
	}
	
	public String throwDice() {
		Random rando = new Random();
		return faces.get(rando.nextInt(NB_FACES));
	}
	//TODO shuffle opir on s'en fout
	}
