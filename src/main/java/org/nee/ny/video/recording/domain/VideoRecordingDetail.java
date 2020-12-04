package org.nee.ny.video.recording.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Author: alec
 * Description: 录屏文件详情
 * @date: 18:25 2020-11-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VideoRecordingDetail {

    private Integer id;

    private Integer recordId;

    private String app;

    private String fileName;

    private String filePath;

    private Integer fileSize;

    private String folder;

    private String startTime;

    private String endTime;

    private String stream;

    private Integer timeLen;

    private String url;

    private String vHost;

    private String mediaServerId;

}
