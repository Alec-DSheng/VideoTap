package org.nee.ny.video.recording.listeners.controller;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.service.VideoRecordingService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 14:11 2020-11-18
 */
@RestController
@RequestMapping(value = "recording")
@Slf4j
public class VideoRecordController {

    private final VideoRecordingService videoRecordingService;

    public VideoRecordController(VideoRecordingService videoRecordingService) {
        this.videoRecordingService = videoRecordingService;
    }

    @GetMapping(value = "start")
    public Mono startRecording(@RequestParam(value = "channelId") String channelId,
                               @RequestParam(value = "type") Integer type,
                               @RequestParam(value = "urgentFlag", defaultValue = "0") Integer urgentFlag) {
        return videoRecordingService.startRecordingVideo(channelId, type, urgentFlag);
    }

    @GetMapping(value = "end")
    public Mono endRecording(@RequestParam(value = "id") Integer id) {
        return videoRecordingService.stopRecordingVideo(id);
    }

    @GetMapping(value = "list")
    public Mono listRecording(@RequestParam(value = "channelId") String channelId,
                              @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        return videoRecordingService.listDetail(channelId, pageNo, pageSize);
    }

    @PutMapping(value = "urgent")
    public Mono listRecording(@RequestParam(value = "id") Integer id,
                              @RequestParam(value = "urgentFlag") Integer urgentFlag) {

        return videoRecordingService.updateUrgentFlag(id, urgentFlag);
    }

    /**
     * 查询当天指定渠道录像
     * */
    @GetMapping(value = "time")
    public Mono listTimeRecording(@RequestParam(value = "channelId") String channelId) {
        return null;
    }

}
