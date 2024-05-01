package bondar.iot.ecotrackerback.controller;

import bondar.iot.ecotrackerback.request.AudioRequest;
import bondar.iot.ecotrackerback.service.AudioDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audio")
public class AudioDataController {
    private final AudioDataService audioDataService;

    public AudioDataController(AudioDataService audioDataService) {
        this.audioDataService = audioDataService;
    }

    @PostMapping
    public ResponseEntity<?> saveAudioData(@RequestBody AudioRequest audioRequest) {
        try {
            audioDataService.saveAudioData(audioRequest.getData());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio data");
        }
    }
}