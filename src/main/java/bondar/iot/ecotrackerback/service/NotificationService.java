package bondar.iot.ecotrackerback.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class NotificationService {
    private static final String AUDIO_ALERT = "{\"message\":\"Audio Level Is Too High\"}";
    private static final String LIGHT_ALERT = "{\"message\":\"Light Level Is Too High\"}";
    private static final Set<String> AUDIO_ALERTS_SET = new HashSet<>();
    private static final Set<String> LIGHT_ALERTS_SET = new HashSet<>();

    public void saveAudioAlert(String deviceId) {
        AUDIO_ALERTS_SET.add(deviceId);
    }

    public void saveLightAlert(String deviceId) {
        LIGHT_ALERTS_SET.add(deviceId);
    }

    public String checkForAlert(String deviceId) {
        if (AUDIO_ALERTS_SET.contains(deviceId)) {
            AUDIO_ALERTS_SET.remove(deviceId);
            return AUDIO_ALERT;
        }

        if (LIGHT_ALERTS_SET.contains(deviceId)) {
            LIGHT_ALERTS_SET.remove(deviceId);
            return LIGHT_ALERT;
        }

        return null;
    }

}
