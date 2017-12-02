package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ComboBox<String> cursoBox;
	@FXML
	private TextField emailField;

	private Stage dialogStage;
	private boolean okClicked = false;
	private Aluno aluno;
	public ViewAddEditAlunoController(){



	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	public void initialize() {
		ObservableList<String> sexo = FXCollections.observableArrayList(
				"Masculinho", "Feminino");
		sexoBox.getItems().addAll(sexo);
		ObservableList<String> curso = FXCollections.observableArrayList(
				"Analise e desenvolvimento", "Economia");
		cursoBox.getItems().addAll(curso);
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
			errorMessage += "Nome é obrigatoria!\n";
		}
		if (cpfField.getLength() < 11) {
			errorMessage += "CPF inválido!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Mostra a mensagem de erro.
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Inválidos");
			alert.setHeaderText("Por favor, corrija os campos inválidos");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}
}
