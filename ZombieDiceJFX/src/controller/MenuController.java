package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import zombieDiceGame.Difficulty;

public class MenuController implements Initializable{
	@FXML RadioButton facile;
	@FXML RadioButton normal;
	@FXML RadioButton difficile;
	@FXML Button launch;
	@FXML Slider nbJoueurs;
	@FXML Label j1;
	@FXML Label j2;
	@FXML Label j3;
	@FXML Label j4;
	@FXML TextField j1name;
	@FXML TextField j2name;
	@FXML TextField j3name;
	@FXML TextField j4name;
	static public String difficulty;
	static int nbPlayers;
	static String namej1;
	static String namej2;
	static String namej3;
	static String namej4;
	static Parent saved;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		namej1=null;
		namej2=null;
		namej3=null;
		namej4=null;
	}


	@FXML public void setFacile(){
		facile.setSelected(true);
		normal.setSelected(false);
		difficile.setSelected(false);
	}
	@FXML public void setNormal(){
		facile.setSelected(false);
		normal.setSelected(true);
		difficile.setSelected(false);
	}
	@FXML public void setDifficile(){
		facile.setSelected(false);
		normal.setSelected(false);
		difficile.setSelected(true);
	}

	@FXML public void displayPlayerNames() {
		int i=(int)nbJoueurs.getValue();
		switch(i) {
		case 3 :
			initTextField();
			j1.setVisible(true);
			j1name.setVisible(true);
			j2.setVisible(true);
			j2name.setVisible(true);
			j3.setVisible(true);
			j3name.setVisible(true);
			break;
		case 4 :
			initTextField();
			j1.setVisible(true);
			j1name.setVisible(true);
			j2.setVisible(true);
			j2name.setVisible(true);
			j3.setVisible(true);
			j3name.setVisible(true);
			j4.setVisible(true);
			j4name.setVisible(true);
			break;
		default :
			initTextField();
			j1.setVisible(true);
			j1name.setVisible(true);
			j2.setVisible(true);
			j2name.setVisible(true);
			break;
		}
	}
	private void initTextField() {
		j1.setVisible(false);
		j1name.setVisible(false);
		j2.setVisible(false);
		j2name.setVisible(false);
		j3.setVisible(false);
		j3name.setVisible(false);
		j4.setVisible(false);
		j4name.setVisible(false);
	}

	@FXML private void launch() throws IOException {
		if(facile.isSelected())difficulty=Difficulty.EASY.toString();
		if(normal.isSelected())difficulty=Difficulty.NORMAL.toString();
		if(difficile.isSelected())difficulty=Difficulty.HARD.toString();
		nbPlayers=(int)nbJoueurs.getValue();
		namej1=j1name.getText();
		namej2=j2name.getText();
		namej3=j3name.getText();
		namej4=j4name.getText();
		Parent root = FXMLLoader.load(getClass().getResource("/view/GamePage.fxml"));
		Stage thisStage = (Stage) Main.actualRoot.getScene().getWindow();
		Main.actualRoot=root;
		Scene next = new Scene(root);
		thisStage.setScene(next);
	}
	@FXML private void gotoHelp() throws IOException {
		saved = FXMLLoader.load(getClass().getResource("/view/Rules.fxml"));
		Scene save = new Scene(saved);
		Stage help = new Stage();
		help.initModality(Modality.APPLICATION_MODAL);
		help.setScene(save);
		help.show();
		
	}
}
