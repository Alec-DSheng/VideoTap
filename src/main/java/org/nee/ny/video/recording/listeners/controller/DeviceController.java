package org.nee.ny.video.recording.listeners.controller;

import org.nee.ny.video.recording.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 09:43 2020-12-02
 */
@RestController
@RequestMapping(value = "device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public Mono list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return  deviceService.searchPage(pageNo, pageSize);
    }
}
