package com.yufeng.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-07
 */
@Data
@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath: file-upload-dev.properties")
/**
 * 以后如果上生产的话, 这边的dev就可以改成prod
 */
public class FileUpload {

    private String imageUserFaceLocation;

    private String imageServerUrl;

}
