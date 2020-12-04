package org.nee.ny.video.recording.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: alec
 * Description:
 * @date: 09:10 2020-11-16
 */
@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "media.api")
public class ZlMediaApiProperties {

    private String apiUrl;

    private String vHost;

    private String app;

    private String secret;

    private String customizedPath;
}
