package org.nee.ny.video.recording.service;

import org.nee.ny.video.recording.domain.api.ZlMediaRequest;
import org.nee.ny.video.recording.domain.webhook.StreamNoneReaderRequest;
import org.springframework.http.RequestEntity;

/**
 * @Author: alec
 * Description:
 * @date: 08:59 2020-11-16
 */
public interface ZlMediaRequestBuilder {

    /**
     * 开始录制视频
     * */
    RequestEntity<ZlMediaRequest> startRecordingVideo(String scrCode, Integer type);

    /**
     * 停止录制视频
     * */
    RequestEntity<ZlMediaRequest> stopRecordingVideo(String scrCode, Integer type);

    /**
     * 判断直播流是否在线
     * */
    RequestEntity<Void> liveStreamAlive(String scrCode);

    /**
     * 获取流录制状态
     * */
    RequestEntity<Void> getRecordingStatus(String scrCode);

    /**
     * 关闭直播流
     * */
    RequestEntity<ZlMediaRequest> closeStreams( StreamNoneReaderRequest streamNoneReaderRequest);
}
