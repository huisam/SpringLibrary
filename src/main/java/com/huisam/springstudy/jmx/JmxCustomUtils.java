package com.huisam.springstudy.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class JmxCustomUtils {
    private static final MBeanServer M_BEAN_SERVER = ManagementFactory.getPlatformMBeanServer();

    public static <T> T getMetric(Metrics metrics, Class<T> clazz) {
        if (clazz != metrics.getClazz()) {
            throw new IllegalArgumentException("doesn't match with metric type : " + clazz.getName());
        }

        try {
            final Object metricAttribute = M_BEAN_SERVER.getAttribute(metrics.getObjectName(), metrics.getAttribute());
            return castObject(metricAttribute, clazz);
        } catch (MBeanException | AttributeNotFoundException | InstanceNotFoundException | ReflectionException e) {
            throw new IllegalArgumentException("");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T castObject(Object object, Class<T> clazz) {
        return (T) object;
    }

}
