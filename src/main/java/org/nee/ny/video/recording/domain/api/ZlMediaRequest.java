package org.nee.ny.video.recording.domain.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nee.ny.video.recording.configuration.ZlMediaApiProperties;
import org.nee.ny.video.recording.domain.webhook.StreamNoneReaderRequest;

/**
 * @Author: alec
 * Description:
 * @date: 15:03 2020-11-16
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ZlMediaRequest {

    private String secret;

    private String vhost;

    private Integer type;

    private String app;

    private String stream;

    private String customized_path;

    private String schema;

    private Boolean force;


    public ZlMediaRequest(ZlMediaApiProperties zlMediaApiProperties, String stream, Integer type) {

        this.secret = zlMediaApiProperties.getSecret();
        this.vhost = zlMediaApiProperties.getVHost();
        this.type = type;
        this.app = zlMediaApiProperties.getApp();
        this.stream = stream;
        this.customized_path = zlMediaApiProperties.getCustomizedPath();
    }

    public ZlMediaRequest(ZlMediaApiProperties zlMediaApiProperties, String stream) {

        this.secret = zlMediaApiProperties.getSecret();
        this.vhost = zlMediaApiProperties.getVHost();
        this.app = zlMediaApiProperties.getApp();
        this.stream = stream;
    }

    public ZlMediaRequest(ZlMediaApiProperties zlMediaApiProperties, StreamNoneReaderRequest streamNoneReaderRequest) {

        this.secret = zlMediaApiProperties.getSecret();
        this.vhost = streamNoneReaderRequest.getVhost();
        this.app = streamNoneReaderRequest.getApp();
        this.stream = streamNoneReaderRequest.getStream();
        this.force = false;
        this.schema = streamNoneReaderRequest.getSchema();
    }
}
