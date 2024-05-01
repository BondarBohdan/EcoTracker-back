package bondar.iot.ecotrackerback.controller;

import bondar.iot.ecotrackerback.model.AudioData;
import bondar.iot.ecotrackerback.model.LightData;
import bondar.iot.ecotrackerback.service.AudioDataService;
import bondar.iot.ecotrackerback.service.LightDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DeviceViewController {

    @Autowired
    private AudioDataService audioDataService;
    @Autowired
    private LightDataService lightDataService;

    @GetMapping("/")
    public String listDevices(Model model) {
        List<AudioData> audioDataList = audioDataService.getAllDevices();
        model.addAttribute("audioDataList", audioDataList);
        return "index";
    }

    @GetMapping("/device/{deviceId}")
    public String deviceDetails(@PathVariable String deviceId, Model model) {
        List<AudioData> audioData = audioDataService.findAudioDataByDeviceId(deviceId);
        List<LightData> lightData = lightDataService.findLightDataByDeviceId(deviceId);
        model.addAttribute("audioData", audioData);
        model.addAttribute("lightData", lightData);
        model.addAttribute("deviceId", deviceId);
        return "deviceDetails";
    }
}
