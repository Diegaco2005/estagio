package view;

import Util.DateUtil;
import Util.MaskTextField;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
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

    public AddEditEstagioController() {

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
        datainicioTx.setMask("NN/NN/NNNN");
        datafinalTx.setMask("NN/NN/NNNN");
        montaTable();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            
            estagio.setAluno(tabelaAlunos.getSelectionModel().getSelectedItem());
            estagio.setEmpresa(tabelaEmpresas.getSelectionModel().getSelectedItem());
            
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