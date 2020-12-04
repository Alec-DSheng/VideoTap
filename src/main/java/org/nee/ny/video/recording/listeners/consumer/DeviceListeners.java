package org.nee.ny.video.recording.listeners.consumer;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.EventEnvelope;
import org.nee.ny.video.recording.domain.consumer.DeviceRegisterEvent;
import org.nee.ny.video.recording.service.DeviceService;
import org.nee.ny.video.recording.utils.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.nee.ny.video.recording.domain.Constants.*;

/**
 * @Author: alec
 * Description:
 * @date: 11:37 2020-12-02
 */
@Component
@Slf4j
public class DeviceListeners {

    @Autowired
    private DeviceService deviceService;

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
    public void deviceOnline() {

    }

    /**
     * 监听设备下线
     * */
    @KafkaListener(topics = TOPIC_DEVICE_OFFLINE)
    public void deviceOffline() {

    }
}
