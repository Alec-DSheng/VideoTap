package org.nee.ny.video.recording.listeners.consumer;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.domain.EventEnvelope;
import org.nee.ny.video.recording.service.DeviceChannelService;
import org.nee.ny.video.recording.utils.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

import static org.nee.ny.video.recording.domain.Constants.TOPIC_DEVICE_CHANNEL_REGISTER;

/**
 * @Author: alec
 * Description:
 * @date: 17:23 2020-12-03
 */
@Component
@Slf4j
public class DeviceChannelListeners {

    private final DeviceChannelService deviceChannelService;


    private static final String KEY_DEVICE_NUM = "channelNum";

    @Autowired
    public DeviceChannelListeners(DeviceChannelService deviceChannelService) {
        this.deviceChannelService = deviceChannelService;
    }

    /**
     * 监听设备注册
     * */
    @KafkaListener(topics = TOPIC_DEVICE_CHANNEL_REGISTER)
    public void channelReport(EventEnvelope eventEnvelope) {
        Map<String, Object> payloadMap = eventEnvelope.getPayload();
        log.info("map {}", payloadMap);
        DeviceChannel deviceChannel = new DeviceChannel();
        Object sum = payloadMap.get(KEY_DEVICE_NUM);
        Integer channelNum = null;
        if (Objects.nonNull(sum)) {
            channelNum = Integer.valueOf(sum.toString());
        }
        deviceChannelService.reportChannel(BeanToMapUtil.mapToBean(payloadMap, deviceChannel), channelNum);
    }
}
