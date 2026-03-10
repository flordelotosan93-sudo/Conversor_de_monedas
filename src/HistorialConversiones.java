
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.List;

public class HistorialConversiones {
    public void guardarHistorial(List<TasaCambio> registro) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (FileWriter escritor = new FileWriter("Historial.json")) {
            escritor.write(gson.toJson(registro));
        } catch (Exception e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public void mostrarHistorial(List<TasaCambio> registro) {
        for (TasaCambio tasa : registro) {
            System.out.printf("De %s a %s: Tasa de cambio = %.4f, Resultado = %.2f%n",
                    tasa.getBase_code(), tasa.getTarget_code(),
                    tasa.getConversion_rate(), tasa.getConversion_result());
        }
    }
}