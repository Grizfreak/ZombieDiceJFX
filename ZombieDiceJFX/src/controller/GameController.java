package controller;

import java.net.URL;
import java.util.ResourceBundle;
import zombieDiceGame.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class GameController implements Initializable{
	@FXML private TextField salut;
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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
			Game game = new Game(difficulty, j1, j2, j3);
		}
		else if (j3 == null) {
			Game game = new Game(difficulty, j1, j2);
		}
		else {
			Game game = new Game(difficulty, j1, j2, j3, j4);
		}
	}

}
