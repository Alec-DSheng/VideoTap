package org.nee.ny.video.recording.listeners.consumer;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.EventEnvelope;
import org.nee.ny.video.recording.domain.enums.StatusEnum;
import org.nee.ny.video.recording.service.DeviceService;
import org.nee.ny.video.recording.utils.BeanToMapUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.nee.ny.video.recording.domain.Constants.*;

/**
 * @Author: alec
 * Description:
 * @date: 11:37 2020-12-02
 */
@Component
@Slf4j
public class DeviceListeners {

    private static final String KEY_DEVICE_LINE = "deviceId";

    private final DeviceService deviceService;

    public DeviceListeners(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * 监听设备注册
     * */
    @KafkaListener(topics = TOPIC_DEVICE_REGISTER)
    public void deviceRegister(EventEnvelope eventEnvelope) {
      Map<String, Object> payloadMap = eventEnvelope.getPayload();
      Device device = new Device();
      deviceService.registerDevice(BeanToMapUtil.mapToBean(payloadMap, device));
    }
    /**
     * 监听设备上线
     * */
    @KafkaListener(topics = TOPIC_DEVICE_ONLINE)
    public void deviceOnline(EventEnvelope eventEnvelope) {
        log.info("设备上线 {}", StatusEnum.ENABLE.getCode());
        dealDeviceOnlineOrOffLine(eventEnvelope.getPayload(), StatusEnum.ENABLE.getCode());
    }
    /**
     * 监听设备下线
     * */
    @KafkaListener(topics = TOPIC_DEVICE_OFFLINE)
    public void deviceOffline(EventEnvelope eventEnvelope) {
        log.info("设备下线 {}", StatusEnum.DISABLED.getCode());
        dealDeviceOnlineOrOffLine(eventEnvelope.getPayload(), StatusEnum.DISABLED.getCode());
    }

    private void dealDeviceOnlineOrOffLine(Map<String, Object> payloadMap, Integer status) {
        Optional.ofNullable(payloadMap).ifPresent(payload -> {
            String deviceId = Objects.requireNonNull(payloadMap.get(KEY_DEVICE_LINE)).toString();
            deviceService.deviceLineStatus(deviceId, status);
        });
    }

}
