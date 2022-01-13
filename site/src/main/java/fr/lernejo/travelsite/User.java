package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull(message = "userEmail field is required.")
    private final String userEmail;

    @NotNull(message = "userName field is required.")
    private final String userName;

    @NotNull(message = "userCountry field is required.")
    private final String userCountry;

    @NotNull(message = "weatherExpectation field is required.")
    private final WeatherExpectation weatherExpectation;

    @Range(min = 0, max = 40, message = "Value range : 0 to 40.")
    @NotNull(message = "minimumTemperatureDistance field is required.")
    private final int minimumTemperatureDistance;

    public User(@JsonProperty("userEmail") String userEmail,
                @JsonProperty("userName") String userName,
                @JsonProperty("userCountry") String userCountry,
                @JsonProperty("weatherExpectation") WeatherExpectation weatherExpectation,
                @JsonProperty("minimumTemperatureDistance") int minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public WeatherExpectation getWeatherExpectation() {
        return weatherExpectation;
    }

    public int getMinimumTemperatureDistance() {
        return minimumTemperatureDistance;
    }

    enum WeatherExpectation {
        WARMER, COLDER
    }
}
