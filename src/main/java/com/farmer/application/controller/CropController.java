package com.farmer.application.controller;

import com.farmer.application.service.CropService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping("/crop")
    public Map<String, String> getCrop(@RequestParam String location) {
        return cropService.getCropSuggestion(location);
    }
}