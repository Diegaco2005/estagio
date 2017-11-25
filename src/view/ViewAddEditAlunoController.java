package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Aluno;

public class ViewAddEditAlunoController {
	private Stage dialogStage;
	private boolean okClicked = false;
	private Aluno aluno;

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
	public void initialize(){

	}
}
