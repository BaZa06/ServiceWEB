package ni.edu.uam.servicioweb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class UsuarioApplication extends Application {

    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
       FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(UsuarioApplication.class.getResource("usuarios-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Consulta de usuarios - API REST");
        stage.setScene(scene);
        stage.show();
    }


}
