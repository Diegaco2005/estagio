package view;

import java.awt.image.BufferedImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import model.Aluno;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeRegExp.source;


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
        @FXML
	private ImageView imgView;
        

	private Stage dialogStage;
	private boolean okClicked = false;
	private Aluno aluno;
        private Path from;
        private Path to;
        
        
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
				"Masculino", "Feminino");
		sexoBox.getItems().addAll(sexo);
		ObservableList<String> curso = FXCollections.observableArrayList(
				"Analise e Desenvolvimento de Sistemas", "Economia");
		cursoBox.getItems().addAll(curso);
	}

	@FXML
	private void handleOk() {
            if (isInputValid()) {
                aluno.setNome(nomeField.getText());
                aluno.setCpf(cpfField.getText());
                aluno.setEmail(emailField.getText());
                aluno.setSexo("Masculino"); //sexoBox.getValue().toString());
                aluno.setCurso("Análise e Desenvolvimento de Sistemas"); //cursoBox.getValue().toString());
                try {
                    aluno.setImagem(copiaRenomeiaImg());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }           
                okClicked = true;
                dialogStage.close();
            }
	}
        @FXML
        private void handleImg() {
            
	    FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            
            fc.setTitle("Selecione uma imagem");
            File selectedFile = fc.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                this.imgView.setImage(image);
                
                from = Paths.get(selectedFile.toURI());
                to = Paths.get("upload/" + selectedFile.getName());            
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
  
	}
        private String geraNomeImg(){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            return now.format(formatter)+".jpg";   
        }
        private String copiaRenomeiaImg() throws IOException{
            String nomeArquivo = geraNomeImg();
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            File copyFile = to.toFile();            
            Files.move(to, to.resolveSibling(nomeArquivo));
            return nomeArquivo;
        
        }
	private boolean isInputValid() {
		String errorMessage = "";
		if (nomeField.getText() == null || nomeField.getText().length() == 0) {
			errorMessage += "Nome é obrigatorio!\n";
		}
		if (cpfField.getLength() < 11) {
			errorMessage += "CPF inválido!\n";
		}
                /*if (sexoBox.getValue().toString() != "Masculino" || sexoBox.getValue().toString() != "Feminino"){
                    errorMessage += "Sexo inválido!\n";
                }*/
                /*if(cursoBox.toString().length() < 5){
                    errorMessage += "O curso deve ser selecionado!\n";
                }*/
                if(emailField.getText() == null || emailField.getText().length() < 7){
                    errorMessage += "Digite um email válido!\n";
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
