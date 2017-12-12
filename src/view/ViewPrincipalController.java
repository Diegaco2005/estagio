package view;

import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.MainApp;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import model.AlunoDao;

public class ViewPrincipalController {
    


    // Reference to the main application.
    private MainApp mainApp;

    @FXML
    public void initialize() {
      
    }
    public ViewPrincipalController(){}
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void handleAlunoMenuItem(){
        boolean okClicked = mainApp.showViewAlunoDialog();
    }

    @FXML
    private void handleEmpresaMenuItem(){
        boolean okClicked = mainApp.showViewEmpresaDialog();
    }

    @FXML
    private void handleEstagioMenuItem(){
            boolean okClicked = mainApp.showViewEstagioDialog();
    }
    @FXML
    private void handleGraficoMenuItem(){
            mainApp.showViewGraficoDialog();  
        
    }
    
}
