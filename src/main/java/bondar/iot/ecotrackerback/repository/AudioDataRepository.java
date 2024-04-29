package bondar.iot.ecotrackerback.repository;

import bondar.iot.ecotrackerback.model.AudioData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioDataRepository extends JpaRepository<AudioData, Long> {
}