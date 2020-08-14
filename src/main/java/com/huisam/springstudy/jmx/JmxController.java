package com.huisam.springstudy.jmx;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmxController {

    @GetMapping("/metrics")
    public ResponseEntity<String> metrics() {
        final Double cpuLoad = JmxCustomUtils.getMetric(Metrics.CPU_LOAD, double.class);
        final Integer thread_busy = JmxCustomUtils.getMetric(Metrics.THREAD_BUSY, int.class);

        return ResponseEntity.ok(
              "cpuLoad : " + cpuLoad + ", threadBusy : " + thread_busy
        );
    }

}
