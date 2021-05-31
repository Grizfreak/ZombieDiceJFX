package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import zombieDiceGame.*;
import zombieDiceGame.Dice.symbole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
	@FXML private Label greenDice;
	@FXML private Label yellowDice;
	@FXML private Label redDice;
	@FXML private Label alerte;
	@FXML private Button pass;
	@FXML private Button play;
	@FXML private Button scores;
	private GraphicsContext gc;
	private String difficulty;
	public static int nbPlayers;
	private String namej1;
	private String namej2;
	private String namej3;
	private String namej4;
	private static Player j1;
	private static Player j2;
	private static Player j3;
	private static Player j4;
	private Game game;
	public static int nb_cerveaux_add=0;
	public static boolean plusdedes=false;
	public static boolean autoFinishedTurn=false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		greenDice.setText(String.valueOf(game.getGreenDice()));
		yellowDice.setText(String.valueOf(game.getYellowDice()));
		redDice.setText(String.valueOf(game.getRedDice()));
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
	}

	@FXML public void playturn() {
		if(nbPlayers==2) {
			if(j1.isFinishing()||j2.isFinishing())game.getCurrentPlayer().setHasFinished(true);
		}
		else if(nbPlayers==3) {
			if(j1.isFinishing()||j2.isFinishing()||j3.isFinishing())game.getCurrentPlayer().setHasFinished(true);
		}
		else {
			if(j1.isFinishing()||j2.isFinishing()||j3.isFinishing()||j4.isFinishing())game.getCurrentPlayer().setHasFinished(true);
		}
		alerte.setVisible(false);
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		game.jeterLesDes();
		if(autoFinishedTurn) {
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
		URL path=setDe(d1,f1);
		URL path2=setDe(d2,f2);
		URL path3=setDe(d3,f3);
		gc.drawImage(new Image(path.toString()),0,0, 100, 100);
		gc.drawImage(new Image(path2.toString()), 150, 0, 100, 100);
		gc.drawImage(new Image(path3.toString()), 300, 0, 100, 100);
		setCerveaux();
		setFusil();
		greenDice.setText(String.valueOf(game.getGreenDice()));
		yellowDice.setText(String.valueOf(game.getYellowDice()));
		redDice.setText(String.valueOf(game.getRedDice()));


	}
	@FXML private void passerTour() {
		if(nbPlayers==2) {
			if(j1.isFinishing()&& j2.isFinishing()) {
				game.getCurrentPlayer().setHasFinished(true);
				finJeu();
				return;
			}

		}
		else if(nbPlayers==3) {
			if(j1.isFinishing()&&j2.isFinishing()&&j3.isFinishing()) {
				game.getCurrentPlayer().setHasFinished(true);
				finJeu();
				return;
			}

		}
		else {
			if(j1.isFinishing()&&j2.isFinishing()&&j3.isFinishing()&&j4.isFinishing()) {
				game.getCurrentPlayer().setHasFinished(true);
				finJeu();
				return;
			}

		}

		alerte.setVisible(false);
		if(autoFinishedTurn)setAlerte();
		else game.finirTour();
		setFusil();
		setCerveaux();
		greenDice.setText(String.valueOf(game.getGreenDice()));
		yellowDice.setText(String.valueOf(game.getYellowDice()));
		redDice.setText(String.valueOf(game.getRedDice()));
		autoFinishedTurn=false;
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if(currentText.getText().trim().equals(j1.toString())) {
			current.setText(String.valueOf(j1.getCerveaux()));
			if(j1.getCerveaux()>=13)j1.setHasFinished(true);
		}
		else if(currentText.getText().trim().equals(j2.toString())) {
			current.setText(String.valueOf(j2.getCerveaux()));
			if(j2.getCerveaux()>=13)j2.setHasFinished(true);
		}
		else if(currentText.getText().trim().equals(j3.toString())) {
			current.setText(String.valueOf(j3.getCerveaux()));
			if(j3.getCerveaux()>=13)j3.setHasFinished(true);
		}
		else if(currentText.getText().trim().equals(j4.toString())) {
			current.setText(String.valueOf(j4.getCerveaux()));
			if(j4.getCerveaux()>=13)j4.setHasFinished(true);
		}
		exchange();
	}

	private void setCerveaux() {
		currentbrains.setText(String.valueOf(game.getCerveaux_en_cours()));
	}

	private void exchange() {
		//current / currentText / first / firstlabel / second / secondlabel / third / thirdlabel
		String currentS=current.getText(),currentTextS=currentText.getText(),firstS=first.getText(),firstlabelS=firstlabel.getText(),secondS=second.getText(),secondlabelS=secondlabel.getText(),thirdS=third.getText(),thirdlabelS=thirdlabel.getText();
		switch(nbPlayers) {
		case 3: 
			first.setText(secondS);
			firstlabel.setText(secondlabelS);
			second.setText(currentS);
			secondlabel.setText(currentTextS);
			current.setText(firstS);
			currentText.setText(firstlabelS);
			break;
		case 4:
			first.setText(secondS);
			firstlabel.setText(secondlabelS);
			second.setText(thirdS);
			secondlabel.setText(thirdlabelS);
			third.setText(currentS);
			thirdlabel.setText(currentTextS);
			current.setText(firstS);
			currentText.setText(firstlabelS);
			break;
		default:
			current.setText(firstS);
			currentText.setText(firstlabelS);
			first.setText(currentS);
			firstlabel.setText(currentTextS);
			break;
		}
	}

	private void setFusil() {
		first_pump.setImage(null);
		second_pump.setImage(null);
		third_pump.setImage(null);
		System.out.println(game.getFusils_en_cours());
		if(game.getFusils_en_cours()>=1) {
			first_pump.setImage(new Image(getClass().getResource("/ZombieDicePic/shotgun.png").toString()));
		}
		if(game.getFusils_en_cours()>=2) {
			second_pump.setImage(new Image(getClass().getResource("/ZombieDicePic/shotgun.png").toString()));
		}
		if(game.getFusils_en_cours()==3) {
			third_pump.setImage(new Image(getClass().getResource("/ZombieDicePic/shotgun.png").toString()));
		}
	}
	public URL setDe(Dice d, String f) {
		URL path;
		if(d instanceof GreenDice) {
			if(f.equals(symbole.CERVEAU.toString())) {
				path = getClass().getResource("/ZombieDicePic/cerveauG.png");
			}
			else if(f.equals(symbole.EMPREINTE.toString())) {
				path = getClass().getResource("/ZombieDicePic/empreinteG.png");
			}
			else {
				path = getClass().getResource("/ZombieDicePic/shotgunG.png");
			}
		}
		else if(d instanceof RedDice) {
			if(f.equals(symbole.CERVEAU.toString())) {
				path = getClass().getResource("/ZombieDicePic/cerveauR.png");
			}
			else if(f.equals(symbole.EMPREINTE.toString())) {
				path = getClass().getResource("/ZombieDicePic/empreinteR.png");
			}
			else {
				path = getClass().getResource("/ZombieDicePic/shotgunR.png");
			}
		}
		else {
			if(f.equals(symbole.CERVEAU.toString())) {
				path = getClass().getResource("/ZombieDicePic/cerveauJ.png");
			}
			else if(f.equals(symbole.EMPREINTE.toString())) {
				path = getClass().getResource("/ZombieDicePic/empreinteJ.png");
			}
			else {
				path = getClass().getResource("/ZombieDicePic/shotgunJ.png");
			}
		}
		return path;

	}
	public void setAlerte() {
		alerte.setVisible(true);
		if(plusdedes) {
			plusdedes=false;
			alerte.setText("Votre tour s'est terminé car : Il n'y a plus de dés jouables");
			return;
		}
		else {
			alerte.setText("Votre tour s'est terminé car : vous avez obtenu 3 fusils ou plus");
			return;
		}
	}

	public void finJeu() {
		play.setDisable(true);
		pass.setDisable(true);
		scores.setVisible(true);
	}
	@FXML public void gotoStats() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ScorePage.fxml"));
		Stage thisStage = (Stage) Main.actualRoot.getScene().getWindow();
		Main.actualRoot=root;
		Scene next = new Scene(root);
		thisStage.setScene(next);
	}

	public static Player getJ1() {
		return j1;
	}

	public static Player getJ2() {
		return j2;
	}

	public static Player getJ3() {
		return j3;
	}

	public static Player getJ4() {
		return j4;
	}

}
