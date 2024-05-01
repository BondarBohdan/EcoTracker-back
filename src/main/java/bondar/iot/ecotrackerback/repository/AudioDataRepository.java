package bondar.iot.ecotrackerback.repository;

import bondar.iot.ecotrackerback.model.AudioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AudioDataRepository extends JpaRepository<AudioData, Long> {
    List<AudioData> findAllByDeviceId(String deviceId);

    @Query(value = "SELECT DISTINCT DEVICE_ID FROM AUDIO_DATA", nativeQuery = true)
    List<String> findDistinctDevices();
}