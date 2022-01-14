package fr.lernejo.prediction;

import org.springframework.lang.NonNull;

import java.util.ArrayList;

public record Prediction(@NonNull String country,
                         ArrayList<Temperature> temperatures) {



    public record Temperature(String date,double temperature){


    }
}
