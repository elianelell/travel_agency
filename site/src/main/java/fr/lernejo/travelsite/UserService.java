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

    public Stream<String> readFileCountries() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        Stream<String> countries = content.lines();
        return countries;
    }

    public ArrayList<Destination> getCountries(String weatherExpectation, int minimumTemperatureDistance, String userCountry) throws IOException {
        Stream<String> countries = readFileCountries();
        ArrayList<Destination> destinations = new ArrayList<>();
        for (Iterator<String> it = countries.iterator(); it.hasNext(); ) {
            String country = it.next();
            Call<Prediction> predictionCall = predictionEngineClient.getPrediction(country);
            try {
                Response<Prediction> response = predictionCall.execute();
                destinations.add(temperatureTester(response.body(), weatherExpectation, minimumTemperatureDistance, userCountry));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        destinations.removeAll(Collections.singleton(null));
        return destinations;
    }

    public Destination temperatureTester(Prediction prediction, String weatherExpectation, int minimumTemperatureDistance, String userCountry) {
        double meanTemperature = (prediction.getTemperatures().get(0).getTemperature() + prediction.getTemperatures().get(1).getTemperature()) / 2;
        if (!userCountry.equals(prediction.getCountry()))
            if (weatherExpectation.equals("WARMER") && minimumTemperatureDistance < meanTemperature) {
                return new Destination(prediction.getCountry(), meanTemperature);
            } else if (weatherExpectation.equals("COLDER") && minimumTemperatureDistance > meanTemperature) {
                return new Destination(prediction.getCountry(), meanTemperature);
            }
        return null;
    }
}
