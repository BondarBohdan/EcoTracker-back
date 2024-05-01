package bondar.iot.ecotrackerback.repository;

import bondar.iot.ecotrackerback.model.LightData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LightDataRepository extends JpaRepository<LightData, Long> {
    List<LightData> findAllByDeviceId(String deviceId);
}