package bondar.iot.ecotrackerback.service;

import bondar.iot.ecotrackerback.model.Subscription;
import bondar.iot.ecotrackerback.repository.SubscriptionRepository;
import bondar.iot.ecotrackerback.request.PushRequest;
import bondar.iot.ecotrackerback.request.SubscriptionRequest;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {

    private final SubscriptionRepository subscriptionRepository;
    private final String publicVapidKey;
    private final String privateVapidKey;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Autowired
    public PushNotificationService(SubscriptionRepository subscriptionRepository, @Value("${vapid.public.key}") String publicVapidKey, @Value("${vapid.private.key}") String privateVapidKey) {
        this.subscriptionRepository = subscriptionRepository;
        this.publicVapidKey = publicVapidKey;
        this.privateVapidKey = privateVapidKey;
    }

    // Метод для збереження підписки
    public void subscribe(SubscriptionRequest request) {
        if (!subscriptionRepository.existsByDeviceId(request.getDeviceId())) {
            Subscription subscription = new Subscription();
            subscription.setDeviceId(request.getDeviceId());
            subscription.setEndpoint(request.getEndpoint());
            subscription.setP256dh(request.getP256dh());
            subscription.setAuth(request.getAuth());

            subscriptionRepository.save(subscription); // Зберігаємо підписку в БД
        }
    }

    // Метод для відправлення пуш-повідомлення на конкретний пристрій
    public void sendPushMessage(PushRequest pushRequest) {
        Subscription subscription = subscriptionRepository.findByDeviceId(pushRequest.getDeviceId());

        if (subscription != null) {
            try {
                PushService pushService = new PushService(publicVapidKey, privateVapidKey);

                Notification notification = new Notification(
                        subscription.getEndpoint(),
                        subscription.getP256dh(),
                        subscription.getAuth(),
                        "{\n" +
                                "  \"title\": \"Нове повідомлення\",\n" +
                                "  \"body\": \"У вас є нове повідомлення!\",\n" +
                                "  \"icon\": \"/icon.png\"\n" +
                                "}".getBytes()
                );

                pushService.send(notification);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("Subscription not found for device ID: " + pushRequest.getDeviceId());
        }
    }
}