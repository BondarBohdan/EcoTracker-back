package bondar.iot.ecotrackerback.service;

import bondar.iot.ecotrackerback.model.AudioData;
import bondar.iot.ecotrackerback.repository.AudioDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioDataService {
    private final AudioDataRepository audioDataRepository;

    public AudioDataService(AudioDataRepository audioDataRepository) {
        this.audioDataRepository = audioDataRepository;
    }

    public void saveAudioData(List<AudioData> audioDataList) {
        audioDataRepository.saveAll(audioDataList);
    }

    public List<AudioData> getAllAudioData() {
        return audioDataRepository.findAll();
    }

    public List<AudioData> findAudioDataByDeviceId(String deviceId) {
        return audioDataRepository.findAllByDeviceId(deviceId);
    }

    public List<AudioData> getAllDevices() {
        return audioDataRepository.findDistinctDevices();
    }
}
