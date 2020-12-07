package org.nee.ny.video.recording.listeners.controller;

import org.nee.ny.video.recording.domain.web.VideoPlayerResponse;
import org.nee.ny.video.recording.service.VideoPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description: 执行视频播放
 * @date: 16:59 2020-12-05
 */
@RestController
@RequestMapping(value = "player")
public class VideoPlayerController {

    @Autowired
    private VideoPlayerService videoPlayerService;

    @GetMapping(value = "start")
    public Mono<VideoPlayerResponse> start(@RequestParam(value = "deviceId") String deviceId,
                                           @RequestParam(value = "channelId")String channelId) {

        return Mono.just(videoPlayerService.startPlayer(deviceId, channelId));
    }

    @GetMapping(value = "stop")
    public Mono<VideoPlayerResponse> stop(@RequestParam(value = "deviceId") String deviceId,
                                           @RequestParam(value = "channelId")String channelId) {

        return Mono.just(videoPlayerService.stopPlayer(deviceId, channelId));
    }
}
