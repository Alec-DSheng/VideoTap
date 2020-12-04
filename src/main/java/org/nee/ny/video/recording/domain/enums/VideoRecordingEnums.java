package org.nee.ny.video.recording.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: alec
 * Description:
 * @date: 13:44 2020-11-18
 */
public class VideoRecordingEnums {

    @Getter
    @AllArgsConstructor
    public enum RecordStatus {

        RUNNING(0),
        FINISH(1);

        private Integer code;
    }

    @Getter
    @AllArgsConstructor
    public enum RecordSourceStatus {

        Automatic("Automatic"),
        Manual("Manual");

        private String code;
    }
}
