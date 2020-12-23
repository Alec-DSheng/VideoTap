package org.nee.ny.video.recording.service;

import com.github.pagehelper.PageInfo;
import org.nee.ny.video.recording.domain.Device;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 09:40 2020-12-02
 */
public interface DeviceService {

    //查询设备列表
    Mono<PageInfo<Device>> searchPage(Integer pageNo, Integer pageSize);

    void registerDevice(Device device);

    void deviceLineStatus(String deviceId, Integer status);

}
