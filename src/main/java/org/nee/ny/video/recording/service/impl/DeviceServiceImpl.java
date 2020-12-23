package org.nee.ny.video.recording.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.enums.StatusEnum;
import org.nee.ny.video.recording.moudle.DeviceChannelMapper;
import org.nee.ny.video.recording.moudle.DeviceMapper;
import org.nee.ny.video.recording.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

/**
 * @Author: alec
 * Description:
 * @date: 09:41 2020-12-02
 */
@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;

    private final DeviceChannelMapper deviceChannelMapper;

    public DeviceServiceImpl(DeviceMapper deviceMapper, DeviceChannelMapper deviceChannelMapper) {
        this.deviceMapper = deviceMapper;
        this.deviceChannelMapper = deviceChannelMapper;
    }

    @Override
    public Mono<PageInfo<Device>> searchPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Device> deviceList = deviceMapper.searchList();
        return Mono.just(new PageInfo<>(deviceList));
    }

    @Override
    public void registerDevice(Device device) {
        device.setStatus(StatusEnum.ENABLE.getCode());
        Optional<Device> optionalDevice = deviceMapper.searchDeviceByCode(device.getCode());
        if (optionalDevice.isPresent()) {
            deviceMapper.updateDynamic(device);
            return;
        }
        deviceMapper.insert(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deviceLineStatus(String deviceId, Integer status) {
        deviceMapper.searchDeviceByCode(deviceId).ifPresent(device -> {
            deviceMapper.updateStatus(status, device.getId());
            if (status.equals(StatusEnum.DISABLED.getCode())) {
                //批量下线渠道
                deviceChannelMapper.updateStatusByDevice(status, deviceId);
            }
        });
    }
}
