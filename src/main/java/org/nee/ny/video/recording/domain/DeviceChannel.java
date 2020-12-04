package org.nee.ny.video.recording.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @Author: alec
 * Description:
 * @date: 13:18 2020-11-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DeviceChannel {

    private String deviceId;

    private String manufacturer;

    private String code;

    private String no;

    private String name;

    private String model;

    private String owner;

    private String civilCode;

    private String address;

    private Integer registerWay;

    private Integer status;

    private Integer secret;

    private Integer viewers;

    private String  faceIcon;

    private Integer cloudRecording;
}
