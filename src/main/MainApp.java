package main;



import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;

    public MainApp() throws SQLException {
        // Add some sample data

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        //this.primaryStage.setTitle("Estagios");
        initRootLayout();
    }
    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            Parent root = FXMLLoader.load(getClass().getResource("/view/RootLayout.fxml"));
           

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  

    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
