package org.nee.ny.video.recording.domain.web;

import lombok.*;

import java.util.Date;

/**
 * @Author: alec
 * Description:
 * @date: 09:12 2020-11-19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordingDetailResponse {

    private Integer id;

    private Integer timeLen;

    private String channelId;

    private String channelName;

    private Long startTime;

    private Long endTime;

    private String url;

    private Integer urgentFlag;

}
