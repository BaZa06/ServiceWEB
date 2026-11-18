module ni.edu.uam.servicioweb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    exports ni.edu.uam.servicioweb.controller to javafx.fxml;
    opens ni.edu.uam.servicioweb.controller to javafx.fxml;
    opens ni.edu.uam.servicioweb.model to javafx.base;

    opens ni.edu.uam.servicioweb to javafx.fxml;
    exports ni.edu.uam.servicioweb;
}