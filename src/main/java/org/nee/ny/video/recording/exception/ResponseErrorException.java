package org.nee.ny.video.recording.exception;

import lombok.Getter;
import org.nee.ny.video.recording.common.ResponseCodeCommon;

/**
 * @Author: alec
 * Description:
 * @date: 14:02 2020-11-18
 */
public class ResponseErrorException extends RuntimeException{
    @Getter
    private Integer code;

    @Getter
    private String msg;
    public ResponseErrorException(Integer code , String message) {
        super("http 请求响应失败, 响应数据 " + code + "响应数据 " + message);
        this.code = ResponseCodeCommon.RESPONSE_ERROR;
        this.msg = message;
    }
}
