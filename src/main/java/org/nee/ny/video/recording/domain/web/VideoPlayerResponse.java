package org.nee.ny.video.recording.domain.web;

import lombok.*;

/**
 * @Author: alec
 * Description:
 * @date: 17:01 2020-12-05
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VideoPlayerResponse {

    private String streamCode;

    private String flvUrl;

    private String hlsUrl;
}
