package org.nee.ny.video.recording.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.nee.ny.video.recording.domain.EventEnvelope;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: alec
 * Description:
 * @date: 17:05 2020-12-02
 */
@Slf4j
public class JacksonJsonSerializer implements Deserializer{


    private ObjectMapper mapper;

    @Override
    public void configure(Map map, boolean b) {
        mapper = new ObjectMapper();
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, EventEnvelope.class);
        } catch (IOException e) {
            log.error("My Deserializer error:", e);
        }
        return null;
    }

    @Override
    public void close() {

    }
}
