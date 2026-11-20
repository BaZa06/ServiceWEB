package ni.edu.uam.servicioweb.controller;

import ni.edu.uam.servicioweb.model.Customer;
import ni.edu.uam.servicioweb.service.CustomerService;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

public class CustomerController {


    @FXML
    private TableView<Customer> tablaClientes;

    @FXML
    private TableColumn<Customer, Integer> colId;

    @FXML
    private TableColumn<Customer, String> colNombre;

    @FXML
    private TableColumn<Customer, String> colApellido;

    @FXML
    private TableColumn<Customer, String> colCorreo;

    @FXML
    private TableColumn<Customer, String> colTelefono;

    @FXML
    private TableColumn<Customer, String> colPais;

    @FXML
    private TableColumn<Customer, String> colCiudad;

    @FXML
    private TableColumn<Customer, Double> colIngreso;

    @FXML
    private TableColumn<Customer, String> colEstado;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private ComboBox<String> cbPais;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIngresoAnual;

    @FXML
    private TextField txtCreditScore;

    @FXML
    private CheckBox chkTieneDeuda;

    @FXML
    private TextField txtMontoDeuda;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private TextField txtFechaCreacion;

    @FXML
    private TextField txtFechaActualizacion;


    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblMensaje;


    private final CustomerService service = new CustomerService();

