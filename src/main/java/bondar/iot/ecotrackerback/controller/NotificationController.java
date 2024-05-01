package bondar.iot.ecotrackerback.controller;

import bondar.iot.ecotrackerback.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/send-audio-alert/{deviceId}")
    public ResponseEntity<?> sendAudioAlert(@PathVariable String deviceId) {
        notificationService.saveAudioAlert(deviceId);
        return ResponseEntity.ok("Audio alert sent for device " + deviceId);
    }

    @PostMapping("/send-light-alert/{deviceId}")
    public ResponseEntity<?> sendLightAlert(@PathVariable String deviceId) {
        notificationService.saveLightAlert(deviceId);
        return ResponseEntity.ok("Light alert sent for device " + deviceId);
    }

    @GetMapping("/poll/{deviceId}")
    public ResponseEntity<?> poll(@PathVariable String deviceId) {
        String notification = notificationService.checkForAlert(deviceId);
        return Optional.ofNullable(notification)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}
