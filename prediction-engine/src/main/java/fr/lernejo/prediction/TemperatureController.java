package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public record TemperatureController(TemperatureService temperatureService) {

    @GetMapping("/api/temperature")
    @ResponseBody
    public Prediction getPrediction(@RequestParam String country) {
        try {
            ArrayList<Prediction.Temperature> predictedTemperatures =
                new ArrayList<Prediction.Temperature>(Arrays.asList(
                    new Prediction.Temperature(LocalDate.now().minusDays(1).toString(),
                temperatureService.getTemperature(country)),
                    new Prediction.Temperature(LocalDate.now().minusDays(2).toString(),
                temperatureService.getTemperature(country))));
            return new Prediction(country, predictedTemperatures);
        } catch (UnknownCountryException exception) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Unknown country : " + country);
        }
    }
}
