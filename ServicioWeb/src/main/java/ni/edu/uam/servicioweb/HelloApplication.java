package ni.edu.uam.servicioweb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApplication.class.getResource("customers-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Consulta de clientes - API REST");
        stage.setScene(scene);
        stage.show();
    }
}
