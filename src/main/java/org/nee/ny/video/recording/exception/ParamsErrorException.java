package org.nee.ny.video.recording.exception;

import lombok.Getter;
import org.nee.ny.video.recording.common.ResponseCodeCommon;

/**
 * @Author: alec
 * Description:
 * @date: 13:45 2020-11-18
 */
public class ParamsErrorException extends RuntimeException {
    @Getter
    private Integer code;

    @Getter
    private String msg;
    public ParamsErrorException(String message) {
        super("参数不合法" + message);
        this.code = ResponseCodeCommon.PARAMS_ERROR;
        this.msg = message;
    }

}
