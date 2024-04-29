package bondar.iot.ecotrackerback.service;

import bondar.iot.ecotrackerback.model.LightData;
import bondar.iot.ecotrackerback.repository.LightDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightDataService {
    private final LightDataRepository lightDataRepository;

    public LightDataService(LightDataRepository lightDataRepository) {
        this.lightDataRepository = lightDataRepository;
    }

    public void saveLightData(List<LightData> lightDataList) {
        lightDataRepository.saveAll(lightDataList);
    }

    public List<LightData> getAllLightData() {
        return lightDataRepository.findAll();
    }
}