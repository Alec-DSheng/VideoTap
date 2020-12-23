package org.nee.ny.video.recording.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.domain.VideoRecording;
import org.nee.ny.video.recording.domain.web.DeviceChannelTreeResponse;
import org.nee.ny.video.recording.moudle.DeviceChannelMapper;
import org.nee.ny.video.recording.moudle.DeviceMapper;
import org.nee.ny.video.recording.moudle.VideoRecordingMapper;
import org.nee.ny.video.recording.service.DeviceChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: alec
 * Description:
 * @date: 12:33 2020-11-20
 */
@Service
@Slf4j
public class DeviceChannelServiceImpl implements DeviceChannelService {

    private final VideoRecordingMapper videoRecordingMapper;

    private final DeviceChannelMapper deviceChannelMapper;

    private final DeviceMapper deviceMapper;

    public DeviceChannelServiceImpl(VideoRecordingMapper videoRecordingMapper, DeviceChannelMapper deviceChannelMapper,
                                    DeviceMapper deviceMapper) {
        this.videoRecordingMapper = videoRecordingMapper;
        this.deviceChannelMapper = deviceChannelMapper;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public Mono searchPage(DeviceChannel deviceChannel, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<DeviceChannel> deviceChannels = deviceChannelMapper.searchList(deviceChannel);
        return Mono.just(new PageInfo<>(deviceChannels));
    }

    @Override
    public void reportChannel(DeviceChannel deviceChannel, Integer channelNum) {
        log.info("channel {} ------- {}", deviceChannel, channelNum);
        Optional<DeviceChannel> channel = deviceChannelMapper.searchDeviceChannelByCode(deviceChannel.getCode());
        if (channel.isPresent()) {
            deviceChannelMapper.updateDynamic(deviceChannel);
            return;
        }
        deviceChannelMapper.insert(deviceChannel);
        if (channelNum != null) {
            deviceMapper.updateChannelNum(channelNum, deviceChannel.getDeviceId());
        }
    }


    @Override
    public Mono<List<DeviceChannelTreeResponse>> treeNodes() {

        List<Device> deviceList = deviceMapper.searchList();
        if (CollectionUtils.isEmpty(deviceList)) {
            return Mono.empty();
        }
        List<String> deviceIds = deviceList.stream().map(Device::getCode).collect(Collectors.toList());
        List<DeviceChannel> deviceChannels = deviceChannelMapper.searchByIdList(deviceIds);
        if (CollectionUtils.isEmpty(deviceChannels)) {
            return Mono.empty();
        }
        //分组
        Map<String, List<DeviceChannel>> channelMap = deviceChannels.stream().collect(Collectors.groupingBy(DeviceChannel::getDeviceId));
        List<DeviceChannelTreeResponse> deviceChannelTreeResponses = deviceList.stream().map(device ->
                new DeviceChannelTreeResponse(device, channelMap.get(device.getCode()))).collect(Collectors.toList());

        return Mono.just(deviceChannelTreeResponses);
    }
}
