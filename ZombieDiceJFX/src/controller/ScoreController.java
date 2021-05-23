package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import zombieDiceGame.Player;

public class ScoreController implements Initializable{
	@FXML private Label first;
	@FXML private Label firstp;
	@FXML private Label second;
	@FXML private Label secondp;
	@FXML private Label third;
	@FXML private Label thirdp;
	@FXML private Label fourth;
	@FXML private Label fourthp;
	@FXML private ImageView firstI;
	@FXML private ImageView secondI;
	@FXML private ImageView thirdI;
	@FXML private ImageView fourthI;
	private Player j1,j2,j3,j4;
	private ArrayList<Player>top;
	private int nbPlayers;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		top=new ArrayList<Player>();
		nbPlayers = GameController.nbPlayers;
		if(nbPlayers>=2) {
			j1=GameController.getJ1();
			top.add(j1);
			j2=GameController.getJ2();
			top.add(j2);
		}
		if(nbPlayers>=3) {
			j3=GameController.getJ3();
			top.add(j3);
			third.setVisible(true);
			thirdI.setVisible(true);
			thirdp.setVisible(true);
		}
		if(nbPlayers==4) {
			j4=GameController.getJ4();
			top.add(j4);
			fourth.setVisible(true);
			fourthI.setVisible(true);
			fourthp.setVisible(true);
		}
		top.sort(Comparator.comparingInt(Player::getCerveaux));
		if(nbPlayers==2) {
			first.setText(top.get(1).toString());
			firstp.setText(String.valueOf(top.get(1).getCerveaux()));
			second.setText(top.get(0).toString());
			secondp.setText(String.valueOf(top.get(0).getCerveaux()));
			return;
		}
		if(nbPlayers==3) {
			first.setText(top.get(2).toString());
			firstp.setText(String.valueOf(top.get(2).getCerveaux()));
			second.setText(top.get(1).toString());
			secondp.setText(String.valueOf(top.get(1).getCerveaux()));
			third.setText(top.get(0).toString());
			thirdp.setText(String.valueOf(top.get(0).getCerveaux()));
			return;
		}
		if(nbPlayers==4) {
			first.setText(top.get(3).toString());
			firstp.setText(String.valueOf(top.get(3).getCerveaux()));
			second.setText(top.get(2).toString());
			secondp.setText(String.valueOf(top.get(2).getCerveaux()));
			third.setText(top.get(1).toString());
			thirdp.setText(String.valueOf(top.get(1).getCerveaux()));
			fourth.setText(top.get(0).toString());
			fourthp.setText(String.valueOf(top.get(0).getCerveaux()));
			return;
		}
		
		
	}
	@FXML private void exit() {
		Platform.exit();
	}
	@FXML private void goToDebut() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPage.fxml"));
		Stage thisStage = (Stage) Main.actualRoot.getScene().getWindow();
		Main.actualRoot=root;
		Scene next = new Scene(root);
		thisStage.setScene(next);
	}

}
