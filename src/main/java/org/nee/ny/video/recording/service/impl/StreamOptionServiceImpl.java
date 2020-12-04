package org.nee.ny.video.recording.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.api.ZlMediaRequest;
import org.nee.ny.video.recording.domain.webhook.StreamNoneReaderRequest;
import org.nee.ny.video.recording.service.StreamOptionService;
import org.nee.ny.video.recording.service.ZlMediaRequestBuilder;
import org.nee.ny.video.recording.service.zkmedia.RequestSendService;
import org.nee.ny.video.recording.service.zkmedia.ZkMediaCloseStreamResponse;
import org.nee.ny.video.recording.service.zkmedia.ZkMediaRecordingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: alec
 * Description:
 * @date: 08:46 2020-11-24
 */
@Service
@Slf4j
public class StreamOptionServiceImpl extends RequestSendService<ZkMediaCloseStreamResponse> implements StreamOptionService {

    @Autowired
    private ZlMediaRequestBuilder zlMediaRequestBuilder;

    public StreamOptionServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public void closeStream(StreamNoneReaderRequest streamNoneReaderRequest) {
        RequestEntity<ZlMediaRequest> requestEntity = zlMediaRequestBuilder.closeStreams(streamNoneReaderRequest);
        log.info("request url {}, params {}", requestEntity.getUrl().toString(), streamNoneReaderRequest);
        ZkMediaCloseStreamResponse response = this.sendRequest(requestEntity, ZkMediaCloseStreamResponse.class);
        log.info("执行端流操作响应消息  {}", response);
    }
}
