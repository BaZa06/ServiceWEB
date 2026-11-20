package ni.edu.uam.servicioweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ni.edu.uam.servicioweb.model.Customer;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {

    private static final String API_URL = "https://sqpvqkkdfgtzdgrjrgxs.supabase.co/rest/v1/customers";

    // Obtener la API Key desde una variable de entorno
    private static final String API_KEY =
            System.getenv("API_KEY");

    public static List<Customer> getCustomers() throws Exception, InterruptedException{




        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException(
                    "No se ha configurado la API Key de Supabase"
            );
        }


        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            throw new IOException("Error al obtener el cliente");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode datos = mapper.readTree(response.body());

        if(!datos.isArray()){
            throw new IOException("La respuesta no contiene un formato JSON");
        }

        List<Customer> customers = new ArrayList<>();
        for(JsonNode nodo : datos) {
            Customer customer = new Customer(
                    nodo.path("id").asInt(),
                    nodo.path("first_name").asText(),
                    nodo.path("last_name").asText(),
                    nodo.path("email").asText(),
                    nodo.path("phone").asText(),
                    convertirDate(nodo.path("date_of_birth").asText()),
                    nodo.path("country").asText(),
                    nodo.path("city").asText(),
                    nodo.path("address").asText(),
                    nodo.path("annual_income").asDouble(),
                    nodo.path("credit_score").asInt(),
                    nodo.path("has_unpaid_debt").asBoolean(),
                    nodo.path("unpaid_debt_amount").asDouble(),
                    nodo.path("status").asText(),
                    convertirLocalDate(nodo.path("created_at").asText()),
                    convertirLocalDate(nodo.path("updated_at").asText())

            );
            customers.add(customer);
        }
        return customers;
    }


    private static LocalDate convertirLocalDate(String fecha){

        if (fecha == null || fecha.isBlank()){
            return null;
        }
        return LocalDate.parse(fecha.substring(0,10));
    }

    private static Date convertirDate(String fecha){

        LocalDate fechaLocal = convertirLocalDate(fecha);
        return fechaLocal == null ? null : java.sql.Date.valueOf(fechaLocal);
    }
}
