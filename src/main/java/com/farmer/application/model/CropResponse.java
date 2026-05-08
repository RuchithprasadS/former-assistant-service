package com.farmer.application.model;

import java.util.List;

public class CropResponse {

    private List<String> crops;      // 🌾 Multiple crop suggestions
    private String season;           // 🌦 Season (Rabi/Kharif/etc)
    private String water;            // 💧 Water requirement
    private String temperature;      // 🌡 Temperature
    private String humidity;         // 💨 Humidity
    private String condition;        // ☁️ Weather condition
    private String advice;           // 🧠 AI advice
    private String confidence;       // 📊 Confidence level
    private String error;            // ❗ Error message (if any)

    // ✅ Default constructor (REQUIRED for Spring/JSON)
    public CropResponse() {}

    // ✅ Getters and Setters

    public List<String> getCrops() {
        return crops;
    }

    public void setCrops(List<String> crops) {
        this.crops = crops;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}