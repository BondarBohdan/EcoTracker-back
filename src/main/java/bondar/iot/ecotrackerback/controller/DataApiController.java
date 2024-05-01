package bondar.iot.ecotrackerback.controller;

import bondar.iot.ecotrackerback.model.AudioData;
import bondar.iot.ecotrackerback.model.LightData;
import bondar.iot.ecotrackerback.service.AudioDataService;
import bondar.iot.ecotrackerback.service.LightDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DataApiController {

    @Autowired
    private AudioDataService audioDataService;

    @Autowired
    private LightDataService lightDataService;

    @GetMapping("/device-data/{deviceId}")
    public ResponseEntity<Map<String, Object>> getDeviceData(@PathVariable String deviceId) {
        List<AudioData> audioData = audioDataService.findAudioDataByDeviceId(deviceId);
        List<LightData> lightData = lightDataService.findLightDataByDeviceId(deviceId);
        Map<String, Object> data = new HashMap<>();
        data.put("audioData", audioData);
        data.put("lightData", lightData);
        return ResponseEntity.ok(data);
    }
}