    private FilteredList<Customer> customersFiltrados;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("first_name"));

        colApellido.setCellValueFactory(
                new PropertyValueFactory<>("last_name"));

        colCorreo.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        colPais.setCellValueFactory(
                new PropertyValueFactory<>("country"));

        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("city"));

        colIngreso.setCellValueFactory(
                new PropertyValueFactory<>("annual_income"));

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        cbEstado.setItems(
                FXCollections.observableArrayList(
                        "active",
                        "inactive",
                        "blocked"
                )
        );

        cbEstado.setValue("active");

        tablaClientes.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    if (seleccionado != null) {
                        cargarDatosFormulario(seleccionado);
                    }
                });

        chkTieneDeuda.selectedProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    txtMontoDeuda.setDisable(!seleccionado);

                    if (!seleccionado) {
                        txtMontoDeuda.setText("0");
                    }
                });

        txtMontoDeuda.setDisable(true);

        consultarClientes();
    }


    @FXML
    private void consultarClientes() {

        Task<List<Customer>> task = new Task<List<Customer>>() {

            @Override
            protected List<Customer> call() throws Exception {

                return service.getCustomers();
            }
        };

        lblMensaje.setText("Consultando API REST . . .");

        task.setOnSucceeded(event -> {

            List<Customer> customers = task.getValue();

            customersFiltrados = new FilteredList<>(
                    FXCollections.observableArrayList(customers)
            );

            tablaClientes.setItems(customersFiltrados);

            lblTotalClientes.setText(
                    "Clientes encontrados: " + customers.size()
            );

            lblMensaje.setText("Consulta realizada correctamente.");

        });

        // EN CASO DE ERROR

        task.setOnFailed(event -> {

            Throwable error = task.getException();

            lblMensaje.setText(
                    "Error al consultar API REST: " + error.getMessage()
            );

            mostrarError(
                    "Error de consulta",
                    error.getMessage()
            );

        });

        Thread hilo = new Thread(task);

        hilo.setDaemon(true);

        hilo.start();
    }


    @FXML
    private void buscar() {

        if (customersFiltrados == null) {
            return;
        }

        String busqueda = txtBuscar.getText()
                .trim()
                .toLowerCase(Locale.ROOT);

        customersFiltrados.setPredicate(customer -> {

            if (busqueda.isEmpty()) {
                return true;
            }

            return contiene(customer.getFirst_name(), busqueda)
                    || contiene(customer.getLast_name(), busqueda)
                    || contiene(customer.getEmail(), busqueda)
                    || contiene(customer.getCountry(), busqueda)
                    || contiene(customer.getCity(), busqueda);
        });

        lblTotalClientes.setText(
                "Clientes encontrados: " + customersFiltrados.size()
        );
    }

    private boolean contiene(String texto, String busqueda) {

        return texto != null
                && texto.toLowerCase(Locale.ROOT).contains(busqueda);
    }



    private void cargarDatosFormulario(Customer customer) {

        txtId.setText(String.valueOf(customer.getId()));

        txtNombre.setText(customer.getFirst_name());

        txtApellido.setText(customer.getLast_name());

        txtCorreo.setText(customer.getEmail());

        txtTelefono.setText(customer.getPhone());

        if (customer.getDate_of_birth() != null) {

            Date fecha = (Date) customer.getDate_of_birth();

            dpFechaNacimiento.setValue(fecha.toLocalDate());

        } else {

            dpFechaNacimiento.setValue(null);
        }

        cbPais.setValue(customer.getCountry());

        txtCiudad.setText(customer.getCity());

        txtDireccion.setText(customer.getAddress());

        txtIngresoAnual.setText(
                String.valueOf(customer.getAnnual_income())
        );

        txtCreditScore.setText(
                String.valueOf(customer.getCredit_score())
        );

        chkTieneDeuda.setSelected(
                Boolean.TRUE.equals(customer.getHas_unpaid_debt())
        );

        txtMontoDeuda.setText(
                String.valueOf(customer.getUnpaid_debt_amount())
        );

        txtMontoDeuda.setDisable(
                !Boolean.TRUE.equals(customer.getHas_unpaid_debt())
        );

        cbEstado.setValue(customer.getStatus());

        txtFechaCreacion.setText(
                String.valueOf(customer.getCreated_at())
        );

        txtFechaActualizacion.setText(
                String.valueOf(customer.getUpdated_at())
        );
    }


    @FXML
    private void limpiar() {

        tablaClientes.getSelectionModel().clearSelection();

        txtId.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtTelefono.clear();

        dpFechaNacimiento.setValue(null);

        cbPais.setValue(null);

        txtCiudad.clear();
        txtDireccion.clear();
        txtIngresoAnual.clear();
        txtCreditScore.clear();

        chkTieneDeuda.setSelected(false);

        txtMontoDeuda.setText("0");
        txtMontoDeuda.setDisable(true);

        cbEstado.setValue("active");

        txtFechaCreacion.clear();
        txtFechaActualizacion.clear();

        txtBuscar.clear();

        if (customersFiltrados != null) {
            customersFiltrados.setPredicate(customer -> true);
        }

        lblTotalClientes.setText(
                "Clientes encontrados: "
                        + tablaClientes.getItems().size()
        );

        lblMensaje.setText("Formulario limpio.");
    }


    @FXML
    private void guardar() {

        mostrarAdvertencia(
                "Operación pendiente",
                "El servicio actual todavía no tiene implementado "
                        + "el método POST para registrar clientes."
        );
    }


    @FXML
    private void actualizar() {

        if (tablaClientes.getSelectionModel().getSelectedItem() == null) {

            mostrarAdvertencia(
                    "Selecciona un cliente",
                    "Debes seleccionar un cliente de la tabla."
            );

            return;
        }

        mostrarAdvertencia(
                "Operación pendiente",
                "El servicio actual todavía no tiene implementado "
                        + "el método PATCH o PUT para actualizar clientes."
        );
    }



    @FXML
    private void eliminar() {

        Customer seleccionado =
                tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarAdvertencia(
                    "Selecciona un cliente",
                    "Debes seleccionar un cliente de la tabla."
            );

            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmacion.setTitle("Confirmar eliminación");

        confirmacion.setHeaderText("¿Deseas eliminar este cliente?");

        confirmacion.setContentText(
                seleccionado.getFirst_name() + " "
                        + seleccionado.getLast_name()
        );

        confirmacion.showAndWait().ifPresent(respuesta -> {

            if (respuesta == ButtonType.OK) {

                mostrarAdvertencia(
                        "Operación pendiente",
                        "El servicio actual todavía no tiene implementado "
                                + "el método DELETE para eliminar clientes."
                );
            }
        });
    }


    private void mostrarAdvertencia(
            String titulo,
            String mensaje
    ) {

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Advertencia");

        alert.setHeaderText(titulo);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    private void mostrarError(
            String titulo,
            String mensaje
    ) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");

        alert.setHeaderText(titulo);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }

}
