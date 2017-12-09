package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Empresa;

public class AddEditEmpresaController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField enderecoField;
    @FXML
    private ComboBox cidadeBox;
    @FXML
    private ComboBox paisBox;
    @FXML
    private ImageView imgView;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Empresa empresa;
    private boolean imgAlterada = false;
    private Path from;
    private Path to;

    public AddEditEmpresaController() {

    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;

    }
    
    public void preecheForm(){
        nomeField.setText(this.empresa.getNome());
        enderecoField.setText(this.empresa.getEndereco());
        cidadeBox.setValue(this.empresa.getCidade().toString());
        paisBox.setValue(this.empresa.getPais().toString());
        
        
       
        File selectedFile = null;
        if(this.empresa.getLogo() != null){
            selectedFile = new File("upload/"+this.empresa.getLogo());
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
        ObservableList<String> cidade = FXCollections.observableArrayList(
                "Farroupilha", "João Pessoa");
        cidadeBox.getItems().addAll(cidade);
        cidadeBox.setValue("Farroupilha");
        ObservableList<String> pais = FXCollections.observableArrayList(
                "Brasil", "Mexico", "Outro");
        paisBox.getItems().addAll(pais);
        paisBox.setValue("Brasil");
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            empresa.setNome(nomeField.getText());
            empresa.setCidade(cidadeBox.getValue().toString());
            empresa.setEndereco(enderecoField.getText());
            empresa.setPais(paisBox.getValue().toString());

            if (imgAlterada) {
                try {
                    String nomeImg = copiaRenomeiaImg();
                    if (nomeImg != null) {
                        empresa.setLogo(nomeImg);
                    } else {
                        empresa.setLogo("defaultEmpresa.jpg");
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
    private void handleCancel() {
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

    private String geraNomeImg() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter) + ".jpg";
    }

    private String copiaRenomeiaImg() throws IOException {
        if (from != null) {
            String nomeArquivo = geraNomeImg();
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            File copyFile = to.toFile();
            Files.move(to, to.resolveSibling(nomeArquivo));
            return nomeArquivo;

        }
        return null;
    }
}
