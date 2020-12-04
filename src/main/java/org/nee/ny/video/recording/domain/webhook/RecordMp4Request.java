package org.nee.ny.video.recording.domain.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Author: alec
 * Description:
 * @date: 17:52 2020-11-18
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RecordMp4Request {

    private String app;

    @JsonProperty(value = "file_name")
    private String fileName;

    @JsonProperty(value = "file_path")
    private String filePath;

    @JsonProperty(value = "file_size")
    private Integer fileSize;

    private String folder;

    @JsonProperty(value = "start_time")
    private Long startTime;

    private String stream;

    @JsonProperty(value = "time_len")
    private Integer timeLen;

    private String url;

    @JsonProperty(value = "vhost")
    private String vHost;

    private String mediaServerId;
}
