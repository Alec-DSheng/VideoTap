package org.nee.ny.video.recording.domain.webhook;

import lombok.*;

/**
 * @Author: alec
 * Description:
 * @date: 08:33 2020-11-24
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class StreamNoneReaderRequest {

    private String  app;

    private String schema;

    private String stream;

    private String vhost;

    private String mediaServerId;
}
