package org.nee.ny.video.recording.domain.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: alec
 * Description:
 * @date: 23:38 2020-12-03
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceChannelTreeResponse {

    private String deviceId;

    private String deviceName;

    private Integer deviceStatus;

    private List<ChannelNode> nodes;

    public DeviceChannelTreeResponse (Device device, List<DeviceChannel> deviceChannels) {
        this.deviceId = device.getCode();
        this.deviceName = device.getName();
        this.deviceStatus = device.getStatus();
        if (CollectionUtils.isEmpty(deviceChannels)) {
            this.nodes = new ArrayList<>();
        } else {
            this.nodes = deviceChannels.stream().map(res ->
                    new ChannelNode(res.getCode(), res.getName(), res.getStatus())).collect(Collectors.toList());
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    static class ChannelNode {

        private String channelId;

        private String channelName;

        private Integer channelStatus;
    }
}
