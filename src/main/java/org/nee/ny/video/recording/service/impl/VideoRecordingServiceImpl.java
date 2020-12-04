package org.nee.ny.video.recording.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.DeviceChannel;
import org.nee.ny.video.recording.domain.VideoRecording;
import org.nee.ny.video.recording.domain.VideoRecordingDetail;
import org.nee.ny.video.recording.domain.api.ZlMediaRequest;
import org.nee.ny.video.recording.domain.enums.VideoRecordingEnums;
import org.nee.ny.video.recording.domain.web.RecordingDetailResponse;
import org.nee.ny.video.recording.exception.NotFoundDeviceException;
import org.nee.ny.video.recording.exception.ParamsErrorException;
import org.nee.ny.video.recording.exception.ResponseErrorException;
import org.nee.ny.video.recording.moudle.DeviceChannelMapper;
import org.nee.ny.video.recording.moudle.VideoRecordingDetailMapper;
import org.nee.ny.video.recording.moudle.VideoRecordingMapper;
import org.nee.ny.video.recording.service.VideoRecordingService;
import org.nee.ny.video.recording.service.ZlMediaRequestBuilder;
import org.nee.ny.video.recording.service.zkmedia.RequestSendService;
import org.nee.ny.video.recording.service.zkmedia.ZkMediaRecordingResponse;
import org.nee.ny.video.recording.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: alec
 * Description:
 * @date: 13:26 2020-11-18
 */
@Service
@Slf4j
public class VideoRecordingServiceImpl extends RequestSendService<ZkMediaRecordingResponse> implements VideoRecordingService {

    private final DeviceChannelMapper deviceChannelMapper;

    private final VideoRecordingMapper videoRecordingMapper;

    private final RedisTemplate<String, String> redisTemplate;

    private final ZlMediaRequestBuilder zlMediaRequestBuilder;

    private final VideoRecordingDetailMapper videoRecordingDetailMapper;

    private final static String S_CODE = "S_CO:";

