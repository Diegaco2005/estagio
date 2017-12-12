package view;

import java.sql.SQLException;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.MainApp;
import model.Aluno;
import model.AlunoDao;
import model.Empresa;
import model.EmpresaDao;

public class ViewEmpresaController {
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;
	private Empresa empresa;
        
        @FXML
        private TableView<Empresa> tabelaEmpresas;
        @FXML
        private TableColumn<Empresa, String> nomeCol;
        @FXML
        private TableColumn<Empresa, String> cidadeCol;
        @FXML
        private TableColumn<Empresa, String> paisCol;

	private void montaTable(){
            //this.tabelaAlunos = new TableView();
            
            nomeCol = new TableColumn("Nome");
            nomeCol.setCellValueFactory(
                new PropertyValueFactory<>("nome"));          
            cidadeCol = new TableColumn("Cidade");
            cidadeCol.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));               
            paisCol = new TableColumn("Pais");
            paisCol.setCellValueFactory(
                new PropertyValueFactory<>("pais"));          
            
            
             this.tabelaEmpresas.getColumns().addAll(nomeCol, cidadeCol, paisCol);
            
            try {
                EmpresaDao empresadao = new EmpresaDao();
                this.tabelaEmpresas.setItems(FXCollections.observableArrayList(empresadao.getAll()));
               
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
           

        }
	@FXML
	private void initialize(){
            montaTable();
        }
	@FXML
	private void handleNovoEmpresa() throws SQLException{
            Empresa empresaTmp = new Empresa();
            Boolean okClicked = mainApp.showAddEmpresaDialog(empresaTmp);
            if(okClicked){
                    EmpresaDao empresadao = new EmpresaDao();
                    empresadao.adiciona(empresaTmp);
                    atualizaTable();
            }
	}
	@FXML
	private void handleEditaEmpresa() throws SQLException {
            Empresa empresaSelected = this.tabelaEmpresas.getSelectionModel().getSelectedItem();
            if (empresaSelected != null) {
                Boolean okClicked = mainApp.showEditEmpresaDialog(empresaSelected);
                if (okClicked) {
                    EmpresaDao empresadao = new EmpresaDao();
                    empresadao.edita(empresaSelected);
                    atualizaTable();
                }
            } else {
                // Mostra a mensagem de erro.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nenhuma empresa selecionado");
                alert.setContentText("Por favor, Selecione uma EMPRESA para editar");
                alert.showAndWait();

            }
        }
	@FXML
	private void handleDeletaEmpresa(){

	}
        
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
        private void atualizaTable(){
             try {
                 EmpresaDao empresadao = new EmpresaDao();
                this.tabelaEmpresas.setItems(FXCollections.observableArrayList(empresadao.getAll()));
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        
        }

}
