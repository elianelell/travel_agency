package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

@Service
public record UserService(PredictionEngineClient predictionEngineClient) {

    

    public ArrayList<Travel> getCountries(String weatherExpectation, int minimumTemperatureDistance, String userCountry) throws IOException {
        Stream<String> countries = readFileCountries();
        ArrayList<Travel> Travels = new ArrayList<>();
        for (Iterator<String> it = countries.iterator(); it.hasNext(); ) {
            String country = it.next();
            Call<Prediction> predictionCall = predictionEngineClient.getPrediction(country);
            try {
                Response<Prediction> response = predictionCall.execute();
                Travels.add(temperatureTester(response.body(), weatherExpectation, minimumTemperatureDistance, userCountry));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Travels.removeAll(Collections.singleton(null));
        return Travels;
    }

   
}
