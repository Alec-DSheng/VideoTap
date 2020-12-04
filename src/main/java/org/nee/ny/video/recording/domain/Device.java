package org.nee.ny.video.recording.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @Author: alec
 * Description:
 * @date: 09:30 2020-12-02
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Device {

    private Integer id;

    private String code;

    private String name;

    private String host;

    private Integer port;

    private String transport;

    private Integer channelNum;

    private String model;

    private String firmware;

    private String domain;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
