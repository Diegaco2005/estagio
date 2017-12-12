package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class ViewAlunoController{
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;        
	private Aluno aluno;
        
        
        
        @FXML
        private TableView<Aluno> tabelaAlunos;
        @FXML
        private TableColumn<Aluno, String> nomeCol;
        @FXML
        private TableColumn<Aluno, String> sexoCol;
        @FXML
        private TableColumn<Aluno, String> emailCol;

        public ViewAlunoController(){
            
        
        }
        private void montaTable(){
            //this.tabelaAlunos = new TableView();
            
            nomeCol = new TableColumn("Nome");
            nomeCol.setCellValueFactory(
                new PropertyValueFactory<>("nome"));          
            sexoCol = new TableColumn("Sexo");
            sexoCol.setCellValueFactory(
                new PropertyValueFactory<>("sexo"));               
            emailCol = new TableColumn("email");
            emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));          
            
            
             this.tabelaAlunos.getColumns().addAll(nomeCol, sexoCol, emailCol);
            
            try {
                AlunoDao alunodao = new AlunoDao();
                this.tabelaAlunos.setItems(FXCollections.observableArrayList(alunodao.getAll()));
               
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
           

        }
        private void atualizaTable(){
             try {
                AlunoDao alunodao = new AlunoDao();
                this.tabelaAlunos.setItems(FXCollections.observableArrayList(alunodao.getAll()));
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        
        }
        
	@FXML
	private void initialize(){
            montaTable();
        }
	@FXML
	private void handleNovoAluno() throws SQLException{
            Aluno alunoTmp = new Aluno();
            Boolean okClicked = mainApp.showAddAlunoDialog(alunoTmp);
            if(okClicked){
                AlunoDao alunodao = new AlunoDao();
                alunodao.adiciona(alunoTmp);
                atualizaTable();
            }
	}
	@FXML
	private void handleEditaAluno() throws SQLException{
           Aluno alunoSelected = this.tabelaAlunos.getSelectionModel().getSelectedItem();
           if(alunoSelected != null){
               Boolean okClicked = mainApp.showEditAlunoDialog(alunoSelected);
                if(okClicked){
                    AlunoDao alunodao = new AlunoDao();
                    alunodao.edita(alunoSelected);
                    atualizaTable();
                }
           }else{
                // Mostra a mensagem de erro.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nenhum aluno selecionado");
                alert.setContentText("Por favor, Selecione um ALUNO para editar");
                alert.showAndWait();
                        
           }
           
	}
	@FXML
	private void handleDeletaAluno(){

	}
        
        /*
        *
        *GET SET
        
        */
        
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

}
