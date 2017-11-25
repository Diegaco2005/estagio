package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.MainApp;
import model.Aluno;

public class ViewAlunoController {
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;
	private Aluno aluno;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	public void setAluno(Aluno aluno){
		this.aluno = aluno;
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
	private void handleNovoAluno(){
		Aluno aluno = new Aluno();
		Boolean okClicked = mainApp.showAddEditAlunoDialog(aluno);
		if(okClicked){
			//lalal
		}
	}
	@FXML
	private void handleEditaAluno(){

	}
	@FXML
	private void handleDeletaAluno(){

	}

}