package org.nee.ny.video.recording.service;

import org.nee.ny.video.recording.domain.web.VideoPlayerResponse;

/**
 * @Author: alec
 * Description:
 * @date: 17:03 2020-12-05
 */
public interface VideoPlayerService {

    VideoPlayerResponse startPlayer(String deviceId, String channelId);

    VideoPlayerResponse stopPlayer(String deviceId, String channelId);
}
