package view;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.MainApp;
import model.Empresa;
import model.EmpresaDao;

public class ViewEmpresaController {
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;
	private Empresa empresa;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	public void setEmpresa(Empresa empresa){
		this.empresa = empresa;
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
	private void handleNovoEmpresa() throws SQLException{
		Empresa empresaTmp = new Empresa();
		Boolean okClicked = mainApp.showAddEditEmpresaDialog(empresaTmp);
		if(okClicked){
			EmpresaDao empresadao = new EmpresaDao();
			empresadao.adiciona(empresa);
		}
	}
	@FXML
	private void handleEditaEmpresa(){

	}
	@FXML
	private void handleDeletaEmpresa(){

	}

}
