package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import zombieDiceGame.*;
import zombieDiceGame.Dice.symbole;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController implements Initializable{
	@FXML private TextField salut;
	@FXML private TextField current;
	@FXML private Label currentText;
	@FXML private TextField currentbrains;
	@FXML private TextField first;
	@FXML private Label firstlabel;
	@FXML private Label secondlabel;
	@FXML private Label thirdlabel;
	@FXML private TextField second;
	@FXML private TextField third;
	@FXML private ImageView first_pump;
	@FXML private ImageView second_pump;
	@FXML private ImageView third_pump;
	@FXML private Canvas canvas;
	private GraphicsContext gc;
	private String difficulty;
	private int nbPlayers;
	private String namej1;
	private String namej2;
	private String namej3;
	private String namej4;
	private Player j1;
	private Player j2;
	private Player j3;
	private Player j4;
	private Game game;
	public static boolean isDead=false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Init");
		difficulty=MenuController.difficulty;
		nbPlayers=MenuController.nbPlayers;
		namej1=MenuController.namej1;
		namej2=MenuController.namej2;
		namej3=MenuController.namej3;
		namej4=MenuController.namej4;
		if(!namej1.isEmpty()) j1 = new Player(namej1);
		if(!namej2.isEmpty()) j2 = new Player(namej2);
		if(!namej3.isEmpty()) j3 = new Player(namej3);
		if(!namej4.isEmpty()) j4 = new Player(namej4);
		if(j4 ==null) {
			game = new Game(difficulty, j1, j2, j3);
		}
		else if (j3 == null) {
			game = new Game(difficulty, j1, j2);
		}
		else {
			game = new Game(difficulty, j1, j2, j3, j4);
		}
		currentText.setText(game.getCurrentPlayer().toString());
		firstlabel.setText(j2.toString());
		if(nbPlayers>=3) {
			second.setVisible(true);
			secondlabel.setVisible(true);
			secondlabel.setText(j3.toString());
			second.setText(String.valueOf(j3.getCerveaux()));
		}
		if(nbPlayers>=4) {
			third.setVisible(true);
			thirdlabel.setVisible(true);
			thirdlabel.setText(j4.toString());
			third.setText(String.valueOf(j4.getCerveaux()));
		}
		gc=canvas.getGraphicsContext2D();
		System.out.println("Passé");
		current.setText(String.valueOf(j1.getCerveaux()));
		first.setText(String.valueOf(j2.getCerveaux()));
		//first_pump.setImage(new Image(("file:src/ZombieDicePic/shotgun.png")));
		//second_pump.setImage(new Image("file:./src/ZombieDicePic/shotgun.png"));
	}

	@FXML public void playturn() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		game.jeterLesDes();
		if(isDead) {
			passerTour();
			return;
		}
		Object[] dejete=game.getLaunchedDices();
		Dice d1 = (Dice) dejete[0];
		Dice d2 = (Dice) dejete[1];
		Dice d3 = (Dice) dejete[2];
		String f1 = (String) dejete[3];
		String f2 = (String) dejete[4];
		String f3 = (String) dejete[5];
		String path=setDe(d1,f1);
		String path2=setDe(d2,f2);
		String path3=setDe(d3,f3);
		gc.drawImage(new Image(path),0,0, 100, 100);
		gc.drawImage(new Image(path2), 150, 0, 100, 100);
		gc.drawImage(new Image(path3), 300, 0, 100, 100);
		setCerveaux();
		setFusil();


	}
	@FXML private void passerTour() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		//TODO REFAIRE FONCTION
	}

	private void setCerveaux() {
		currentbrains.setText(String.valueOf(game.getCerveaux_en_cours()));
	}

	private void exchange(Player p1, Player p2) {
		game.setCurrentPlayer(p2);
		currentText.setText(p2.toString());
		if(currentText.getText().equals(firstlabel.getText())) {
			firstlabel.setText(p1.toString());
		}
		else if (currentText.getText().equals(secondlabel.getText())) {
			secondlabel.setText(p1.toString());
		}
		else {
			thirdlabel.setText(p1.toString());
		}

	}

	private void setFusil() {
		first_pump.setImage(null);
		second_pump.setImage(null);
		third_pump.setImage(null);
		System.out.println(game.getFusils_en_cours());
		if(game.getFusils_en_cours()>=1) {
			first_pump.setImage(new Image("file:./src/ZombieDicePic/shotgun.png"));
		}
		if(game.getFusils_en_cours()>=2) {
			second_pump.setImage(new Image("file:./src/ZombieDicePic/shotgun.png"));
		}
		if(game.getFusils_en_cours()==3) {
			third_pump.setImage(new Image("file:./src/ZombieDicePic/shotgun.png"));
		}
	}
	public String setDe(Dice d, String f) {
		String path;
		if(d instanceof GreenDice) {
			if(f.equals(symbole.CERVEAU.toString())) {
				path = "file:./src/ZombieDicePic/cerveauG.png";
			}
			else if(f.equals(symbole.EMPREINTE.toString())) {
				path = "file:./src/ZombieDicePic/empreinteG.png";
			}
			else {
				path = "file:./src/ZombieDicePic/shotgunG.png";
			}
		}
		else if(d instanceof RedDice) {
			if(f.equals(symbole.CERVEAU.toString())) {
				path = "file:./src/ZombieDicePic/cerveauR.png";
			}
			else if(f.equals(symbole.EMPREINTE.toString())) {
				path = "file:./src/ZombieDicePic/empreinteR.png";
			}
			else {
				path = "file:./src/ZombieDicePic/shotgunR.png";
			}
		}
		else {
			if(f.equals(symbole.CERVEAU.toString())) {
				path = "file:./src/ZombieDicePic/cerveauJ.png";
			}
			else if(f.equals(symbole.EMPREINTE.toString())) {
				path = "file:./src/ZombieDicePic/empreinteJ.png";
			}
			else {
				path = "file:./src/ZombieDicePic/shotgunJ.png";
			}
		}
		return path;

	}

}
