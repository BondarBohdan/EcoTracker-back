package bondar.iot.ecotrackerback.request;

import lombok.Data;

@Data
public class AudioData {
    private String deviceId;
    private double averageAudioAmplitude;
    private String timestamp;
}