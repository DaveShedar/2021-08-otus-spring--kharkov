package mongodb.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceAHealthIndicator implements HealthIndicator {

    private final String message_key = "Service A";

    @Override
    public Health health() {
        if(!isRunningServiceA()) {
            return Health.down().withDetail(message_key, "Не доступен!!!!").build();
        }
        return Health.up().withDetail(message_key, "Доступен").build();
    }

    private Boolean isRunningServiceA() {
        Boolean isRunning = true;

//        здесь может быть некоторая логика

        return isRunning;
    }
}
