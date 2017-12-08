package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Estagio;

public class AddEditEstagioController {
    private Stage dialogStage;
    private boolean okClicked = false;
    private Estagio estagio;

    public AddEditEstagioController() {

    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void initialize() {
        //TODO: combos
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            
            //TODO: consistir valores

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        
        //TODO: validar campos
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os campos inválidos:");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}