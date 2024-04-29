package bondar.iot.ecotrackerback.controller;

import bondar.iot.ecotrackerback.request.PushRequest;
import bondar.iot.ecotrackerback.request.SubscriptionRequest;
import bondar.iot.ecotrackerback.service.PushNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionRequest request) {
        pushNotificationService.subscribe(request);
        return ResponseEntity.ok("Підписка успішно зареєстрована");
    }

    @PostMapping("/send-push")
    public void push(@RequestBody PushRequest pushRequest) {
        pushNotificationService.sendPushMessage(pushRequest);
    }
}