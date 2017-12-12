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
 

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;



public class AddEditAlunoController {
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
        private boolean imgAlterada = false;
	private Aluno aluno;
        private Path from;
        private Path to;
        
        
	public AddEditAlunoController(){
            
	}
        
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;

	}
        
        public void preecheForm(){
            nomeField.setText(this.aluno.getNome());
            cpfField.setText(this.aluno.getCpf());
            sexoBox.setValue(this.aluno.getSexo());
            cursoBox.setValue(this.aluno.getCurso());
            emailField.setText(this.aluno.getEmail());
            System.out.println("upload/"+this.aluno.getImagem());
            File selectedFile = null;
            if(this.aluno.getImagem() != null){
                selectedFile = new File("upload/"+this.aluno.getImagem());
            }
            if (selectedFile != null) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(selectedFile);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.imgView.setImage(image);
                }catch (IOException ex) {
                    ex.printStackTrace();

                }
            }
                    
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
				"Male", "Female");
		sexoBox.getItems().addAll(sexo);
                sexoBox.setValue("Male");
		ObservableList<String> curso = FXCollections.observableArrayList(
				"Analise e Desenvolvimento de Sistemas", "Direito", "Medicina");
		cursoBox.getItems().addAll(curso);
                cursoBox.setValue("Analise e Desenvolvimento de Sistemas");
	}

	@FXML
	private void handleOk() {
            if (isInputValid()) {
               
                aluno.setNome(nomeField.getText());
                aluno.setCpf(cpfField.getText());
                aluno.setEmail(emailField.getText());
                
                aluno.setSexo(sexoBox.getValue().toString()); //sexoBox.getValue().toString());
                aluno.setCurso(cursoBox.getValue().toString()); //cursoBox.getValue().toString());
                if(imgAlterada){
                    try {
                        String nomeImg = copiaRenomeiaImg();
                        if(nomeImg != null){
                             aluno.setImagem(nomeImg);
                        }else{
                            aluno.setImagem("defaultUser.jpg");
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }           

                }
                
                okClicked = true;
                dialogStage.close();
            }
	}
        @FXML
        private void handleCancel(){
        
            dialogStage.close();
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
                imgAlterada = true;    
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
            if(from != null){
                String nomeArquivo = geraNomeImg();
                Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                File copyFile = to.toFile();            
                Files.move(to, to.resolveSibling(nomeArquivo));
                return nomeArquivo;
            
            }
            return null;       
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
