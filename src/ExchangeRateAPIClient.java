
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ExchangeRateAPIClient {
    private static final String CLAVE_API;

    static {
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            propiedades.load(fis);
            CLAVE_API = propiedades.getProperty("API_KEY");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TasaCambio resultadoDeConversion(String baseCode, String targetCode, double amount) throws IOException, InterruptedException {
        // Construir la URL correctamente
        String urlString = "https://v6.exchangerate-api.com/v6/"
                + CLAVE_API
                + "/pair/"
                + baseCode
                + "/"
                + targetCode
                + "/"
                + amount;

        URI direccion = URI.create(urlString);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());

        // Mapear a TasaCambio
        return new Gson().fromJson(respuesta.body(), TasaCambio.class);
    }}