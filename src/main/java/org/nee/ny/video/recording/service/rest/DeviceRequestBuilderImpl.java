package org.nee.ny.video.recording.service.rest;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: alec
 * Description:
 * @date: 13:29 2020-12-29
 */
@Component
public class DeviceRequestBuilderImpl implements DeviceRequestBuilder {

    @Override
    public RequestEntity asyncLoadCatalog() {
        return null;
    }
}
