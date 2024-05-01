package bondar.iot.ecotrackerback.request;

import bondar.iot.ecotrackerback.model.AudioData;
import lombok.Data;

import java.util.List;

@Data
public class AudioRequest {
    private List<AudioData> data;
}