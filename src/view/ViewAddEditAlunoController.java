package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Aluno;

public class ViewAddEditAlunoController {
	@FXML
	private TextField nomeField;
	@FXML
	private TextField cpfField;
	@FXML
	private ComboBox sexoBox;
	@FXML
	private ComboBox cursoBox;
	@FXML
	private TextField emailField;

	private Stage dialogStage;
	private boolean okClicked = false;
	private Aluno aluno;

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		//aa
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	public void initialize() {
		cursoBox.getItems().addAll("Masculino", "Feminino");
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			aluno.setNome(nomeField.getText());
			aluno.setCpf(cpfField.getText());
			aluno.setEmail(emailField.getText());
			// aluno.setSexo(sexoBox.getValue().toString());
			// aluno.setCurso(cursoBox.getValue().toString());

			okClicked = true;
			dialogStage.close();
		}
	}

	private boolean isInputValid() {
		String errorMessage = "";
		if (nomeField.getText() == null || nomeField.getText().length() == 0) {
			errorMessage += "Nome � obrigatoria!\n";
		}
		if (cpfField.getLength() < 11) {
			errorMessage += "CPF inv�lido!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Mostra a mensagem de erro.
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Inv�lidos");
			alert.setHeaderText("Por favor, corrija os campos inv�lidos");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}
}
