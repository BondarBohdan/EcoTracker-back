package bondar.iot.ecotrackerback.request;

import bondar.iot.ecotrackerback.model.LightData;
import lombok.Data;

import java.util.List;

@Data
public class LightRequest {
    List<LightData> data;
}