package view;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.MainApp;
import model.Estagio;
import model.EstagioDao;

public class ViewEstagioController {
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;
	private Estagio estagio;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	public void setAluno(Estagio estagio){
		this.estagio = estagio;
	}
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	public boolean isOkClicked(){
		return okClicked;
	}
	@FXML
	private void initialize(){}
	@FXML
	private void handleNovoEstagio() throws SQLException{
		Estagio estagioTmp = new Estagio();
		Boolean okClicked = mainApp.showAddEditEstagioDialog(estagioTmp);
		if(okClicked){
			EstagioDao estagiodao = new EstagioDao();
			estagiodao.adiciona(estagio);
		}
	}
	@FXML
	private void handleEditaEstagio(){

	}
	@FXML
	private void handleDeletaEstagio(){

	}

}
