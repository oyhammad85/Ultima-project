package com.paxera.ultima.utils.read_properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ReadProperties {
    private static final String ROOT_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String UltimaConfiguration = ROOT_PATH.concat("ultima.properties");

    private ReadProperties() {
    }

    public static Properties setPaxerahealthCorporationConfig() throws IOException {
        Properties configProperties = new Properties();
        FileInputStream inputStream = new FileInputStream(UltimaConfiguration);
        configProperties.load(inputStream);
        return configProperties;
    }
}
