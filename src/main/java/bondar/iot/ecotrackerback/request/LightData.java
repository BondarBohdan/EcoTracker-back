package bondar.iot.ecotrackerback.request;

import lombok.Data;

@Data
public class LightData {
    private String deviceId;
    private double lightLevel;
    private String timestamp;
}