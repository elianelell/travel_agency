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
            ArrayList<Temperature> predictedTemperatures = new ArrayList<Temperature>(Arrays.asList(new Temperature(LocalDate.now().minusDays(1).toString(),
                (int) temperatureService.getTemperature(country, LocalDate.now().minusDays(1).toString())), new Temperature(LocalDate.now().minusDays(2).toString(),
                (int) temperatureService.getTemperature(country, LocalDate.now().minusDays(2).toString()))));
            return new Prediction(country, predictedTemperatures);
        } catch (UnknownCountryException exception) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Unknown country : " + country);
        }
    }
}
