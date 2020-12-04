package org.nee.ny.video.recording.service;

import com.github.pagehelper.PageInfo;
import org.nee.ny.video.recording.domain.VideoRecordingDetail;
import org.nee.ny.video.recording.domain.web.RecordingDetailResponse;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 13:24 2020-11-18
 */
public interface VideoRecordingService {

    /**
     * 开始录屏
     * */
    Mono startRecordingVideo(String channelId, Integer type, Integer urgentFlag);
    /**
     * 结束录屏
     * */
    Mono stopRecordingVideo(Integer id);

    /**
     * 存储录屏详情
     * */
    void saveRecordDetail(VideoRecordingDetail videoRecordingDetail);

    /**
     * 更新录像计划
     * */
    Mono updateUrgentFlag(Integer id, Integer urgentFlag);

    Mono<PageInfo<RecordingDetailResponse>> listDetail(String channelId, Integer pageNo, Integer pageSize);
}
