package view;

import main.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class RootLayoutController {
	@FXML
	private MenuItem MIempresa;


	 // Reference to the main application.
    private MainApp mainApp;

	@FXML
    private void initialize(){}
	public RootLayoutController(){}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	@FXML
	private void handleAlunoMenuItem(){
		System.out.println("test");
	}
}
