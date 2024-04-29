package bondar.iot.ecotrackerback.repository;

import bondar.iot.ecotrackerback.model.AudioData;
import bondar.iot.ecotrackerback.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByDeviceId(String deviceId);

    boolean existsByDeviceId(String deviceId);
}