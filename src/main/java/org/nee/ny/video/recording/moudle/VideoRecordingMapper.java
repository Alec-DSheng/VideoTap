package org.nee.ny.video.recording.moudle;

import org.apache.ibatis.annotations.*;
import org.nee.ny.video.recording.domain.VideoRecording;

import java.util.List;
import java.util.Optional;

/**
 * @Author: alec
 * Description:
 * @date: 13:14 2020-11-18
 */
@Mapper
public interface VideoRecordingMapper {

    @Insert("insert into t_video_record(device_id,channel_id,device_name,channel_name,stream_code" +
            ",status,start_time,end_time,source, video_type, state, urgent_flag) " +
            "values (#{videoRecord.deviceId},#{videoRecord.channelId}" +
            ",#{videoRecord.deviceName},#{videoRecord.channelName},#{videoRecord.streamCode}" +
            ",#{videoRecord.status},#{videoRecord.startTime},#{videoRecord.endTime}," +
            "#{videoRecord.source}, #{videoRecord.videoType}, #{videoRecord.state}, #{videoRecord.urgentFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(@Param(value = "videoRecord") VideoRecording videoRecording);

    @Select("<script>" +
            " select * " +
            "  from `t_video_record` " +
            "  where " +
            "  status = 1 " +
            "<if test='videoRecording.deviceId != null'>" +
            "  and device_id = #{videoRecording.deviceId}" +
            "</if>" +
            "<if test='videoRecording.channelId != null'>" +
            "  and channel_id = #{videoRecording.channelId}" +
            "</if>" +
            " </script>")
    List<VideoRecording> searchList(@Param(value = "videoRecording") VideoRecording videoRecording);

    @Select("select * from t_video_record where id =#{id}")
    VideoRecording searchCodeById(@Param(value = "id") Integer id);

    @Select("select * from t_video_record where stream_code =#{streamCode}")
    VideoRecording searchCodeByStreamCode(@Param(value = "streamCode") String streamCode);


    @Select("select * from t_video_record where stream_code = #{streamCode} and status = #{status} ")
    Optional<VideoRecording> searchByStreamCode(@Param(value = "streamCode") String streamCode,
                                                @Param(value = "status") Integer status);

    @Update("update t_video_record set status = #{videoRecording.status}," +
            " end_time = #{videoRecording.endTime} where id = #{videoRecording.id}")
    void updateStatus(@Param(value = "videoRecording") VideoRecording videoRecording);

    @Update("update t_video_record set state = #{videoRecording.state} where id = #{videoRecording.id}")
    void updateState(@Param(value = "videoRecording") VideoRecording videoRecording);


    @Update("update t_video_record set urgent_flag = #{videoRecording.urgentFlag} where id = #{videoRecording.id}")
    void updateUrgentFlag(@Param(value = "videoRecording") VideoRecording videoRecording);
}
