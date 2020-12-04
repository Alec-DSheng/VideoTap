package org.nee.ny.video.recording.service;

import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.domain.web.DeviceChannelTreeResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: alec
 * Description:
 * @date: 12:32 2020-11-20
 */
public interface DeviceChannelService {

    Mono searchPage(DeviceChannel deviceChannel, Integer pageNo, Integer pageSize);

    void reportChannel(DeviceChannel deviceChannel);

    Mono<List<DeviceChannelTreeResponse>> treeNodes();
}
