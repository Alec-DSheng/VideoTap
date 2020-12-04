package org.nee.ny.video.recording.domain.consumer;

import lombok.*;

/**
 * @Author: alec
 * Description:
 * @date: 14:53 2020-12-02
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceRegisterEvent {

    private String deviceId;

    private String host;

    private Integer port;
}
