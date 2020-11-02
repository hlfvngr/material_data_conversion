package com.hgits.hotc.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class HotcProperties {

    @Value("${hotc.tmpDir}")
    private String tmpDir;
}
