package bondar.iot.ecotrackerback.request;

import lombok.Data;

import java.util.List;

@Data
public class LightRequest {
    List<LightData> data;
}