package bondar.iot.ecotrackerback.controller;

import bondar.iot.ecotrackerback.model.LightData;
import bondar.iot.ecotrackerback.request.LightRequest;
import bondar.iot.ecotrackerback.service.LightDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/light")
public class LightDataController {
    private final LightDataService lightDataService;

    public LightDataController(LightDataService lightDataService) {
        this.lightDataService = lightDataService;
    }

    @PostMapping
    public ResponseEntity<?> saveLightData(@RequestBody LightRequest lightRequest) {
        try {
            lightDataService.saveLightData(lightRequest.getData());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing light data");
        }
    }
}