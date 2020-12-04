package org.nee.ny.video.recording.listeners.controller;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.service.DeviceChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 13:20 2020-11-20
 */
@RestController
@RequestMapping(value = "device/channel")
@Slf4j
public class DeviceChannelController {

    private final  DeviceChannelService deviceChannelService;

    public DeviceChannelController(DeviceChannelService deviceChannelService) {
        this.deviceChannelService = deviceChannelService;
    }


    @GetMapping
    public Mono searchList(@RequestParam(value = "deviceId") String deviceId,
                           @RequestParam(value = "channelId", required = false) String channelId,
                           @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        DeviceChannel deviceChannel = DeviceChannel.builder().deviceId(deviceId).code(channelId).build();
        return deviceChannelService.searchPage(deviceChannel, pageNo, pageSize);
    }

    @GetMapping(value = "tree")
    public Mono searchForTree() {
        return deviceChannelService.treeNodes();
    }
}
