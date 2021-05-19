package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}


	@FXML public void setFacile(){
		facile.setSelected(true);
		normal.setSelected(false);
		difficile.setSelected(false);
		changeLaunchState();
	}
	@FXML public void setNormal(){
		facile.setSelected(false);
		normal.setSelected(true);
		difficile.setSelected(false);
		changeLaunchState();
	}
	@FXML public void setDifficile(){
		facile.setSelected(false);
		normal.setSelected(false);
		difficile.setSelected(true);
		changeLaunchState();
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
			changeLaunchState();
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
			changeLaunchState();
			break;
		default :
			initTextField();
			j1.setVisible(true);
			j1name.setVisible(true);
			j2.setVisible(true);
			j2name.setVisible(true);
			changeLaunchState();
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

	@FXML private void changeLaunchState() {
		
	}
}
