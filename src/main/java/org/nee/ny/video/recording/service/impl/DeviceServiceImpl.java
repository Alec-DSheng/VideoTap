package org.nee.ny.video.recording.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.moudle.DeviceMapper;
import org.nee.ny.video.recording.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
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

    public DeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Override
    public Mono<PageInfo<Device>> searchPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Device> deviceList = deviceMapper.searchList();
        return Mono.just(new PageInfo<>(deviceList));
    }

    @Override
    public void registerDevice(Device device) {
        Optional<Device> optionalDevice = deviceMapper.searchDeviceByCode(device.getCode());
        if (optionalDevice.isPresent()) {
            deviceMapper.updateDynamic(device);
            return;
        }
        deviceMapper.insert(device);
    }
}
