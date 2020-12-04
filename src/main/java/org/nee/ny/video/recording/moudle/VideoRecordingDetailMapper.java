package org.nee.ny.video.recording.moudle;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nee.ny.video.recording.domain.VideoRecordingDetail;
import org.nee.ny.video.recording.domain.web.RecordingDetailResponse;

import java.util.List;

/**
 * @Author: alec
 * Description:
 * @date: 18:42 2020-11-18
 */
@Mapper
public interface VideoRecordingDetailMapper {

    @Insert(" insert into t_video_recording_detail (" +
            "app,file_name,file_path,file_size,folder,start_time,stream,time_len,url,vhost,media_server_id, " +
            "end_time, recording_id) " +
            "values (#{detail.app},#{detail.fileName},#{detail.filePath},#{detail.fileSize},#{detail.folder}," +
            "#{detail.startTime},#{detail.stream},#{detail.timeLen},#{detail.url},#{detail.vHost}," +
            "#{detail.mediaServerId}, #{detail.endTime}, #{detail.recordId})")
    void insert(@Param(value = "detail") VideoRecordingDetail videoRecordingDetail);


    @Select("select detail.url, detail.start_time, detail.end_time, record.channel_name, record.channel_id, " +
            "record.id, record.urgent_flag, detail.time_len " +
            "from t_video_recording_detail  detail " +
            "left join t_video_record record on record.id = detail.recording_id " +
            "where record.channel_id = #{channelId}")
    List<RecordingDetailResponse> searchList(@Param(value = "channelId") String channelId);
}
