package org.nee.ny.video.recording.service.rest;

import org.springframework.http.RequestEntity;

/**
 * @Author: alec
 * Description:
 * @date: 13:26 2020-12-29
 */
public interface DeviceRequestBuilder {

    /**
     * 异步加载渠道信息
     * */
     RequestEntity asyncLoadCatalog();


}
