package org.nee.ny.video.recording.moudle;

import org.apache.ibatis.annotations.*;
import org.nee.ny.video.recording.domain.Device;

import java.util.List;
import java.util.Optional;

/**
 * @Author: alec
 * Description:
 * @date: 09:38 2020-12-02
 */
@Mapper
public interface DeviceMapper {

    @Select("select * from t_device ")
    List<Device> searchList();


    @Select("select * from t_device where code = #{code}")
    Optional<Device> searchDeviceByCode(@Param("code") String code);

    @Insert("insert into t_device (code, name, host,port,transport,channel_num,model," +
            "firmware, domain ) values (#{device.code},#{device.name},#{device.host},#{device.port},#{device.transport}," +
            "#{device.channelNum},#{device.model},#{device.firmware},#{device.domain})")
    void insert(@Param("device") Device device);

    @Update("update t_device set host=#{device.host},name=#{device.name},channel_num=#{device.channelNum},status=1," +
            "model=#{device.model}, firmware=#{device.firmware}, domain=#{device.domain} where code =#{device.code}")
    void updateDynamic(@Param("device") Device device);
}
