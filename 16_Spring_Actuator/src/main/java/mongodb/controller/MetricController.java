package mongodb.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MetricController {

    private Counter visitCounter;

    public MetricController(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
                .description("Количество посещений веб-приложения")
                .register(registry);
    }

    @GetMapping("/admin")
    public String helloFromAdmin() {
        visitCounter.increment();
        return "admin-Hello";
    }
}
