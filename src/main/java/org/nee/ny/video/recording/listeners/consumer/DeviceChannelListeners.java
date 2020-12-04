package org.nee.ny.video.recording.listeners.consumer;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.domain.EventEnvelope;
import org.nee.ny.video.recording.service.DeviceChannelService;
import org.nee.ny.video.recording.utils.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.nee.ny.video.recording.domain.Constants.TOPIC_DEVICE_CHANNEL_REGISTER;

/**
 * @Author: alec
 * Description:
 * @date: 17:23 2020-12-03
 */
@Component
@Slf4j
public class DeviceChannelListeners {

    @Autowired
    private DeviceChannelService deviceChannelService;
    /**
     * 监听设备注册
     * */
    @KafkaListener(topics = TOPIC_DEVICE_CHANNEL_REGISTER)
    public void channelReport(EventEnvelope eventEnvelope) {
        Map<String, Object> payloadMap = eventEnvelope.getPayload();
        log.info("map {}", payloadMap);
        DeviceChannel deviceChannel = new DeviceChannel();
        deviceChannelService.reportChannel(BeanToMapUtil.mapToBean(payloadMap, deviceChannel));
    }
}
