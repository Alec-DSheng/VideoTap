package org.nee.ny.video.recording.service;

import org.nee.ny.video.recording.domain.api.ZlMediaRequest;
import org.springframework.http.RequestEntity;

/**
 * @Author: alec
 * Description:
 * @date: 17:20 2020-12-05
 */
public interface VideoPlayerRequestBuilder {


    /**
     * 开始播放视频
     * */
    RequestEntity<Void> startPlayer(String deviceId, String channelId);
}
