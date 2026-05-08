package com.farmer.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.farmer.application.model.CropResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class CropService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    // ✅ Constructor Injection
    public CropService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // ✅ Constants (clean + scalable)
    private static final double HIGH_TEMP = 30;
    private static final double HIGH_HUMIDITY = 60;
    private static final double LOW_TEMP = 25;

    public CropResponse getCropSuggestion(String location) {

        CropResponse response = new CropResponse();

        try {
            // ✅ Input validation
            if (location == null || location.trim().isEmpty()) {
                response.setError("Location cannot be empty");
                return response;
            }

            // ✅ Encode location (handles spaces like "Bangalore city")
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);

            String url = "https://api.openweathermap.org/data/2.5/weather?q="
                    + encodedLocation + "&appid=" + apiKey + "&units=metric";

            // ✅ API Call
            Map<String, Object> weatherData =
                    restTemplate.getForObject(url, Map.class);

            // ✅ Debug log (remove later if needed)
            System.out.println("Weather API response: " + weatherData);

            // ❗ Validate response
            if (weatherData == null || weatherData.get("main") == null) {
                response.setError("Invalid location or API issue ❗");
                return response;
            }

            // 🌡 Extract main weather data
            Map<String, Object> main = (Map<String, Object>) weatherData.get("main");

            Double temp = ((Number) main.get("temp")).doubleValue();
            Double humidity = ((Number) main.get("humidity")).doubleValue();

            // ☁️ Extract weather condition safely
            List<?> weatherList = (List<?>) weatherData.get("weather");

            if (weatherList == null || weatherList.isEmpty()) {
                response.setError("Weather data not available");
                return response;
            }

            Map<?, ?> weather = (Map<?, ?>) weatherList.get(0);
            String condition = weather.get("main").toString();

            // 🌾 Smart crop logic
            if (temp > HIGH_TEMP && humidity > HIGH_HUMIDITY) {
                response.setCrops(List.of("Rice", "Sugarcane"));
                response.setSeason("Kharif");
                response.setWater("High");
                response.setAdvice("Hot & humid climate is ideal for water-intensive crops");
                response.setConfidence("High");

            } else if (temp < LOW_TEMP) {
                response.setCrops(List.of("Wheat", "Barley"));
                response.setSeason("Rabi");
                response.setWater("Low");
                response.setAdvice("Cool temperature supports Rabi crops");
                response.setConfidence("Medium");

            } else {
                response.setCrops(List.of("Maize", "Millets"));
                response.setSeason("General");
                response.setWater("Moderate");
                response.setAdvice("Moderate weather allows flexible crop selection");
                response.setConfidence("Medium");
            }

            // 📊 Set weather info
            response.setTemperature(temp + " °C");
            response.setHumidity(humidity + " %");
            response.setCondition(condition);

        } catch (Exception e) {
            e.printStackTrace(); // for debugging
            response.setError("Weather service unavailable. Please try again.");
        }

        return response;
    }
}