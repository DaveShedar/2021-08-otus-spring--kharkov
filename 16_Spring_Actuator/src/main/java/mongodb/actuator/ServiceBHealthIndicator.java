package mongodb.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceBHealthIndicator implements HealthIndicator {
    private final String message_key = "Service B";

    @Override
    public Health health() {
        if(!isRunningServiceB()) {
            return Health.down().withDetail(message_key, "Не доступен!!!!").build();
        }
        return Health.up().withDetail(message_key, "Доступен").build();
    }

    private Boolean isRunningServiceB() {
        Boolean isRunning = true;

//        здесь может быть некоторая логика

        return isRunning;
    }
}
