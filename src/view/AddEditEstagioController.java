package view;

import Util.DateUtil;
import Util.MaskTextField;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Aluno;
import model.AlunoDao;
import model.Empresa;
import model.EmpresaDao;
import model.Estagio;

public class AddEditEstagioController {
    private Stage dialogStage;
    private boolean okClicked = false;
    private Estagio estagio;
    
    @FXML
    private TableView<Aluno> tabelaAlunos;
    @FXML
    private TableColumn<Aluno, String> nomeCol;
    @FXML
    private TableColumn<Aluno, String> cursoCol;
    @FXML
    private TableColumn<Aluno, String> sexoCol;
    @FXML
    private TableColumn<Aluno, String> emailCol;
    
    
    @FXML
    private TableView<Empresa> tabelaEmpresas;
    @FXML
    private TableColumn<Empresa, String> nomeEmpCol;
    @FXML
    private TableColumn<Empresa, String> cidadeCol;
    @FXML
    private TableColumn<Empresa, String> paisCol;
    
    @FXML
    private MaskTextField datainicioTx;
    @FXML
    private MaskTextField datafinalTx;
    @FXML
    private Label labelTitle;
    private boolean editBoolean = false;

    public AddEditEstagioController() {

    }
    private void montaTable(){
        //this.tabelaAlunos = new TableView();

        nomeCol = new TableColumn("Nome");
        nomeCol.setCellValueFactory(
            new PropertyValueFactory<>("nome")); 
        cursoCol = new TableColumn("Curso");
        cursoCol.setCellValueFactory( 
            new PropertyValueFactory<>("curso")); 
        sexoCol = new TableColumn("Sexo");
        sexoCol.setCellValueFactory(
            new PropertyValueFactory<>("sexo"));               
        emailCol = new TableColumn("email");
        emailCol.setCellValueFactory(
            new PropertyValueFactory<>("email"));          


        this.tabelaAlunos.getColumns().addAll(nomeCol, cursoCol, sexoCol, emailCol);

        try {
            AlunoDao alunodao = new AlunoDao();
            this.tabelaAlunos.setItems(FXCollections.observableArrayList(alunodao.getAll()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }



         nomeEmpCol = new TableColumn("Nome");
         nomeEmpCol.setCellValueFactory(
             new PropertyValueFactory<>("nome"));          
         cidadeCol = new TableColumn("Cidade");
         cidadeCol.setCellValueFactory(
             new PropertyValueFactory<>("cidade"));               
         paisCol = new TableColumn("Pais");
         paisCol.setCellValueFactory(
             new PropertyValueFactory<>("pais"));          


          this.tabelaEmpresas.getColumns().addAll(nomeEmpCol, cidadeCol, paisCol);

         try {
             EmpresaDao empresadao = new EmpresaDao();
             this.tabelaEmpresas.setItems(FXCollections.observableArrayList(empresadao.getAll()));

         } catch (SQLException ex) {
             ex.printStackTrace();
         }


    }
    public void preecheForm(){
        this.labelTitle.setText("Edita Estágio");
        
        ObservableList<Empresa> empresas = FXCollections.observableArrayList();
        empresas.addAll(tabelaEmpresas.getItems());
                    
        for(Empresa empresa : empresas){            
            if(empresa.getId() != this.estagio.getEmpresa().getId()){
                tabelaEmpresas.getItems().remove(empresa);
            }
        }
        ObservableList<Aluno> alunos = FXCollections.observableArrayList();
        alunos.addAll(tabelaAlunos.getItems());
                    
        for(Aluno aluno : alunos){          
            if(aluno.getId() != this.estagio.getAluno().getId()){
                tabelaAlunos.getItems().remove(aluno);
            }
        }
        datainicioTx.setText(DateUtil.format(estagio.getDataInicio()));
        if(estagio.getDataFinal() != null && !estagio.getDataFinal().equals(estagio.getDataInicio())){
            datafinalTx.setText(DateUtil.format(estagio.getDataFinal()));
        }
        else datafinalTx.setText("");
            
        
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
    public void setEditBoolean(Boolean edit){
        this.editBoolean = edit;
    }
    @FXML
    public void initialize() {
        datainicioTx.setMask("NN/NN/NNNN");
        datafinalTx.setMask("NN/NN/NNNN");
        montaTable();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if(!editBoolean){
                estagio.setAluno(tabelaAlunos.getSelectionModel().getSelectedItem());
                estagio.setEmpresa(tabelaEmpresas.getSelectionModel().getSelectedItem());
            }
            
            
            estagio.setDataInicio(DateUtil.parseBr(datainicioTx.getText()));
            if(!datafinalTx.getText().equals("")){
                estagio.setDataFinal(DateUtil.parseBr(datafinalTx.getText()));
            }          
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        if(datainicioTx.getText().trim().equals("")){
            errorMessage += "Data de inicio é obrigatorio!\n";
        }else if(!datafinalTx.getText().trim().equals("")){
            if(DateUtil.parse(datainicioTx.getText()).compareTo(DateUtil.parse(datafinalTx.getText())) == 1){
                errorMessage += "Data final não pode ser menor que a data inicial!\n";       
            }      
        }
        
        if(!editBoolean){  
            Aluno alunoSelected = this.tabelaAlunos.getSelectionModel().getSelectedItem();
            if(alunoSelected == null){
               errorMessage += "Selecione um aluno na tabela de alunos!\n"; 
            }
            Empresa empresaSelected = this.tabelaEmpresas.getSelectionModel().getSelectedItem();
            if(empresaSelected == null){
                errorMessage += "Selecione uma empresa na tabela de empresas!\n"; 
            }
        }
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