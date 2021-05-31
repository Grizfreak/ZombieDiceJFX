package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class rulesController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	@FXML public void exit() {
		Stage stage = (Stage) Main.actualRoot.getScene().getWindow();
		
		stage.close();
	}

}
