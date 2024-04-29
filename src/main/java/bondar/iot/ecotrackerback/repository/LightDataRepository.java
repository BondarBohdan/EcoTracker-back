package bondar.iot.ecotrackerback.repository;

import bondar.iot.ecotrackerback.model.LightData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightDataRepository extends JpaRepository<LightData, Long> {
}