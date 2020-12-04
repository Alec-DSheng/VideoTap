package org.nee.ny.video.recording.service.zkmedia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: alec
 * Description:
 * @date: 09:24 2020-11-16
 */
@Slf4j
public abstract class RequestSendService<T extends RecordingResponse> {

    private final RestTemplate restTemplate;

    public RequestSendService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public final T sendRequest(RequestEntity requestEntity, Class classes ) {
        log.debug("Sending  to {}", requestEntity.getBody());
        ResponseEntity<T> responseEntity = this.restTemplate.exchange(requestEntity,classes);
        log.info("Received {} ",  responseEntity.getBody());
        return  (T)responseEntity.getBody();
    }
}
