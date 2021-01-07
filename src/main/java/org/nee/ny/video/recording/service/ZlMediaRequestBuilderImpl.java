package org.nee.ny.video.recording.service;

import org.nee.ny.video.recording.configuration.ZlMediaApiProperties;
import org.nee.ny.video.recording.domain.api.ZlMediaRequest;
import org.nee.ny.video.recording.domain.webhook.StreamNoneReaderRequest;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

/**
 * @Author: alec
 * Description:
 * @date: 09:07 2020-11-16
 */
@Service
public class ZlMediaRequestBuilderImpl implements ZlMediaRequestBuilder {

    private final static String RECORDING_START = "/index/api/startRecord";

    private final static String RECORDING_STOP = "/index/api/stopRecord";

    private final static String CLOSE_STREAM = "/index/api/close_streams";

    private final ZlMediaApiProperties zlMediaApiProperties;

    public ZlMediaRequestBuilderImpl(ZlMediaApiProperties zlMediaApiProperties) {
        this.zlMediaApiProperties = zlMediaApiProperties;
    }

    @Override
    public RequestEntity<ZlMediaRequest> startRecordingVideo(String scrCode, Integer type) {
        ZlMediaRequest zlMediaRequest = new ZlMediaRequest(zlMediaApiProperties,scrCode, type);
        return buildPostRequest(RECORDING_START,zlMediaRequest);
    }

    @Override
    public RequestEntity<ZlMediaRequest> stopRecordingVideo(String scrCode, Integer type) {
        ZlMediaRequest zlMediaRequest = new ZlMediaRequest(zlMediaApiProperties,scrCode, type);
        return buildPostRequest(RECORDING_STOP,zlMediaRequest);
    }

    @Override
    public RequestEntity<Void> liveStreamAlive(String scrCode) {
        return null;
    }

    @Override
    public RequestEntity<Void> getRecordingStatus(String scrCode) {
        return null;
    }

    @Override
    public RequestEntity<ZlMediaRequest> closeStreams( StreamNoneReaderRequest streamNoneReaderRequest) {
        ZlMediaRequest zlMediaRequest = new ZlMediaRequest(zlMediaApiProperties,streamNoneReaderRequest);
        return buildPostRequest(CLOSE_STREAM,zlMediaRequest);
    }


    private <T> RequestEntity<T> buildPostRequest(String path, T requestBody) {

        URI uri = fromHttpUrl(zlMediaApiProperties.getApiUrl()).path(path).build().toUri();
        RequestEntity.BodyBuilder builder =
                RequestEntity.post(uri)
                        .contentType(APPLICATION_JSON);
        return builder.body(requestBody);
    }
}
