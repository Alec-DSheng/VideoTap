package org.nee.ny.video.recording.listeners.webhook;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.common.ResponseResult;
import org.nee.ny.video.recording.domain.VideoRecordingDetail;
import org.nee.ny.video.recording.domain.webhook.RecordMp4Request;
import org.nee.ny.video.recording.domain.webhook.StreamNoneReaderRequest;
import org.nee.ny.video.recording.service.StreamOptionService;
import org.nee.ny.video.recording.service.VideoRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 17:51 2020-11-18
 */
@RestController
@RequestMapping(value = "index/hook")
@Slf4j
public class VideoRecordingHook {

    private final VideoRecordingService videoRecordingService;

    private final StreamOptionService streamOptionService;

    public VideoRecordingHook(VideoRecordingService videoRecordingService, StreamOptionService streamOptionService) {
        this.videoRecordingService = videoRecordingService;
        this.streamOptionService = streamOptionService;
    }

    @PostMapping(value = "on_record_mp4")
    public Mono<ResponseResult> onRecordMp4 (@RequestBody RecordMp4Request recordMp4Request) {
        log.info("录播完成接受到回调 {}", recordMp4Request);
        VideoRecordingDetail recordingDetail = VideoRecordingDetail.builder()
                .app(recordMp4Request.getApp())
                .fileName(recordMp4Request.getFileName())
                .filePath(recordMp4Request.getFilePath())
                .fileSize(recordMp4Request.getFileSize())
                .url(recordMp4Request.getUrl())
                .folder(recordMp4Request.getFolder())
                .mediaServerId(recordMp4Request.getMediaServerId())
                .startTime(String.valueOf(recordMp4Request.getStartTime()))
                .stream(recordMp4Request.getStream())
                .timeLen(recordMp4Request.getTimeLen())
                .build();
        videoRecordingService.saveRecordDetail(recordingDetail);
        return Mono.just(ResponseResult.ok());
    }

    @PostMapping(value = "on_stream_none_reader")
    public Mono<ResponseResult> onStreamNoneReader(@RequestBody StreamNoneReaderRequest streamNoneReaderRequest) {
        log.info("无人观看视频监听 {}", streamNoneReaderRequest);
        streamOptionService.closeStream(streamNoneReaderRequest);
        return Mono.just(ResponseResult.ok(true));
    }

}
