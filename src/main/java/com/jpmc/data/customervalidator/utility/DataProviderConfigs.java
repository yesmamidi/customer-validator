package com.jpmc.data.customervalidator.utility;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@ConfigurationProperties(prefix = "my")
@Data
public class DataProviderConfigs {
    private Map<String, String> providers;
}
