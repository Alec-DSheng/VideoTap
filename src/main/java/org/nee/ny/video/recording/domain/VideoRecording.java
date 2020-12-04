package org.nee.ny.video.recording.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * @Author: alec
 * Description:
 * @date: 13:15 2020-11-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VideoRecording {

    private Integer id;

    private String deviceId;

    private String deviceName;

    private String channelId;

    private String source;

    private String channelName;

    private String streamCode;

    private String path;
    //回调状态
    private Integer status;
    //结束状态
    private Integer state;

    private Integer urgentFlag;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer videoType;
}
