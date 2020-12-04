package org.nee.ny.video.recording.service.zkmedia;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Author: alec
 * Description:
 * @date: 08:49 2020-11-24
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ZkMediaCloseStreamResponse implements RecordingResponse {

    private int code;

    private Boolean result;

    @JsonProperty(value = "count_hit")
    private Integer countHit;

    @JsonProperty(value = "count_closed")
    private Integer countClosed;

    @Override
    public Boolean result() {
        return result;
    }

    @Override
    public Integer code() {
        return code;
    }
}
