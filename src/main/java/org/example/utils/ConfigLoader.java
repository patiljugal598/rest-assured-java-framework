package org.example.utils;

import java.util.Properties;

public class ConfigLoader {

    static Properties properties;
    private static ConfigLoader configLoader;


    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/main/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseURI(){
        String value = properties.getProperty("baseURI");
        if(value !=null)
            return value;
        else throw new RuntimeException("property baseURI is not specified in config.properties file");
    }
}
