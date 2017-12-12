/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.MainApp;
import model.Aluno;
import model.AlunoDao;
import model.EstagioDao;
import Util.LabeledPieChart;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author caio
 */
public class viewGraficoController {

    private Stage dialogStage;
    private MainApp mainApp;
    @FXML
    private LabeledPieChart graficoPizza;
    @FXML
    private ComboBox cursoBox;

    private static final int GRAFICO_ALTURA = 300;
    private static final int GRAFICO_LARGURA = 300;

    private ObservableList<String> cursosNome = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        montaComboBox();
        try {
            montaGrafico();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void montaGrafico() throws SQLException {
        graficoPizza.getData().clear();
        ObservableList<PieChart.Data> pieChartData = new EstagioDao().getGrafico(cursoBox.getSelectionModel().getSelectedItem().toString());
        //graficoPizza.setData(pieChartData);
        for(Data data: pieChartData){
            graficoPizza.getData().add(data);
        
        }
                
        graficoPizza.setTitle(cursoBox.getSelectionModel().getSelectedItem().toString()); 
        
    }
    

    

    private void montaComboBox() {

        try {
            ObservableList<String> cursos = new AlunoDao().getCursos();
            cursoBox.getItems().addAll(cursos);
            cursoBox.setValue(cursos.get(0));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void handleComboBox() {
        try {
            montaGrafico();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
