package bondar.iot.ecotrackerback.repository;

import bondar.iot.ecotrackerback.model.AudioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AudioDataRepository extends JpaRepository<AudioData, Long> {
    List<AudioData> findAllByDeviceId(String deviceId);

    @Query("SELECT ad FROM AudioData ad WHERE ad.id IN (SELECT MIN(ad2.id) FROM AudioData ad2 GROUP BY ad2.deviceId)")
    List<AudioData> findDistinctDevices();
}