package org.nee.ny.video.recording.service;

import org.nee.ny.video.recording.domain.webhook.StreamNoneReaderRequest;

/**
 * @Author: alec
 * Description:
 * @date: 08:45 2020-11-24
 */
public interface StreamOptionService {

    void closeStream(StreamNoneReaderRequest streamNoneReaderRequest);
}
