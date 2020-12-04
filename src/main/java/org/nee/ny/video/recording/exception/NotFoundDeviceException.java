package org.nee.ny.video.recording.exception;

import lombok.Getter;
import org.nee.ny.video.recording.common.ResponseCodeCommon;

/**
 * @Author: alec
 * Description:
 * @date: 13:30 2020-11-18
 */
public class NotFoundDeviceException extends NullPointerException {

    @Getter
    private Integer code;

    @Getter
    private String msg;

    public NotFoundDeviceException(String message) {
        super("未找到有效数据" + message);
        this.code = ResponseCodeCommon.RESPONSE_NULL;
        this.msg = message;
    }
}
