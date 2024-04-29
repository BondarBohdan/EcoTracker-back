package bondar.iot.ecotrackerback.service;

import bondar.iot.ecotrackerback.model.Subscription;
import bondar.iot.ecotrackerback.repository.SubscriptionRepository;
import bondar.iot.ecotrackerback.request.PushRequest;
import bondar.iot.ecotrackerback.request.SubscriptionRequest;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Security;

@Service
public class PushNotificationService {
    Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private static final String pushJSON = "{\n" +
            "  \"title\": \"Нове повідомлення\",\n" +
            "  \"body\": \"У вас є нове повідомлення!\",\n" +
            "  \"icon\": \"/icon.png\"\n" +
            "}";
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

            logger.info("Subscribed deviceId: " + request.getDeviceId());
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
                        pushJSON.getBytes()
                );

                pushService.send(notification);
            } catch (Exception e) {
                logger.info("Exception during sending push \n" + e);
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("Subscription not found for device ID: " + pushRequest.getDeviceId());
        }
    }
}