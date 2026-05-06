package com.farmer.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CropService {

    public Map<String, String> getCropSuggestion(String location) {

        Map<String, String> response = new HashMap<>();
    
        try {
            String apiKey = "93e1a1285f436c9ce5d622c0071eb839";
    
            String url = "https://api.openweathermap.org/data/2.5/weather?q="
                    + location + "&appid=" + apiKey + "&units=metric";
    
            RestTemplate restTemplate = new RestTemplate();
            Map weatherData = restTemplate.getForObject(url, Map.class);
    
            if (weatherData == null || weatherData.get("main") == null) {
                response.put("error", "Invalid location or API issue ❗");
                return response;
            }
    
            Map main = (Map) weatherData.get("main");
            Double temp = Double.valueOf(main.get("temp").toString());
    
            if (temp > 30) {
                response.put("crop", "Ragi");
                response.put("season", "Kharif");
                response.put("water", "Moderate");
            } else {
                response.put("crop", "Wheat");
                response.put("season", "Rabi");
                response.put("water", "Low");
            }
    
            response.put("temperature", temp + " °C");
    
        } catch (Exception e) {
            response.put("error", "Something went wrong: " + e.getMessage());
        }
    
        return response;
    }
}