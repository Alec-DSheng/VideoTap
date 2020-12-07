package org.nee.ny.video.recording.domain.enums;

import lombok.Getter;

/**
 * @Author: alec
 * Description:
 * @date: 17:07 2020-12-05
 */
@Getter
public enum  StatusEnum {

    ENABLE(1),
    DISABLED(0),
    ;
    private Integer code;
    StatusEnum(int code) {
        this.code = code;
    }
}
