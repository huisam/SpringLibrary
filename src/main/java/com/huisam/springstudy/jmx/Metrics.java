package com.huisam.springstudy.jmx;

import lombok.Getter;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

@Getter
public enum Metrics {
    THREAD_BUSY("Tomcat:type=ThreadPool,name=\"http-nio-9000\"", "currentThreadsBusy", int.class),
    CPU_LOAD("java.lang:type=OperatingSystem", "CpuLoad", double.class)
    ;

    Metrics(String metricName, String attribute, Class<?> clazz) {
        try {
            this.objectName = new ObjectName(metricName);
        } catch (MalformedObjectNameException e) {
            throw new IllegalArgumentException("Not Found About the jmx metric : " + metricName);
        }
        this.attribute = attribute;
        this.clazz = clazz;
    }

    private final ObjectName objectName;
    private final String attribute;
    private final Class<?> clazz;


}
