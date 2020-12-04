package org.nee.ny.video.recording.service.zkmedia;

import lombok.*;

/**
 * @Author: alec
 * Description:
 * @date: 09:51 2020-11-16
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ZkMediaRecordingResponse implements RecordingResponse {

    private int code;

    private Boolean result;

    @Override
    public Boolean result() {
        return this.result;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    public boolean isSuccess() {
        return code == 0 && result;
    }
}
