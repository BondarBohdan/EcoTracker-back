package bondar.iot.ecotrackerback.request;

import lombok.Data;

@Data
public class SubscriptionRequest {
    private String deviceId;
    private String endpoint;
    private String p256dh;
    private String auth;
}