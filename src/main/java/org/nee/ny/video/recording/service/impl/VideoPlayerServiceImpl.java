package org.nee.ny.video.recording.service.impl;

import org.nee.ny.video.recording.configuration.ZlMediaApiProperties;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.domain.enums.StatusEnum;
import org.nee.ny.video.recording.domain.web.VideoPlayerResponse;
import org.nee.ny.video.recording.exception.NotFoundDeviceException;
import org.nee.ny.video.recording.exception.ParamsErrorException;
import org.nee.ny.video.recording.moudle.DeviceChannelMapper;
import org.nee.ny.video.recording.moudle.DeviceMapper;
import org.nee.ny.video.recording.service.VideoPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

/**
 * @Author: alec
 * Description:
 * @date: 17:04 2020-12-05
 */
@Service
public class VideoPlayerServiceImpl implements VideoPlayerService {

    private final DeviceMapper deviceMapper;

    private final DeviceChannelMapper deviceChannelMapper;

    private final RestTemplate restTemplate;

    @Autowired
    private ZlMediaApiProperties zlMediaApiProperties;

    public VideoPlayerServiceImpl(DeviceMapper deviceMapper, DeviceChannelMapper deviceChannelMapper,
                                  RestTemplate restTemplate) {
        this.deviceMapper = deviceMapper;
        this.deviceChannelMapper = deviceChannelMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public VideoPlayerResponse startPlayer(String deviceId, String channelId) {

       DeviceChannel deviceChannel = getDeviceChannel(deviceId, channelId);
       URI uri = fromHttpUrl(zlMediaApiProperties.getSipUrl()).path("/video/start").pathSegment(deviceChannel.getDeviceId(),
               deviceChannel.getCode()).build().toUri();
       return restTemplate.getForObject(uri, VideoPlayerResponse.class);
    }

    @Override
    public VideoPlayerResponse stopPlayer(String deviceId, String channelId) {
        DeviceChannel deviceChannel = getDeviceChannel(deviceId, channelId);
        URI uri = fromHttpUrl(zlMediaApiProperties.getSipUrl()).path("/video/stop").pathSegment(deviceChannel.getDeviceId(),
                deviceChannel.getCode()).build().toUri();
        return restTemplate.getForObject(uri, VideoPlayerResponse.class);
    }

    private DeviceChannel getDeviceChannel(String deviceId, String channelId) {
        Device device = deviceMapper.searchDeviceByCode(deviceId).orElseThrow(() ->
                new NotFoundDeviceException("设备不存在"));

        DeviceChannel deviceChannel = deviceChannelMapper.searchDeviceChannelByCode(channelId)
                .orElseThrow(() -> new NotFoundDeviceException("渠道不存在"));

        if (!StatusEnum.ENABLE.getCode().equals(device.getStatus()) ||
                !StatusEnum.ENABLE.getCode().equals(deviceChannel.getStatus()) ) {
            throw new ParamsErrorException("设备不在线");
        }
        return deviceChannel;
    }
}
