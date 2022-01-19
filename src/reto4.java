
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class reto4 extends Application {
    @Override
    //Esta estructura siempre es la misma; lo unico que se cambia es el nombre del archivo FXML.
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("reto4.fxml"));//instanciando
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("TabPaneController"); //EDITABLE!!! este el nombre del archivo del fxml
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}