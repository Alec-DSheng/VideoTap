package org.nee.ny.video.recording.moudle;

import org.apache.ibatis.annotations.*;
import org.nee.ny.video.recording.domain.Device;
import org.nee.ny.video.recording.domain.DeviceChannel;

import java.util.List;
import java.util.Optional;

/**
 * @Author: alec
 * Description:
 * @date: 12:47 2020-11-11
 */
@Mapper
public interface DeviceChannelMapper {


    @Select("select channel.name channel_name, channel.channel_id," +
            "device.device_id, device.`name` device_name " +
            "from v_device_channel channel " +
            "left join v_device device on device.device_id = channel.parent_id" +
            " where channel.channel_id = #{channelId}")
    Optional<DeviceChannel> getDeviceChannelByCode(@Param(value = "channelId") String channelId);


    @Select("<script>" +
            " select channel.code, channel.name ,channel.status, channel.device_id " +
            " from t_device_channel channel " +
            "  where channel.device_id in  " +
            " <foreach item='item' index='index' collection='deviceIds' open='(' separator=',' close=')'> " +
            " #{item} " +
            "</foreach>" +
            " </script>")
    List<DeviceChannel> searchByIdList(@Param(value = "deviceIds") List<String> deviceIds);



    @Select("select * from t_device_channel where code = #{code}")
    Optional<DeviceChannel> searchDeviceChannelByCode(@Param("code") String code);

    @Insert("insert into t_device_channel (code, name, device_id,manufacturer,model,address,register_way,secret,status," +
            "owner, civil_code, no, parent_id, parental, safety_way ) values (#{channel.code},#{channel.name}," +
            "#{channel.deviceId},#{channel.manufacturer},#{channel.model}," +
            "#{channel.address},#{channel.registerWay},#{channel.secret},#{channel.status},#{channel.owner}," +
            "#{channel.civilCode},#{channel.no},#{channel.parentId},#{channel.parental},#{channel.safetyWay})")
    void insert(@Param("channel") DeviceChannel channel);

    @Update("update t_device_channel set name=#{channel.name},manufacturer=#{channel.manufacturer},status=#{channel.status}," +
            "model=#{channel.model}, address=#{channel.address}, register_way=#{channel.registerWay},secret=#{channel.secret}," +
            "owner=#{channel.owner},civil_code=#{channel.civilCode}, no= #{channel.no},parent_id = #{channel.parentId}," +
            "parental = #{channel.parental}, safety_way =#{channel.safetyWay}  where code =#{channel.code}")
    void updateDynamic(@Param("channel") DeviceChannel channel);

    @Select("<script>" +
            " select * from t_device_channel where device_id = #{channel.deviceId} " +
            " <when test='channel.code!=null'>" +
            "  and code LIKE CONCAT(#{channel.code},'%')  " +
            " </when> " +
            " </script>")
    List<DeviceChannel> searchList(@Param("channel") DeviceChannel channel);


    @Update("update t_device_channel set status = #{status} where device_id = #{deviceId}")
    void updateStatusByDevice(@Param(value = "status") Integer status, @Param(value = "deviceId") String deviceId);

}
