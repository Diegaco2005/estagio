package view;

import main.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class ViewPrincipalController {



    // Reference to the main application.
    private MainApp mainApp;

    @FXML
    private void initialize(){}
	public ViewPrincipalController(){}
	public void setMainApp(MainApp mainApp) {
		System.out.println(mainApp.getClass());
        this.mainApp = mainApp;
    }
    @FXML
    private void handleAlunoMenuItem(){
        boolean okClicked = mainApp.showViewAlunoDialog();
    }

    @FXML
    private void handleEmpresaMenuItem(){
        boolean okClicked = mainApp.showViewEmpresaDialog();
    }

    @FXML
    private void handleEstagioMenuItem(){
            boolean okClicked = mainApp.showViewEstagioDialog();
    }
}