    @Autowired
    public VideoRecordingServiceImpl(DeviceChannelMapper deviceChannelMapper, VideoRecordingMapper videoRecordingMapper,
                                     RestTemplate restTemplate, RedisTemplate<String, String> redisTemplate,
                                     ZlMediaRequestBuilder zlMediaRequestBuilder, VideoRecordingDetailMapper videoRecordingDetailMapper) {
        super(restTemplate);
        this.deviceChannelMapper = deviceChannelMapper;
        this.videoRecordingMapper = videoRecordingMapper;
        this.redisTemplate = redisTemplate;
        this.zlMediaRequestBuilder = zlMediaRequestBuilder;
        this.videoRecordingDetailMapper = videoRecordingDetailMapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mono startRecordingVideo(String channelId, Integer type, Integer urgentFlag) {
       DeviceChannel deviceChannel = deviceChannelMapper.getDeviceChannelByCode(channelId)
               .orElseThrow(() -> new NotFoundDeviceException("ChannelId " + channelId));
       String streamCode = getStreamCode(deviceChannel);
       videoRecordingMapper.searchByStreamCode(streamCode,
                VideoRecordingEnums.RecordStatus.RUNNING.getCode()).ifPresent(videoRecording -> {
            throw new ParamsErrorException("该流已有录屏任务执行" + streamCode + "channel is " + videoRecording.getChannelId());
        });
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        VideoRecording videoRecording = VideoRecording.builder()
        .channelId(deviceChannel.getCode())
        .channelName(deviceChannel.getName())
        .deviceId(deviceChannel.getDeviceId())
        //.deviceName(deviceChannel.ge())
        .startTime(timestamp)
        .endTime(timestamp)
        .streamCode(streamCode)
        .urgentFlag(urgentFlag)
        .videoType(type)
        .state(VideoRecordingEnums.RecordStatus.RUNNING.getCode())
        .source(VideoRecordingEnums.RecordSourceStatus.Manual.getCode())
        .status(VideoRecordingEnums.RecordStatus.RUNNING.getCode()).build();
       videoRecordingMapper.insert(videoRecording);
       sendRequest(streamCode, type);
       return Mono.just(videoRecording);
    }

    @Override
    public Mono stopRecordingVideo(Integer id) {
        VideoRecording videoRecording = videoRecordingMapper.searchCodeById(id);
        if (Objects.isNull(videoRecording) || videoRecording.getStatus().equals(VideoRecordingEnums.RecordStatus.FINISH.getCode())) {
            throw new NotFoundDeviceException("录屏记录不存在或已结束" + id);
        }

        videoRecording.setState(VideoRecordingEnums.RecordStatus.FINISH.getCode());
        videoRecordingMapper.updateState(videoRecording);
        RequestEntity<ZlMediaRequest>  requestEntity = zlMediaRequestBuilder.stopRecordingVideo(videoRecording.getStreamCode(),
                videoRecording.getVideoType());
        ZkMediaRecordingResponse recordingResponse = this.sendRequest(requestEntity, ZkMediaRecordingResponse.class);
        log.info("response code {}", recordingResponse.code());
        return Mono.just(videoRecording);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecordDetail(VideoRecordingDetail videoRecordingDetail) {
        videoRecordingMapper.searchByStreamCode(videoRecordingDetail.getStream(),
                VideoRecordingEnums.RecordStatus.RUNNING.getCode()).ifPresent(videoRecording -> {
            long timestamp = System.currentTimeMillis();
            videoRecordingDetail.setRecordId(videoRecording.getId());
            videoRecordingDetail.setEndTime(String.valueOf(timestamp));
            videoRecordingDetailMapper.insert(videoRecordingDetail);
            videoRecording.setStatus(VideoRecordingEnums.RecordStatus.FINISH.getCode());
            videoRecording.setEndTime(new Timestamp(timestamp));
            videoRecordingMapper.updateStatus(videoRecording);
        });
    }

    @Override
    public Mono updateUrgentFlag(Integer id, Integer urgentFlag) {
       VideoRecording videoRecording =  videoRecordingMapper.searchCodeById(id);
       if (Objects.isNull(videoRecording)) {
           throw new NotFoundDeviceException("device id " + id);
       }
       videoRecording.setUrgentFlag(urgentFlag);
       videoRecordingMapper.updateUrgentFlag(videoRecording);
       return Mono.just(id);
    }

    @Override
    public Mono<PageInfo<RecordingDetailResponse>> listDetail(String channelId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<RecordingDetailResponse> recordingDetailResponseList = videoRecordingDetailMapper.searchList(channelId);
        recordingDetailResponseList.sort(Comparator.comparing(RecordingDetailResponse::getEndTime));
        return Mono.just(new PageInfo<>(recordingDetailResponseList));
    }

    private String getStreamCode(DeviceChannel deviceChannel) {
        String key = S_CODE + deviceChannel.getCode();
        String code = Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElseThrow(() ->
                new NotFoundDeviceException("SCODE" + key));
        int intValue = Integer.parseInt(code);
        code = Integer.toHexString(intValue);
        code = CodeUtil.padLeft(code, 8, '0');
        return code.toUpperCase();
    }

    private void sendRequest(String streamCode, Integer type) {
        RequestEntity<ZlMediaRequest> requestEntity = zlMediaRequestBuilder.startRecordingVideo(streamCode, type);
        log.info("request url {}, code {}", requestEntity.getUrl().toString(), streamCode);
        ZkMediaRecordingResponse recordingResponse = this.sendRequest(requestEntity, ZkMediaRecordingResponse.class);
        log.info("response code {}", recordingResponse.code());
        if (!recordingResponse.isSuccess()) {
            throw new ResponseErrorException(recordingResponse.getCode(),
                    String.valueOf(recordingResponse.getResult()));
        }
    }
}
