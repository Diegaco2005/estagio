package view;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.MainApp;
import model.Aluno;
import model.AlunoDao;
import model.Empresa;
import model.EmpresaDao;
import model.Estagio;
import model.EstagioDao;

public class ViewEstagioController {
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;
	private Estagio estagio;

        @FXML
        private TableView<Estagio> tableEstagios;
        @FXML
        private TableColumn<Estagio, Aluno> AlunoCol;
        @FXML
        private TableColumn<Estagio, String> EmpresaCol;
        @FXML
        private TableColumn<Estagio, LocalDate> datainicioCol;
        @FXML
        private TableColumn<Estagio, LocalDate> datafinalCol;
       
        
        private void montaTable(){
            //this.tabelaAlunos = new TableView();
            
            AlunoCol = new TableColumn("Aluno");
            AlunoCol.setCellValueFactory(
                new PropertyValueFactory<>("aluno"));
            AlunoCol.setCellValueFactory(
                new PropertyValueFactory<>("nomeAluno")); 
            
            EmpresaCol = new TableColumn("Empresa");
            EmpresaCol.setCellValueFactory(
                new PropertyValueFactory<>("nomeEmpresa"));               
            datainicioCol = new TableColumn("Data inicio");
            datainicioCol.setCellValueFactory(
                new PropertyValueFactory<>("dataInicio"));  
            datafinalCol = new TableColumn("Data Final");
            datafinalCol.setCellValueFactory(
                new PropertyValueFactory<>("dataFinal"));  
            
            
             this.tableEstagios.getColumns().addAll(AlunoCol, EmpresaCol, datainicioCol, datafinalCol);
            
            try {
                EstagioDao estagiodao = new EstagioDao();
                this.tableEstagios.setItems(FXCollections.observableArrayList(estagiodao.getAll()));
               
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
           

        }
        private void atualizaTable(){
             try {
                 EstagioDao estagiodao = new EstagioDao();
                this.tableEstagios.setItems(FXCollections.observableArrayList(estagiodao.getAll()));
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        
        }
        
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	public void setEstagio(Estagio estagio){
		this.estagio = estagio;
	}
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	public boolean isOkClicked(){
		return okClicked;
	}
	@FXML
	private void initialize(){
            montaTable();
        }
	@FXML
	private void handleNovoEstagio() throws SQLException{
            Estagio estagioTmp = new Estagio();
            Boolean okClicked = mainApp.showAddEstagioDialog(estagioTmp);
            if(okClicked){
                    EstagioDao estagiodao = new EstagioDao();
                    estagiodao.adiciona(estagioTmp);
                    atualizaTable();
            }
	}
	@FXML
	private void handleEditaEstagio(){

	}
	@FXML
	private void handleDeletaEstagio(){

	}

}
